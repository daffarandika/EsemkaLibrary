package com.example.esemkalibrary.feature_main.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.esemkalibrary.core.components.theme.MudBrown
import com.example.esemkalibrary.core.components.theme.SandBrown

@Composable
fun MainBottomBar(
    modifier: Modifier = Modifier,
    items: List<String>,
    onActiveIndexChange: (Int) -> Unit,
) {
    Row(modifier.fillMaxWidth().background(SandBrown)) {
        items.forEachIndexed { index, item ->
            BottomTabIndicator(
                text = item,
                onClick = {
                    onActiveIndexChange(index)
                },
                modifier = Modifier.weight((100/items.size).toFloat())
            )
        }
    }
}