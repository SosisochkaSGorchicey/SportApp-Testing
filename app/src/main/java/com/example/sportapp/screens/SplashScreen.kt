package com.example.sportapp.screens

import android.content.pm.ActivityInfo
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sportapp.LockScreenOrientation
import com.example.sportapp.R
import com.example.sportapp.ui.theme.LightGrey
import com.example.sportapp.ui.theme.LightOrange
import com.example.sportapp.ui.theme.MainOrange
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                }
            )
        )

        delay(3000L)

        navController.navigate("main_screen") {
            popUpTo(0)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.app_icon),
            contentDescription = "logo",
            modifier = Modifier
                .size(150.dp)
                .scale(scale.value)
        )

        Text(
            modifier = Modifier.padding(10.dp),
            text = stringResource(R.string.app_name),
            color = MainOrange,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
                .height(3.dp),
            color = LightOrange,
            trackColor = LightGrey
        )
    }
}