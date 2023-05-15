package com.example.esemkalibrary.feature_forum.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.esemkalibrary.R
import com.example.esemkalibrary.core.components.theme.Grey

@Composable
fun PostCard(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(1))
            .border(BorderStroke(width = 1.dp, color = Grey.copy(alpha = 0.5f)),
                shape = RoundedCornerShape(1))
            .background(color = Color.White)
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.photoprofiledefault),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .clip(CircleShape)
                .border(BorderStroke(width = 1.dp, color = Grey.copy(0.5f)), shape = CircleShape)
                .size(64.dp)
        )
        Column(modifier = Modifier, Arrangement.SpaceAround){
            Text(
                text  = "Title",
                fontSize = 18.sp
            )
            Text(
                text  = "Author - 10 Sept 2023",
            )
            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus tempus, risus id feugiat suscipit, urna odio luctus dui, a porttitor dui odio non magna. Maecenas hendrerit tellus eget mi suscipit, eu lobortis quam suscipit. Curabitur sit amet justo ut ex condimentum mattis sed eget risus. Fusce sit amet viverra purus, ut porttitor lacus. Morbi mauris nibh, mollis id est ut, lacinia facilisis sapien. Nam eleifend orci purus, eu eleifend sem ultrices sit amet. Sed risus neque, fermentum in nisi vel, condimentum volutpat massa. Nam ut tristique urna. Praesent hendrerit aliquam ante, a dictum enim aliquam eu. Nulla id dolor ut orci porttitor varius sed et augue. Nam finibus sed erat vel fringilla. Nulla quis libero feugiat, pretium nunc pulvinar, gravida tortor. Nam sed ullamcorper est, ut elementum est. Mauris ut tincidunt justo, quis lacinia quam. Integer non fermentum augue."
            )
        }
    }
}