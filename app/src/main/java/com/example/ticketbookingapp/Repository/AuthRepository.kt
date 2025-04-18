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
//    // Đăng nhập: Kiểm tra tài khoản có tồn tại không
//    fun login(username: String, password: String): LiveData<UserModel?> {
//        val result = MutableLiveData<UserModel?>()
//        usersRef.child(username).addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if (snapshot.exists()) {
//                    val user = snapshot.getValue(UserModel::class.java)
//                    if (user != null && user.password == password) {
//                        result.value = user
//                    } else {
//                        result.value = null
//                    }
//                } else {
//                    result.value = null
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                result.value = null
//            }
//        })
//        return result
//    }
//
//    // Đăng ký: Lưu tài khoản mới vào Firebase
//    fun register(username: String, password: String, role: String = "user"): LiveData<Boolean> {
//        val result = MutableLiveData<Boolean>()
//        usersRef.child(username).addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if (snapshot.exists()) {
//                    // Tên đăng nhập đã tồn tại
//                    result.value = false
//                } else {
//                    val user = UserModel(username, password, role)
//                    usersRef.child(username).setValue(user)
//                        .addOnSuccessListener { result.value = true }
//                        .addOnFailureListener { result.value = false }
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
import com.example.ticketbookingapp.Domain.UserModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AuthRepository {
    private val database = FirebaseDatabase.getInstance()
    private val usersRef = database.getReference("Users")

    // Đăng nhập: Kiểm tra tài khoản bằng username hoặc email
    fun login(identifier: String, password: String): LiveData<UserModel?> {
        val result = MutableLiveData<UserModel?>()
        // Tìm tất cả users và kiểm tra username hoặc email
        usersRef.orderByChild("username").equalTo(identifier)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (userSnapshot in snapshot.children) {
                            val user = userSnapshot.getValue(UserModel::class.java)
                            if (user != null && user.password == password) {
                                result.value = user
                                return
                            }
                        }
                        result.value = null
                    } else {
                        // Nếu không tìm thấy bằng username, thử tìm bằng email
                        usersRef.orderByChild("email").equalTo(identifier)
                            .addListenerForSingleValueEvent(object : ValueEventListener {
                                override fun onDataChange(emailSnapshot: DataSnapshot) {
                                    if (emailSnapshot.exists()) {
                                        for (userSnapshot in emailSnapshot.children) {
                                            val user = userSnapshot.getValue(UserModel::class.java)
                                            if (user != null && user.password == password) {
                                                result.value = user
                                                return
                                            }
                                        }
                                        result.value = null
                                    } else {
                                        result.value = null
                                    }
                                }

                                override fun onCancelled(error: DatabaseError) {
                                    result.value = null
                                }
                            })
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    result.value = null
                }
            })
        return result
    }

    // Đăng ký: Lưu tài khoản mới vào Firebase
    fun register(username: String, password: String, email: String, role: String = "user"): LiveData<Boolean> {
        val result = MutableLiveData<Boolean>()
        usersRef.child(username).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    // Tên đăng nhập đã tồn tại
                    result.value = false
                } else {
                    // Kiểm tra email đã tồn tại chưa
                    usersRef.orderByChild("email").equalTo(email)
                        .addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(emailSnapshot: DataSnapshot) {
                                if (emailSnapshot.exists()) {
                                    // Email đã tồn tại
                                    result.value = false
                                } else {
                                    val user = UserModel(username, password, role, email)
                                    usersRef.child(username).setValue(user)
                                        .addOnSuccessListener { result.value = true }
                                        .addOnFailureListener { result.value = false }
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                                result.value = false
                            }
                        })
                }
            }

            override fun onCancelled(error: DatabaseError) {
                result.value = false
            }
        })
        return result
    }
}