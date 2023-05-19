package com.example.esemkalibrary.feature_mycart.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.esemkalibrary.core.components.theme.MudBrown
import com.example.esemkalibrary.core.components.theme.SandBrown
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DatePickerDialog(onDateSelected: (LocalDate) -> Unit, onDismissRequest: () -> Unit) {
    var selDate by remember {
        mutableStateOf(LocalDate.now())
    }

    Dialog(onDismissRequest = {onDismissRequest()}, properties = DialogProperties()) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .background(color = SandBrown, shape = RoundedCornerShape(5))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        shape = RoundedCornerShape(topStartPercent = 5, topEndPercent = 5),
                        color = MudBrown
                    )
                    .padding(8.dp)
            ) {
                Text(text = "Select Date", color = Color.White)
                Text(
                    text = selDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")),
                    style = MaterialTheme.typography.h4,
                    color = Color.White,
                )
                Spacer(Modifier.size(16.dp))
            }

            LibraryCalendarView(onDateSelected = { selDate = it })

            Row(modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Text(
                    text = "Cancel", modifier = Modifier.clickable{
                        onDismissRequest()
                    }, fontSize = 14.sp, fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.size(8.dp))
                Text(
                    text = "OK", modifier = Modifier.clickable{
                        onDateSelected(selDate)
                        onDismissRequest()
                    }, fontSize = 14.sp, fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
