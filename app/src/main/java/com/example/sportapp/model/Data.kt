package com.example.sportapp.model

import com.google.gson.annotations.SerializedName

data class SportData(
    @SerializedName("result") val result: List<Result> = listOf()
)

data class Result(
    @SerializedName("event_date") val eventDate: String? = null,
    @SerializedName("event_time") val eventTime: String? = null,
    @SerializedName("event_home_team") val eventHomeTeam: String? = null,
    @SerializedName("event_away_team") val eventAwayTeam: String? = null,
    @SerializedName("event_final_result") val eventFinalResult: String? = null,
    @SerializedName("event_status") val eventStatus: String? = null,
    @SerializedName("country_name") val countryName: String? = null,
    @SerializedName("league_name") val leagueName: String? = null,
    @SerializedName("event_home_team_logo") val eventHomeTeamLogo: String? = null,
    @SerializedName("event_away_team_logo") val eventAwayTeamLogo: String? = null,
    @SerializedName("scores") val scores: Scores? = Scores(),
)

data class Scores(
    @SerializedName("1stQuarter") val Quarter1: List<Quarter1?>? = listOf(),
    @SerializedName("2ndQuarter") val Quarter2: List<Quarter2?>? = listOf(),
    @SerializedName("3rdQuarter") val Quarter3: List<Quarter3?>? = listOf(),
    @SerializedName("4thQuarter") val Quarter4: List<Quarter4?>? = listOf()
)

data class Quarter1(
    @SerializedName("score_home") val scoreHome: String? = null,
    @SerializedName("score_away") val scoreAway: String? = null
)

data class Quarter2(
    @SerializedName("score_home") val scoreHome: String? = null,
    @SerializedName("score_away") val scoreAway: String? = null
)

data class Quarter3(
    @SerializedName("score_home") val scoreHome: String? = null,
    @SerializedName("score_away") val scoreAway: String? = null
)

data class Quarter4(
    @SerializedName("score_home") val scoreHome: String? = null,
    @SerializedName("score_away") val scoreAway: String? = null
)