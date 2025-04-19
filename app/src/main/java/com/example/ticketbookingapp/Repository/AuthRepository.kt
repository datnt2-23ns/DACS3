//package com.example.ticketbookingapp.Repository
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import com.example.ticketbookingapp.Domain.UserModel
//import com.google.firebase.database.DataSnapshot
//import com.google.firebase.database.DatabaseError
//import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.database.ValueEventListener
//
//class AuthRepository {
//    private val database = FirebaseDatabase.getInstance()
//    private val usersRef = database.getReference("Users")
//
//    // Đăng nhập: Kiểm tra tài khoản bằng username hoặc email
//    fun login(identifier: String, password: String): LiveData<UserModel?> {
//        val result = MutableLiveData<UserModel?>()
//        // Tìm tất cả users và kiểm tra username hoặc email
//        usersRef.orderByChild("username").equalTo(identifier)
//            .addListenerForSingleValueEvent(object : ValueEventListener {
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    if (snapshot.exists()) {
//                        for (userSnapshot in snapshot.children) {
//                            val user = userSnapshot.getValue(UserModel::class.java)
//                            if (user != null && user.password == password) {
//                                result.value = user
//                                return
//                            }
//                        }
//                        result.value = null
//                    } else {
//                        // Nếu không tìm thấy bằng username, thử tìm bằng email
//                        usersRef.orderByChild("email").equalTo(identifier)
//                            .addListenerForSingleValueEvent(object : ValueEventListener {
//                                override fun onDataChange(emailSnapshot: DataSnapshot) {
//                                    if (emailSnapshot.exists()) {
//                                        for (userSnapshot in emailSnapshot.children) {
//                                            val user = userSnapshot.getValue(UserModel::class.java)
//                                            if (user != null && user.password == password) {
//                                                result.value = user
//                                                return
//                                            }
//                                        }
//                                        result.value = null
//                                    } else {
//                                        result.value = null
//                                    }
//                                }
//
//                                override fun onCancelled(error: DatabaseError) {
//                                    result.value = null
//                                }
//                            })
//                    }
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//                    result.value = null
//                }
//            })
//        return result
//    }
//
//    // Đăng ký: Lưu tài khoản mới vào Firebase
//    fun register(username: String, password: String, email: String, role: String = "user"): LiveData<Boolean> {
//        val result = MutableLiveData<Boolean>()
//        usersRef.child(username).addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if (snapshot.exists()) {
//                    // Tên đăng nhập đã tồn tại
//                    result.value = false
//                } else {
//                    // Kiểm tra email đã tồn tại chưa
//                    usersRef.orderByChild("email").equalTo(email)
//                        .addListenerForSingleValueEvent(object : ValueEventListener {
//                            override fun onDataChange(emailSnapshot: DataSnapshot) {
//                                if (emailSnapshot.exists()) {
//                                    // Email đã tồn tại
//                                    result.value = false
//                                } else {
//                                    val user = UserModel(username, password, role, email)
//                                    usersRef.child(username).setValue(user)
//                                        .addOnSuccessListener { result.value = true }
//                                        .addOnFailureListener { result.value = false }
//                                }
//                            }
//
//                            override fun onCancelled(error: DatabaseError) {
//                                result.value = false
//                            }
//                        })
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                result.value = false
//            }
//        })
//        return result
//    }
//}

package com.example.ticketbookingapp.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ticketbookingapp.Activities.Auth.AuthResult
import com.example.ticketbookingapp.Domain.UserModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import android.util.Log

class AuthRepository {
    private val database = FirebaseDatabase.getInstance()
    private val usersRef = database.getReference("Users")
    private val TAG = "AuthRepository"

    fun login(identifier: String, password: String): LiveData<AuthResult> {
        val result = MutableLiveData<AuthResult>()
        Log.d(TAG, "Starting login query for identifier: $identifier")
        usersRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    Log.d(TAG, "Received snapshot with ${snapshot.childrenCount} users")
                    var userFound = false
                    for (userSnapshot in snapshot.children) {
                        try {
                            val user = userSnapshot.getValue(UserModel::class.java)
                            if (user == null) {
                                Log.e(TAG, "Failed to parse user data for snapshot: ${userSnapshot.key}, data: ${userSnapshot.value}")
                                continue
                            }
                            if (user.username == identifier || user.email == identifier) {
                                userFound = true
                                if (user.password == password) {
                                    Log.d(TAG, "User found and password matched: ${user.username}")
                                    result.value = AuthResult.Success(user)
                                } else {
                                    Log.d(TAG, "Password mismatch for user: ${user.username}. Input length: ${password.length}, Expected length: ${user.password.length}")
                                    result.value = AuthResult.Failure("Wrong password")
                                }
                                break
                            }
                        } catch (e: Exception) {
                            Log.e(TAG, "Error parsing user ${userSnapshot.key}: ${e.message}")
                            continue
                        }
                    }
                    if (!userFound) {
                        Log.d(TAG, "No user found with identifier: $identifier")
                        result.value = AuthResult.Failure("User not found")
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Error processing snapshot: ${e.message}")
                    result.value = AuthResult.Failure("Error: ${e.message}")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(TAG, "Login query cancelled: ${error.message}")
                result.value = AuthResult.Failure("Network error: ${error.message}")
            }
        })
        return result
    }

    fun register(username: String, password: String, email: String, role: String = "user"): LiveData<Boolean> {
        val result = MutableLiveData<Boolean>()
        usersRef.child(username).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    Log.d(TAG, "Username $username already exists")
                    result.value = false
                } else {
                    usersRef.orderByChild("email").equalTo(email)
                        .addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(emailSnapshot: DataSnapshot) {
                                if (emailSnapshot.exists()) {
                                    Log.d(TAG, "Email $email already exists")
                                    result.value = false
                                } else {
                                    try {
                                        val user = UserModel(
                                            username = username,
                                            password = password,
                                            role = role,
                                            email = email,
                                            phoneNumber = ""
                                        )
                                        usersRef.child(username).setValue(user)
                                            .addOnSuccessListener {
                                                Log.d(TAG, "User $username registered successfully")
                                                result.value = true
                                            }
                                            .addOnFailureListener { e ->
                                                Log.e(TAG, "Failed to register user: ${e.message}")
                                                result.value = false
                                            }
                                    } catch (e: Exception) {
                                        Log.e(TAG, "Error creating user: ${e.message}")
                                        result.value = false
                                    }
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                                Log.e(TAG, "Register query cancelled: ${error.message}")
                                result.value = false
                            }
                        })
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(TAG, "Username check cancelled: ${error.message}")
                result.value = false
            }
        })
        return result
    }
}