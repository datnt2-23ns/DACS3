//package com.example.ticketbookingapp.Activities.Dashboard
//
//import android.widget.Toast
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.material.BottomAppBar
//import androidx.compose.material.BottomNavigationItem
//import androidx.compose.material.Icon
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.painter.Painter
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import com.example.ticketbookingapp.R
//
//data class BottomMenuItem(
//    val label: String,
//    val icon: Painter
//)
//
//@Composable
//fun prepareBottomMenu(): List<BottomMenuItem> {
//    return listOf(
//        BottomMenuItem(label = "Home", icon = painterResource(R.drawable.bottom_btn1)),
//        BottomMenuItem(label = "Ticket status", icon = painterResource(R.drawable.bottom_btn2)),
////        BottomMenuItem(label = "Favorite", icon = painterResource(R.drawable.bottom_btn3)),
//        BottomMenuItem(label = "Information", icon = painterResource(R.drawable.bottom_btn4))
//    )
//}
//
//@Composable
//@Preview
//fun MyBottomBar() {
//    val bottomMenuItemsList = prepareBottomMenu()
//    val context = LocalContext.current
//    var selectedItem by remember { mutableStateOf("Home") }
//
//    BottomAppBar(
//        backgroundColor = colorResource(R.color.darkPurple),
//        elevation = 3.dp
//    ) {
//        bottomMenuItemsList.forEach { bottomMenuItem ->
//            BottomNavigationItem(
//                selected = (selectedItem == bottomMenuItem.label),
//                onClick = {
//                    selectedItem = bottomMenuItem.label
//                    if (bottomMenuItem.label == "Cart") {
//                        // TODO: Handle Cart click here
//                    } else {
//                        Toast.makeText(context, bottomMenuItem.label, Toast.LENGTH_SHORT).show()
//                    }
//                },
//                icon = {
//                    Icon(
//                        painter = bottomMenuItem.icon,
//                        contentDescription = null,
//                        tint = colorResource(R.color.orange),
//                        modifier = Modifier
//                            .padding(top = 8.dp)
//                            .size(20.dp)
//                    )
//                }
//            )
//        }
//    }
//}

// Màu mặc định icon
//package com.example.ticketbookingapp.Activities.Dashboard
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.size
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//import com.example.ticketbookingapp.Activities.BookingHistory.BookingHistoryActivity
//import com.example.ticketbookingapp.Activities.Profile.ProfileActivity
//import com.example.ticketbookingapp.Domain.UserModel
//import com.example.ticketbookingapp.R
//
//@Composable
//fun MyBottomBar(user: UserModel) {
//    val context = LocalContext.current
//
//    NavigationBar(
//        modifier = Modifier.background(colorResource(R.color.darkPurple2)),
//        containerColor = colorResource(R.color.darkPurple2)
//    ) {
//        NavigationBarItem(
//            selected = false,
//            onClick = {
//                val intent = DashboardActivity.newIntent(context).apply {
//                    putExtra("user", user)
//                }
//                context.startActivity(intent)
//            },
//            icon = {
//                Icon(
//                    painter = painterResource(R.drawable.bottom_btn1),
//                    contentDescription = null,
//                    modifier = Modifier.size(24.dp)
//                )
//            },
//            colors = NavigationBarItemDefaults.colors(
//                selectedIconColor = Color.Unspecified,
//                unselectedIconColor = Color.Unspecified
//            )
//        )
//        NavigationBarItem(
//            selected = false,
//            onClick = {
//                val intent = BookingHistoryActivity.newIntent(context).apply {
//                    putExtra("user", user)
//                }
//                context.startActivity(intent)
//            },
//            icon = {
//                Icon(
//                    painter = painterResource(R.drawable.bottom_btn2),
//                    contentDescription = null,
//                    modifier = Modifier.size(24.dp)
//                )
//            },
//            colors = NavigationBarItemDefaults.colors(
//                selectedIconColor = Color.Unspecified,
//                unselectedIconColor = Color.Unspecified
//            )
//        )
////        NavigationBarItem(
////            selected = false,
////            onClick = { /* TODO: Thêm sự kiện cho bottom_btn3 */ },
////            icon = {
////                Icon(
////                    painter = painterResource(R.drawable.bottom_btn3),
////                    contentDescription = null,
////                    modifier = Modifier.size(24.dp)
////                )
////            },
////            colors = NavigationBarItemDefaults.colors(
////                selectedIconColor = Color.Unspecified,
////                unselectedIconColor = Color.Unspecified
////            )
////        )
//        NavigationBarItem(
//            selected = false,
//            onClick = {
//                val intent = ProfileActivity.newIntent(context).apply {
//                    putExtra("user", user)
//                }
//                context.startActivity(intent)
//            },
//            icon = {
//                Icon(
//                    painter = painterResource(R.drawable.bottom_btn4),
//                    contentDescription = null,
//                    modifier = Modifier.size(24.dp)
//                )
//            },
//            colors = NavigationBarItemDefaults.colors(
//                selectedIconColor = Color.Unspecified,
//                unselectedIconColor = Color.Unspecified
//            )
//        )
//    }
//}

