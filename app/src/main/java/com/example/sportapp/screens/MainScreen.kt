package com.example.sportapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.sportapp.R
import com.example.sportapp.model.Result
import com.example.sportapp.screens.mainscreendetails.ButtonToWebsite
import com.example.sportapp.screens.mainscreendetails.CustomDivider
import com.example.sportapp.screens.mainscreendetails.DefaultImage
import com.example.sportapp.screens.mainscreendetails.ErrorText
import com.example.sportapp.screens.mainscreendetails.NoMatchesText
import com.example.sportapp.screens.mainscreendetails.QuarterText
import com.example.sportapp.screens.mainscreendetails.TextForNoMatches
import com.example.sportapp.screens.mainscreendetails.VSImage
import com.example.sportapp.ui.theme.LightGrey
import com.example.sportapp.ui.theme.LightestGrey
import com.example.sportapp.ui.theme.MainDarkGrey
import com.example.sportapp.ui.theme.MainMediumGrey
import com.example.sportapp.ui.theme.MainOrange
import com.example.sportapp.viewmodel.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel) {

    // For refreshing data by pulling
    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }
    fun refresh() = refreshScope.launch {
        refreshing = true
        viewModel.getData(viewModel.currentDate.value ?: "2018-07-12")
        delay(1500)
        refreshing = false
    }

    val state = rememberPullRefreshState(refreshing, ::refresh)

    // Main data for ui
    val dataList = remember {
        mutableStateOf(listOf<Result>())
    }

    // Text for error
    val errorMessage = rememberSaveable {
        mutableStateOf("")
    }

    // Looks if no right data has been passed to display a message
    val lookInside = remember {
        mutableStateOf(false)
    }

    viewModel.data.observe(LocalLifecycleOwner.current) {
        dataList.value = it.result
    }

    viewModel.errorMessageForDisplay.observe(LocalLifecycleOwner.current) {
        if (it.isNotEmpty()) {
            if (it.startsWith("Unable")) {
                errorMessage.value = "No Internet connection"
            } else {
                errorMessage.value = "API error"
            }
        } else {
            errorMessage.value = ""
        }
    }



    Box() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .pullRefresh(state)
        ) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {

                if (errorMessage.value.isNotEmpty()) {
                    item {
                        ErrorText(errorMessage.value)
                    }
                }


                    item {
                        NoMatchesText()
                    }


                dataList.value.forEach {
                    if (it.eventStatus == "Finished") {

                        lookInside.value = true

                        item {
                            Card(
                                modifier = Modifier.padding(8.dp),
                                shape = RoundedCornerShape(30.dp),
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 10.dp
                                ),
                                colors = CardDefaults.cardColors(
                                    containerColor = LightestGrey,
                                ),
                            ) {

                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalArrangement = Arrangement.SpaceAround,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {

                                        if (it.eventHomeTeamLogo == null) {
                                            DefaultImage()
                                        } else {
                                            SubcomposeAsyncImage(
                                                contentScale = ContentScale.Crop,
                                                modifier = Modifier
                                                    .clip(CircleShape)
                                                    .width(60.dp),
                                                model = it.eventHomeTeamLogo,
                                                contentDescription = stringResource(R.string.main_screen_first_desc)
                                            ) {
                                                val state = painter.state
                                                if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                                                    CircularProgressIndicator()
                                                } else {
                                                    SubcomposeAsyncImageContent()
                                                }
                                            }
                                        }

                                        Text(
                                            modifier = Modifier.width(75.dp),
                                            text = it.eventHomeTeam
                                                ?: stringResource(R.string.error_not_found),
                                            fontSize = 15.sp,
                                            color = MainDarkGrey,
                                            textAlign = TextAlign.Center
                                        )

                                        VSImage()

                                        Text(

                                            modifier = Modifier.width(75.dp),
                                            text = it.eventAwayTeam
                                                ?: stringResource(R.string.error_not_found),
                                            fontSize = 15.sp,
                                            color = MainDarkGrey,
                                            textAlign = TextAlign.Center
                                        )


                                        if (it.eventAwayTeamLogo == null) {
                                            DefaultImage()
                                        } else {
                                            SubcomposeAsyncImage(
                                                contentScale = ContentScale.Crop,
                                                modifier = Modifier
                                                    .clip(CircleShape)
                                                    .width(60.dp),
                                                model = it.eventAwayTeamLogo,
                                                contentDescription = stringResource(R.string.main_screen_first_desc)
                                            ) {
                                                val state = painter.state
                                                if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                                                    CircularProgressIndicator()
                                                } else {
                                                    SubcomposeAsyncImageContent()
                                                }
                                            }
                                        }

                                    }

                                    CustomDivider(8, 8, 4, 4)

                                    Text(
                                        text = it.eventDate + " " + it.eventTime,
                                        fontSize = 13.sp,
                                        color = MainMediumGrey
                                    )

                                    Text(
                                        text = it.eventFinalResult
                                            ?: stringResource(R.string.error_not_found),
                                        color = MainDarkGrey,
                                        fontSize = 32.sp
                                    )

                                    CustomDivider(top = 4, bottom = 4)

                                    val textClicked = rememberSaveable {
                                        mutableStateOf(false)
                                    }

                                    val clickableText = rememberSaveable {
                                        mutableStateOf(R.string.main_screen_details_close)
                                    }

                                    if (textClicked.value) {

                                        Text(
                                            text = it.leagueName
                                                ?: stringResource(R.string.error_not_found),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(bottom = 4.dp),
                                            textAlign = TextAlign.Center,
                                            fontSize = 15.sp,
                                            color = MainDarkGrey
                                        )

                                        QuarterText(1)

                                        Text(
                                            text = it.scores?.Quarter1?.get(0)?.scoreHome + " — " + it.scores?.Quarter1?.get(
                                                0
                                            )?.scoreAway,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(bottom = 8.dp),
                                            textAlign = TextAlign.Center,
                                            fontSize = 22.sp,
                                            color = MainDarkGrey
                                        )

                                        QuarterText(2)

                                        Text(
                                            text = it.scores?.Quarter2?.get(0)?.scoreHome + " — " + it.scores?.Quarter2?.get(
                                                0
                                            )?.scoreAway,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(bottom = 8.dp),
                                            textAlign = TextAlign.Center,
                                            fontSize = 22.sp,
                                            color = MainDarkGrey
                                        )

                                        QuarterText(3)

                                        Text(
                                            text = it.scores?.Quarter3?.get(0)?.scoreHome + " — " + it.scores?.Quarter3?.get(
                                                0
                                            )?.scoreAway,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(bottom = 8.dp),
                                            textAlign = TextAlign.Center,
                                            fontSize = 22.sp,
                                            color = MainDarkGrey
                                        )

                                        QuarterText(4)

                                        Text(
                                            text = it.scores?.Quarter4?.get(0)?.scoreHome + " — " + it.scores?.Quarter4?.get(
                                                0
                                            )?.scoreAway,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(bottom = 8.dp),
                                            textAlign = TextAlign.Center,
                                            fontSize = 22.sp,
                                            color = MainDarkGrey
                                        )


                                    }

                                    ClickableText(
                                        text = AnnotatedString(stringResource(clickableText.value)),
                                        style = TextStyle(
                                            fontSize = 13.sp,
                                            color = MainMediumGrey,
                                        ),
                                        onClick = {
                                            if (clickableText.value == R.string.main_screen_details_close) {
                                                clickableText.value =
                                                    R.string.main_screen_details_open
                                            } else {
                                                clickableText.value =
                                                    R.string.main_screen_details_close
                                            }
                                            textClicked.value = !textClicked.value
                                        }
                                    )


                                }
                            }
                        }
                    }

                }

                if (!lookInside.value && errorMessage.value.isEmpty()) {
                    item {
                        TextForNoMatches()
                    }
                }

            }


            ButtonToWebsite(navController)

        }

        PullRefreshIndicator(
            refreshing,
            state,
            modifier = Modifier.align(Alignment.TopCenter),
            contentColor = MainOrange,
            backgroundColor = LightGrey
        )

    }

}
