package com.example.esemkalibrary.feature_bookdetail.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.esemkalibrary.R
import com.example.esemkalibrary.core.components.theme.DirtBrown
import com.example.esemkalibrary.core.components.theme.MudBrown
import com.example.esemkalibrary.core.components.theme.SandBrown

@Composable
fun BookDetailScreen(modifier: Modifier = Modifier, id: String) {
    Scaffold(
        modifier = modifier,
        backgroundColor = SandBrown,
        floatingActionButton = {
            FloatingActionButton(onClick = {
            },
                backgroundColor = DirtBrown,
                shape = RoundedCornerShape(5),
                modifier = Modifier
                    .fillMaxWidth(0.91f)
                    .padding(4.dp)
                ,
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation = 4.dp
                )
            ) {
                Text("Add to Cart")
            }
    }) {
        Column(
            modifier = modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Image(painterResource(id = R.drawable.default_img),
                "Book Image",
                modifier = Modifier.size(256.dp))
            Text(
                text = "Title Here",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
            Text("Author")
            Row(modifier.align(Alignment.Start)) {
                Text("ISBN-10: ", fontWeight = FontWeight.Bold)
                Text("8012211")
            }
            Row(modifier.align(Alignment.Start)) {
                Text("Publisher: ", fontWeight = FontWeight.Bold)
                Text("7 Seas")
            }
            Row(modifier.align(Alignment.Start)) {
                Text("Available: ", fontWeight = FontWeight.Bold)
                Text("91")
            }
            Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi ullamcorper pellentesque massa vel pharetra. Nunc rhoncus maximus lectus sed interdum. Curabitur ante sem, pellentesque in augue ac, fermentum consequat magna. Vivamus commodo feugiat massa, vitae finibus arcu dapibus at. Nam viverra tempus nisl, sed aliquet magna luctus sit amet. Duis vehicula congue imperdiet. Sed viverra blandit massa in fringilla. Aenean varius ac lorem congue blandit. Quisque porta lobortis mauris, tincidunt convallis eros ullamcorper vel. Etiam vel lacinia velit. Duis at mi in neque sollicitudin rutrum vitae sit amet nulla. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nam et orci dolor. Interdum et malesuada fames ac ante ipsum primis in faucibus. Vestibulum aliquam arcu lobortis urna vestibulum tincidunt. Fusce quis accumsan quam, non placerat est. Morbi lacus turpis, dignissim vel ornare in, ultrices sed felis. Praesent sed accumsan eros. Nam feugiat euismod commodo. Sed at ipsum et neque sollicitudin posuere ac non tellus. Fusce fringilla consectetur velit eu vestibulum. Etiam et elit quis ante dapibus tempor in a tortor. Nulla tincidunt justo a euismod auctor. Cras dapibus felis ante, vitae luctus elit aliquam in. Duis placerat aliquam lacus rutrum iaculis. Duis et neque viverra diam.")
            Spacer(modifier.size(64.dp))
        }
    }
}