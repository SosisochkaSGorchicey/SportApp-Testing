package com.example.sportapp.screens

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.sportapp.R
import com.example.sportapp.ui.theme.MainOrange
import com.example.sportapp.viewmodel.MainViewModel
import com.onesignal.OneSignal


@Composable
fun WebScreen(navController: NavController, viewModel: MainViewModel) {

    val mUrl = "https://www.sports.ru"

    // Do we need to show message from OneSignal
    val toShow = rememberSaveable {
        mutableStateOf(false)
    }

    viewModel.toShowOneSignal.observe(LocalLifecycleOwner.current) {
        toShow.value = it
    }

    if (toShow.value) {
        OneSignal.addTrigger("see_website", 1)
        viewModel.toShowOneSignal.value = false
    }


    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        AndroidView(
            modifier = Modifier.weight(1f),
            factory = {
                WebView(it).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    webViewClient = WebViewClient()
                    loadUrl(mUrl)
                }
            },
            update = {
                it.loadUrl(mUrl)
            }
        )

        Button(
            onClick = {
                navController.navigate("main_screen") {
                    popUpTo(0)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
            shape = RoundedCornerShape(50),
            elevation = ButtonDefaults.elevation(10.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = MainOrange)
        ) {
            Text(
                text = stringResource(R.string.button_go_back),
                fontSize = 18.sp,
                color = Color.White
            )
        }
    }
}