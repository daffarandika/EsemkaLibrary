package com.example.esemkalibrary.feature_myprofile.ui

import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.esemkalibrary.R
import com.example.esemkalibrary.core.components.LibraryButton
import com.example.esemkalibrary.core.navigation.Screen
import com.example.esemkalibrary.core.utils.viewModelFactory
import com.example.esemkalibrary.feature_myprofile.data.BorrowDetail
import com.example.esemkalibrary.feature_myprofile.data.User

@Suppress("DEPRECATION")
@Composable
fun MyProfileScreen(modifier: Modifier = Modifier, navController: NavHostController) {
    val viewModel: MyProfileViewModel = viewModel(factory = viewModelFactory {
        MyProfileViewModel(LocalContext.current)
    })
    val uiState = viewModel.uiState.collectAsState()
    val token = viewModel.token.collectAsState(initial = "")
    val user = viewModel.getUserDetail(token.value).collectAsState(initial = User())
    val borrowingHistory = viewModel.getCartHistory(token.value).collectAsState(initial = emptyList())
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {
            imageUri = it
        }
    )
    val ctx = LocalContext.current
    viewModel.updateName(user.value.name)
    viewModel.updateEmail(user.value.email)
    viewModel.updateProfilePhoto(user.value.profilePhoto)
    viewModel.updateCartHistory(borrowingHistory.value)
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
                    painter = if (uiState.value.profilePhoto == null) painterResource(id = R.drawable.photoprofiledefault) else BitmapPainter(uiState.value.profilePhoto!!),
                    contentDescription =  "Profile photo",
                    modifier = modifier
                        .size(256.dp)
                        .clip(shape = CircleShape)
                )
                LibraryButton(onClick = {
                    launcher.launch("image/*")
                    imageUri?.let {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                            viewModel.updateProfilePhoto(ImageDecoder.decodeBitmap(ImageDecoder.createSource(
                                ctx.contentResolver, it)).asImageBitmap())
                        } else {
                            viewModel.updateProfilePhoto(MediaStore.Images.Media.getBitmap(ctx.contentResolver, it).asImageBitmap())
                        }
                    }
                }, text = "Upload Photo")
                Text(
                    text = uiState.value.name,
                    fontSize = 18.sp,
                )
                Text(
                    text = uiState.value.email,
                    fontSize = 12.sp,
                )
                LibraryButton(onClick = { /*TODO*/ }, modifier = modifier.fillMaxWidth(), text = "Logout")
                Text(
                    modifier = Modifier.align(Alignment.Start),
                    text = "My Borrowing History: "
                )
            }
        }
        items(uiState.value.borrowingHistory) { borrow ->
            ProfileBorrowingCard(cartItem = borrow, onClick =  {
                navController.navigate(route = Screen.BorrowingDetail.passBorrowingId(borrow.id))
            })
        }
    }
}