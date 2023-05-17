package com.example.esemkalibrary.feature_myprofile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.esemkalibrary.feature_myprofile.data.BorrowDetail

@Composable
fun ProfileBorrowingCard(
    modifier: Modifier = Modifier,
    borrowDetail: BorrowDetail,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .background(shape = RoundedCornerShape(10), color = Color.White)
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick() }
        ,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier) {
            Text("${borrowDetail.startDate} - ${borrowDetail.endDate}")
            Text("${borrowDetail.qty} Book${if (borrowDetail.qty != 1) "s" else ""}")
        }
        Text(borrowDetail.status)
    }
}