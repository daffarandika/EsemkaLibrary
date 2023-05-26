package com.example.esemkalibrary.feature_myprofile.ui

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.core.net.toFile
import com.example.esemkalibrary.R

@Suppress("DEPRECATION")
@Composable
fun ImagePicker(modifier: Modifier = Modifier) {

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val context = LocalContext.current
    var bitmap by remember {
        mutableStateOf<Bitmap?> (null)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {
            imageUri = it
        }
    )

    imageUri?.toFile()

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = if (imageUri == null) painterResource(id = R.drawable.no_image) else BitmapPainter(bitmap!!.asImageBitmap()) ,
            contentDescription = "image"
        )
    }

    Button(onClick = { launcher.launch("image/*") }) {
        Text("pick image")
    }
}

