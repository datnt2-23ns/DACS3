//package com.example.ticketbookingapp.Repository
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import com.example.ticketbookingapp.Domain.FlightModel
//import com.example.ticketbookingapp.Domain.LocationModel
//import com.google.firebase.database.DataSnapshot
//import com.google.firebase.database.DatabaseError
//import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.database.Query
//import com.google.firebase.database.ValueEventListener
//
//class MainRepository {
//    private val firebaseDatabase = FirebaseDatabase.getInstance()
//
//    fun loadLocation(): LiveData<MutableList<LocationModel>> {
//        val listData = MutableLiveData<MutableList<LocationModel>>()
//        val ref = firebaseDatabase.getReference("Locations")
//        ref.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val list = mutableListOf<LocationModel>()
//                if (snapshot.exists()) {
//                    for (childSnapshot in snapshot.children) {
//                        val item = childSnapshot.getValue(LocationModel::class.java)
//                        item?.let {
//                            list.add(it)
//                        } ?: run {
//                            println("Failed to parse LocationModel: ${childSnapshot.key}")
//                        }
//                    }
//                    println("Locations loaded: ${list.size} items")
//                    listData.value = list
//                } else {
//                    println("Locations node is empty or does not exist")
//                    listData.value = mutableListOf() // Trả về rỗng để ngừng loading
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                println("Firebase error in loadLocation: ${error.message}")
//                listData.value = mutableListOf() // Trả về rỗng để ngừng loading
//            }
//        })
//        return listData
//    }
//
//    fun loadFiltered(from: String, to: String): LiveData<MutableList<FlightModel>> {
//        val listData = MutableLiveData<MutableList<FlightModel>>()
//        val ref = firebaseDatabase.getReference("Flights")
//        val query: Query = ref.orderByChild("from").equalTo(from)
//        query.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val lists = mutableListOf<FlightModel>()
//                if (snapshot.exists()) {
//                    for (childSnapshot in snapshot.children) {
//                        val list = childSnapshot.getValue(FlightModel::class.java)
//                        if (list != null && list.To == to) {
//                            lists.add(list)
//                        }
//                    }
//                    println("Filtered flights loaded: ${lists.size} items")
//                } else {
//                    println("Flights node is empty or no matches for from=$from")
//                }
//                listData.value = lists
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                println("Firebase error in loadFiltered: ${error.message}")
//                listData.value = mutableListOf() // Trả về rỗng để ngừng loading
//            }
//        })
//        return listData
//    }
//}

//package com.example.ticketbookingapp.Repository
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import com.example.ticketbookingapp.Domain.FlightModel
//import com.example.ticketbookingapp.Domain.LocationModel
//import com.google.firebase.database.DataSnapshot
//import com.google.firebase.database.DatabaseError
//import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.database.ValueEventListener
//import java.util.Locale
//
//class MainRepository {
//    private val firebaseDatabase = FirebaseDatabase.getInstance()
//
//    fun loadLocation(): LiveData<MutableList<LocationModel>> {
//        val listData = MutableLiveData<MutableList<LocationModel>>()
//        val ref = firebaseDatabase.getReference("Locations")
//        ref.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val list = mutableListOf<LocationModel>()
//                if (snapshot.exists()) {
//                    for (childSnapshot in snapshot.children) {
//                        val item = childSnapshot.getValue(LocationModel::class.java)
//                        item?.let {
//                            list.add(it)
//                        } ?: run {
//                            println("Failed to parse LocationModel: ${childSnapshot.key}")
//                        }
//                    }
//                    println("Locations loaded: ${list.size} items")
//                    listData.value = list
//                } else {
//                    println("Locations node is empty or does not exist")
//                    listData.value = mutableListOf()
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                println("Firebase error in loadLocation: ${error.message}")
//                listData.value = mutableListOf()
//            }
//        })
//        return listData
//    }
//
//    fun loadFiltered(
//        from: String,
//        to: String,
//        departureDate: String,
//        typeClass: String
//    ): LiveData<MutableList<FlightModel>> {
//        val listData = MutableLiveData<MutableList<FlightModel>>()
//        val ref = firebaseDatabase.getReference("Flights")
//        ref.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val lists = mutableListOf<FlightModel>()
//                println("Query snapshot exists: ${snapshot.exists()}, children count: ${snapshot.childrenCount}")
//                if (snapshot.exists()) {
//                    for (childSnapshot in snapshot.children) {
//                        val list = childSnapshot.getValue(FlightModel::class.java)
//                        if (list != null) {
//                            println("Flight data: From=${list.From}, To=${list.To}, Date=${list.Date}, TypeClass=${list.TypeClass}")
//                            val normalizedDate = list.Date.trim().toLowerCase(Locale.getDefault())
//                            val inputDate = departureDate.trim().toLowerCase(Locale.getDefault())
//                            if (list.From.trim() == from.trim() &&
//                                list.To.trim() == to.trim() &&
//                                normalizedDate == inputDate &&
//                                list.TypeClass?.trim() == typeClass.trim()
//                            ) {
//                                lists.add(list)
//                            } else {
//                                println("Flight not matched: From=${list.From} vs $from, To=${list.To} vs $to, Date=$normalizedDate vs $inputDate, TypeClass=${list.TypeClass} vs $typeClass")
//                            }
//                        } else {
//                            println("Failed to parse FlightModel: ${childSnapshot.key}")
//                        }
//                    }
//                    println("Filtered flights loaded: ${lists.size} items")
//                } else {
//                    println("Flights node is empty")
//                }
//                listData.value = lists
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                println("Firebase error in loadFiltered: ${error.message}")
//                listData.value = mutableListOf()
//            }
//        })
//        return listData
//    }
//}