//Nguyên mẫu
//package com.example.ticketbookingapp.Activities.Dashboard
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.size
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//import com.example.ticketbookingapp.Activities.BookingHistory.BookingHistoryActivity
//import com.example.ticketbookingapp.Activities.Profile.ProfileActivity
//import com.example.ticketbookingapp.Domain.UserModel
//import com.example.ticketbookingapp.R
//
//@Composable
//fun MyBottomBar(user: UserModel) {
//    val context = LocalContext.current
//
//    NavigationBar(
//        modifier = Modifier.background(colorResource(R.color.darkPurple2)),
//        containerColor = colorResource(R.color.darkPurple2)
//    ) {
//        NavigationBarItem(
//            selected = false,
//            onClick = {
//                val intent = DashboardActivity.newIntent(context).apply {
//                    putExtra("user", user)
//                }
//                context.startActivity(intent)
//            },
//            icon = {
//                Icon(
//                    painter = painterResource(R.drawable.bottom_btn1),
//                    contentDescription = null,
//                    modifier = Modifier.size(24.dp),
//                    tint = Color.White
//                )
//            }
//        )
//        NavigationBarItem(
//            selected = false,
//            onClick = {
//                val intent = BookingHistoryActivity.newIntent(context).apply {
//                    putExtra("user", user)
//                }
//                context.startActivity(intent)
//            },
//            icon = {
//                Icon(
//                    painter = painterResource(R.drawable.bottom_btn2),
//                    contentDescription = null,
//                    modifier = Modifier.size(24.dp),
//                    tint = Color.White
//                )
//            }
//        )
////        NavigationBarItem(
////            selected = false,
////            onClick = { /* TODO: Thêm sự kiện cho bottom_btn3 */ },
////            icon = {
////                Icon(
////                    painter = painterResource(R.drawable.bottom_btn3),
////                    contentDescription = null,
////                    modifier = Modifier.size(24.dp),
////                    tint = Color.White
////                )
////            }
////        )
//        NavigationBarItem(
//            selected = false,
//            onClick = {
//                val intent = ProfileActivity.newIntent(context).apply {
//                    putExtra("user", user)
//                }
//                context.startActivity(intent)
//            },
//            icon = {
//                Icon(
//                    painter = painterResource(R.drawable.bottom_btn4),
//                    contentDescription = null,
//                    modifier = Modifier.size(24.dp),
//                    tint = Color.White
//                )
//            }
//        )
//    }
//}

package com.example.ticketbookingapp.Activities.Dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ticketbookingapp.Activities.BookingHistory.BookingHistoryActivity
import com.example.ticketbookingapp.Activities.Profile.ProfileActivity
import com.example.ticketbookingapp.Domain.UserModel
import com.example.ticketbookingapp.R

@Composable
fun MyBottomBar(user: UserModel, currentScreen: String) {
    val context = LocalContext.current

    NavigationBar(
        modifier = Modifier.background(colorResource(R.color.darkPurple2)),
        containerColor = colorResource(R.color.darkPurple2)
    ) {
        // Dashboard (bottom_btn1)
        NavigationBarItem(
            selected = currentScreen == "Dashboard",
            onClick = {
                if (currentScreen != "Dashboard") {
                    val intent = DashboardActivity.newIntent(context).apply {
                        putExtra("user", user)
                    }
                    context.startActivity(intent)
                }
            },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.bottom_btn1),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = if (currentScreen == "Dashboard") Color.Yellow else Color.White
                )
            }
        )

        // Booking History (bottom_btn2)
        NavigationBarItem(
            selected = currentScreen == "BookingHistory",
            onClick = {
                if (currentScreen != "BookingHistory") {
                    val intent = BookingHistoryActivity.newIntent(context).apply {
                        putExtra("user", user)
                    }
                    context.startActivity(intent)
                }
            },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.bottom_btn2),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = if (currentScreen == "BookingHistory") Color.Yellow else Color.White
                )
            }
        )

        // Profile (bottom_btn4)
        NavigationBarItem(
            selected = currentScreen == "Profile",
            onClick = {
                if (currentScreen != "Profile") {
                    val intent = ProfileActivity.newIntent(context).apply {
                        putExtra("user", user)
                    }
                    context.startActivity(intent)
                }
            },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.bottom_btn4),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = if (currentScreen == "Profile") Color.Yellow else Color.White
                )
            }
        )
    }
}