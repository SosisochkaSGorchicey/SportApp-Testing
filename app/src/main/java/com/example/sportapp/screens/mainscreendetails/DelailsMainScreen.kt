package com.example.sportapp.screens.mainscreendetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sportapp.R
import com.example.sportapp.ui.theme.ErrorColor
import com.example.sportapp.ui.theme.LightDirtyOrange
import com.example.sportapp.ui.theme.MainDarkGrey
import com.example.sportapp.ui.theme.MainMediumGrey
import com.example.sportapp.ui.theme.MainOrange

@Composable
fun ErrorText(message: String) {
    Text(
        text = message,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        color = ErrorColor,
        fontSize = 16.sp
    )
}

@Composable
fun NoMatchesText() {
    Text(
        text = stringResource(R.string.main_screen_header),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        fontSize = 20.sp,
        color = MainDarkGrey,
        textAlign = TextAlign.Center
    )
}

@Composable
fun DefaultImage() {
    Image(
        painter = painterResource(id = R.drawable.app_icon_small),
        contentDescription = stringResource(R.string.main_screen_first_desc),
        modifier = Modifier
            .size(60.dp)
    )
}

@Composable
fun VSImage() {
    Image(
        painter = painterResource(id = R.drawable.vs),
        contentDescription = stringResource(R.string.main_screen_vs_desc),
        modifier = Modifier
            .size(40.dp),
        alignment = Alignment.Center
    )
}

@Composable
fun CustomDivider(start: Int = 0, end: Int = 0, top: Int = 0, bottom: Int = 0) {
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = start.dp,
                end = end.dp,
                top = top.dp,
                bottom = bottom.dp
            ),
        color = LightDirtyOrange,
        thickness = 1.dp
    )
}

@Composable
fun QuarterText(num: Int) {
    Text(
        text = stringResource(id = R.string.main_screen_quarter) + " $num",
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        fontSize = 15.sp,
        color = MainMediumGrey
    )
}

@Composable
fun TextForNoMatches() {
    Text(
        text = stringResource(R.string.no_data),
        modifier = Modifier.fillMaxSize(),
        fontSize = 18.sp,
        textAlign = TextAlign.Center,
        color = MainMediumGrey
    )
}

@Composable
fun ButtonToWebsite(navController: NavController) {
    Button(
        onClick = {
            navController.navigate("web_screen")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp),
        shape = RoundedCornerShape(50),
        elevation = ButtonDefaults.elevation(10.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = MainOrange)

    ) {
        Text(
            text = stringResource(R.string.button_see_website),
            fontSize = 18.sp,
            color = Color.White
        )
    }
}