//package com.example.ticketbookingapp.Repository
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import com.example.ticketbookingapp.Domain.FlightModel
//import com.example.ticketbookingapp.Domain.LocationModel
//import com.google.firebase.database.DataSnapshot
//import com.google.firebase.database.DatabaseError
//import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.database.ValueEventListener
//
//class MainRepository {
//    private val firebaseDatabase = FirebaseDatabase.getInstance()
//
//    fun loadLocation(): LiveData<MutableList<LocationModel>> {
//        val listData = MutableLiveData<MutableList<LocationModel>>()
//        val ref = firebaseDatabase.getReference("Locations")
//        ref.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val list = mutableListOf<LocationModel>()
//                if (snapshot.exists()) {
//                    for (childSnapshot in snapshot.children) {
//                        try {
//                            val item = childSnapshot.getValue(LocationModel::class.java)
//                            item?.let {
//                                list.add(it)
//                            } ?: run {
//                                println("Failed to parse LocationModel: ${childSnapshot.key}")
//                            }
//                        } catch (e: Exception) {
//                            println("Error parsing LocationModel: ${e.message}")
//                        }
//                    }
//                    println("Locations loaded: ${list.size} items")
//                    listData.value = list
//                } else {
//                    println("Locations node is empty or does not exist")
//                    listData.value = mutableListOf()
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                println("Firebase error in loadLocation: ${error.message}")
//                listData.value = mutableListOf()
//            }
//        })
//        return listData
//    }
//
//    fun loadFiltered(
//        from: String,
//        to: String,
//        departureDate: String,
//        typeClass: String,
//        numPassenger: Int = 1
//    ): LiveData<MutableList<FlightModel>> {
//        val listData = MutableLiveData<MutableList<FlightModel>>()
//        val ref = firebaseDatabase.getReference("Flights")
//        ref.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val lists = mutableListOf<FlightModel>()
//                println("Query snapshot exists: ${snapshot.exists()}, children count: ${snapshot.childrenCount}")
//                if (snapshot.exists()) {
//                    // Xử lý node Flights dạng mảng
//                    snapshot.children.forEachIndexed { index, childSnapshot ->
//                        try {
//                            val list = childSnapshot.getValue(FlightModel::class.java)
//                            if (list != null) {
//                                println("Flight [$index] data: From='${list.From}', To='${list.To}', Date='${list.Date}', TypeClass='${list.TypeClass}'")
//                                // Chuẩn hóa chuỗi để so sánh
//                                val normalizedFrom = list.From.trim().replace("\\s+".toRegex(), " ").lowercase()
//                                val normalizedTo = list.To.trim().replace("\\s+".toRegex(), " ").lowercase()
//                                val normalizedDate = list.Date.trim().replace("\\s+".toRegex(), " ").lowercase()
//                                val normalizedTypeClass = list.TypeClass.trim().replace("\\s+".toRegex(), " ").lowercase()
//                                val inputFrom = from.trim().replace("\\s+".toRegex(), " ").lowercase()
//                                val inputTo = to.trim().replace("\\s+".toRegex(), " ").lowercase()
//                                val inputDate = departureDate.trim().replace("\\s+".toRegex(), " ").lowercase()
//                                val inputTypeClass = typeClass.trim().replace("\\s+".toRegex(), " ").lowercase()
//
//                                if (normalizedFrom == inputFrom &&
//                                    normalizedTo == inputTo &&
//                                    normalizedDate == inputDate &&
//                                    normalizedTypeClass == inputTypeClass
//                                ) {
//                                    // Kiểm tra số ghế trống
//                                    val reservedSeats = list.ReservedSeats
//                                        .split(",")
//                                        .filter { it.isNotBlank() }
//                                        .toSet()
//                                    val availableSeats = list.NumberSeat - reservedSeats.size
//                                    if (availableSeats >= numPassenger) {
//                                        lists.add(list)
//                                        println("Flight [$index] added: ${list.AirlineName}, Seats available=$availableSeats")
//                                    } else {
//                                        println("Flight [$index] excluded: Not enough seats (available=$availableSeats, required=$numPassenger)")
//                                    }
//                                } else {
//                                    println("Flight [$index] not matched: From='$normalizedFrom' vs '$inputFrom', To='$normalizedTo' vs '$inputTo', Date='$normalizedDate' vs '$inputDate', TypeClass='$normalizedTypeClass' vs '$inputTypeClass'")
//                                }
//                            } else {
//                                println("Failed to parse FlightModel at index $index: ${childSnapshot.key}")
//                            }
//                        } catch (e: Exception) {
//                            println("Error parsing FlightModel at index $index: ${e.message}")
//                        }
//                    }
//                    println("Filtered flights loaded: ${lists.size} items")
//                } else {
//                    println("Flights node is empty or does not exist")
//                }
//                listData.value = lists
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                println("Firebase error in loadFiltered: ${error.message}")
//                listData.value = mutableListOf()
//            }
//        })
//        return listData
//    }
//}

