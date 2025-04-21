package com.example.ticketbookingapp.Activities.Dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ticketbookingapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownList(
    items: List<String>,
    loadingIcon: Painter,
    hint: String = "",
    showLocationLoading: Boolean,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier // Thêm tham số modifier
) {
    var selectedItem by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier // Áp dụng modifier vào ExposedDropdownMenuBox
    ) {
        if (showLocationLoading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp) // Đồng bộ chiều cao với DatePickerItem
                    .background(
                        colorResource(R.color.lightPurple),
                        shape = RoundedCornerShape(8.dp) // Sử dụng 8.dp để đồng bộ
                    )
                    .padding(top = 8.dp), // Đồng bộ padding
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            OutlinedTextField(
                value = selectedItem,
                onValueChange = { selectedItem = it },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp) // Đồng bộ chiều cao với DatePickerItem
                    .clip(RoundedCornerShape(8.dp)) // Sử dụng 8.dp để đồng bộ
                    .menuAnchor()
                    .padding(top = 8.dp), // Đồng bộ padding
                placeholder = {
                    Text(
                        text = hint,
                        color = Color.Black,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                leadingIcon = {
                    Image(
                        painter = loadingIcon,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp) // Đồng bộ kích thước icon
                    )
                },
                textStyle = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    containerColor = colorResource(R.color.lightPurple)
                )
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                items.forEach { item ->
                    DropdownMenuItem(
                        text = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = item,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                            }
                        },
                        onClick = {
                            selectedItem = item
                            expanded = false
                            onItemSelected(item)
                        }
                    )
                }
            }
        }
    }
}