package com.example.esemkalibrary.feature_mycart.ui

import android.widget.CalendarView
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import java.time.LocalDate
import java.time.ZoneId

@Composable
fun LibraryCalendarView(onDateSelected: (LocalDate) -> Unit) {
    AndroidView(
        modifier = Modifier.wrapContentSize(),
        factory = { context ->
            CalendarView(context)
        },
        update = { view ->
            view.minDate = LocalDate.of(2023, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
            view.maxDate = LocalDate.of(2023, 12, 31).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
            view.setOnDateChangeListener { _, year, month, dayOfMonth ->
                onDateSelected(
                    LocalDate
                        .now()
                        .withYear(year)
                        .withMonth(month + 1)
                        .withDayOfMonth(dayOfMonth)
                )
            }
        }
    )
}