package com.example.ticketbookingapp.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ticketbookingapp.Domain.FlightModel
import com.example.ticketbookingapp.Domain.LocationModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainRepository {
    private val firebaseDatabase = FirebaseDatabase.getInstance()

    fun loadLocation(): LiveData<MutableList<LocationModel>> {
        val listData = MutableLiveData<MutableList<LocationModel>>()
        val ref = firebaseDatabase.getReference("Locations")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<LocationModel>()
                if ( snapshot.exists()) {
                    for (childSnapshot in snapshot.children) {
                        try {
                            val item = childSnapshot.getValue(LocationModel::class.java)
                            item?.let {
                                list.add(it)
                            } ?: run {
                                println("Failed to parse LocationModel: ${childSnapshot.key}")
                            }
                        } catch (e: Exception) {
                            println("Error parsing LocationModel: ${e.message}")
                        }
                    }
                    println("Locations loaded: ${list.size} items")
                    listData.value = list
                } else {
                    println("Locations node is empty or does not exist")
                    listData.value = mutableListOf()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                println("Firebase error in loadLocation: ${error.message}")
                listData.value = mutableListOf()
            }
        })
        return listData
    }

    fun loadFiltered(
        from: String,
        to: String,
        departureDate: String,
        typeClass: String,
        numPassenger: Int = 1
    ): LiveData<MutableList<FlightModel>> {
        val listData = MutableLiveData<MutableList<FlightModel>>()
        val ref = firebaseDatabase.getReference("Flights")
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<FlightModel>()
                println("Query snapshot exists: ${snapshot.exists()}, children count: ${snapshot.childrenCount}")
                if (snapshot.exists()) {
                    snapshot.children.forEachIndexed { index, childSnapshot ->
                        try {
                            val list = childSnapshot.getValue(FlightModel::class.java)
                            if (list != null) {
                                // Log dữ liệu thô và dữ liệu đã phân tích
                                println("Flight [$index] raw data: ${childSnapshot.value}")
                                println("Flight [$index] parsed: From='${list.From}', To='${list.To}', Date='${list.Date}', TypeClass='${list.TypeClass}', NumberSeat=${list.NumberSeat}, ReservedSeats='${list.ReservedSeats}'")
                                // Chuẩn hóa chuỗi để so sánh
                                val normalizedFrom = list.From.trim().replace("\\s+".toRegex(), " ").lowercase()
                                val normalizedTo = list.To.trim().replace("\\s+".toRegex(), " ").lowercase()
                                val normalizedDate = list.Date.trim().replace("\\s+".toRegex(), " ").lowercase()
                                val normalizedTypeClass = list.TypeClass.trim().replace("\\s+".toRegex(), " ").lowercase()
                                val inputFrom = from.trim().replace("\\s+".toRegex(), " ").lowercase()
                                val inputTo = to.trim().replace("\\s+".toRegex(), " ").lowercase()
                                val inputDate = departureDate.trim().replace("\\s+".toRegex(), " ").lowercase()
                                val inputTypeClass = typeClass.trim().replace("\\s+".toRegex(), " ").lowercase()

                                if (normalizedFrom == inputFrom &&
                                    normalizedTo == inputTo &&
                                    normalizedDate == inputDate &&
                                    normalizedTypeClass == inputTypeClass
                                ) {
                                    val reservedSeats = list.ReservedSeats
                                        .split(",")
                                        .filter { it.isNotBlank() }
                                        .toSet()
                                    val availableSeats = list.NumberSeat - reservedSeats.size
                                    if (availableSeats >= numPassenger) {
                                        lists.add(list)
                                        println("Flight [$index] added: ${list.AirlineName}, Seats available=$availableSeats")
                                    } else {
                                        println("Flight [$index] excluded: Not enough seats (available=$availableSeats, required=$numPassenger)")
                                    }
                                } else {
                                    println("Flight [$index] not matched: From='$normalizedFrom' vs '$inputFrom', To='$normalizedTo' vs '$inputTo', Date='$normalizedDate' vs '$inputDate', TypeClass='$normalizedTypeClass' vs '$inputTypeClass'")
                                }
                            } else {
                                println("Failed to parse FlightModel at index $index: ${childSnapshot.key}")
                            }
                        } catch (e: Exception) {
                            println("Error parsing FlightModel at index $index: ${e.message}")
                        }
                    }
                    println("Filtered flights loaded: ${lists.size} items")
                } else {
                    println("Flights node is empty or does not exist")
                }
                listData.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                println("Firebase error in loadFiltered: ${error.message}")
                listData.value = mutableListOf()
            }
        })
        return listData
    }
}