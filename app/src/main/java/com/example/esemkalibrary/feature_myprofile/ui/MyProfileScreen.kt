package com.example.esemkalibrary.feature_myprofile.ui

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toFile
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.esemkalibrary.MainActivity
import com.example.esemkalibrary.R
import com.example.esemkalibrary.core.components.LibraryButton
import com.example.esemkalibrary.core.navigation.Screen
import com.example.esemkalibrary.core.utils.viewModelFactory
import com.example.esemkalibrary.feature_myprofile.data.BorrowDetail
import com.example.esemkalibrary.feature_myprofile.data.User
import java.io.File
import java.io.FileInputStream

fun ContentResolver.getFileName(fileUri: Uri): String {

    var name = ""
    val returnCursor = this.query(fileUri, null, null, null, null)
    if (returnCursor != null) {
        val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        returnCursor.moveToFirst()
        name = returnCursor.getString(nameIndex)
        returnCursor.close()
    }

    return name
}

private fun getRealPathFromURI(context: Context, contentURI: Uri): String {
    val result: String
    val cursor: Cursor? = context.contentResolver.query(contentURI, null, null, null, null)
    if (cursor == null) {
        result = contentURI.getPath().toString()
    } else {
        cursor.moveToFirst()
        val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
        result = cursor.getString(idx)
        cursor.close()
    }
    return result
}

@Suppress("DEPRECATION")
@Composable
fun MyProfileScreen(modifier: Modifier = Modifier, navController: NavHostController) {
    val context = LocalContext.current
    val viewModel: MyProfileViewModel = viewModel(factory = viewModelFactory {
        MyProfileViewModel(LocalContext.current)
    })
    val uiState by viewModel.uiState.collectAsState()
    val token by viewModel.token.collectAsState(initial = "")
    val user by viewModel.getUserDetail(token).collectAsState(initial = User())
    val borrowingHistory by viewModel.getCartHistory(token).collectAsState(initial = emptyList())
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }



    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let {
                val parcelFileDescriptor = context.contentResolver.openFileDescriptor(it, "r", null)
                val inputStream = context.contentResolver.openInputStream(it)

                inputStream?.let { stream ->
                    val file = File(context.cacheDir, context.contentResolver.getFileName(uri))
                    file.outputStream().use { output ->
                        val buffer = ByteArray(4 * 1024) // Adjust buffer size as needed
                        var bytesRead: Int
                        while (stream.read(buffer).also { bytesRead = it } != -1) {
                            output.write(buffer, 0, bytesRead)
                        }
                        output.flush()
                    }
                    viewModel.updateProfilePhoto(file, token)
                }

                inputStream?.close()
            }
        }
    )
    val ctx = LocalContext.current
    viewModel.updateName(user.name)
    viewModel.updateEmail(user.email)
//    viewModel.updateProfilePhoto(user.profilePhoto)
    viewModel.updateCartHistory(borrowingHistory)
    Log.e("TAG", "MyProfileScreen: isNull ${user.profilePhoto == null}", )
    LazyColumn(
        modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Column(modifier
                .padding(top = 16.dp)
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = if (user.profilePhoto == null) painterResource(id = R.drawable.photoprofiledefault) else BitmapPainter(user.profilePhoto!!),
                    contentDescription =  "Profile photo",
                    modifier = modifier
                        .clip(shape = CircleShape)
                        .size(256.dp),
                    contentScale = ContentScale.FillWidth
                )
                LibraryButton(onClick = {
                    launcher.launch("image/*")
                }, text = "Upload Photo")
                Text(
                    text = uiState.name,
                    fontSize = 18.sp,
                )
                Text(
                    text = uiState.email,
                    fontSize = 12.sp,
                )
                LibraryButton(
                    onClick = {
                        (context as? MainActivity)?.restartApp()
                    },
                    modifier = modifier.fillMaxWidth(),
                    text = "Logout"
                )
                Text(
                    modifier = Modifier.align(Alignment.Start),
                    text = "My Borrowing History: "
                )
            }
        }
        items(uiState.borrowingHistory) { borrow ->
            ProfileBorrowingCard(cartItem = borrow, onClick =  {
                navController.navigate(route = Screen.BorrowingDetail.passBorrowingId(borrow.id))
            })
        }
    }
}