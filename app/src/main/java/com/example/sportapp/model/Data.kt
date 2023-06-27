package com.example.sportapp.model

import com.google.gson.annotations.SerializedName

data class SportData (
    @SerializedName("result"  ) var result  : ArrayList<Result> = arrayListOf()
)

data class Result (
    @SerializedName("event_date"           ) var eventDate         : String?           = null,
    @SerializedName("event_time"           ) var eventTime         : String?           = null,
    @SerializedName("event_home_team"      ) var eventHomeTeam     : String?           = null,
    @SerializedName("event_away_team"      ) var eventAwayTeam     : String?           = null,
    @SerializedName("event_final_result"   ) var eventFinalResult  : String?           = null,
    @SerializedName("event_status"         ) var eventStatus       : String?           = null,
    @SerializedName("country_name"         ) var countryName       : String?           = null,
    @SerializedName("league_name"          ) var leagueName        : String?           = null,
    @SerializedName("event_home_team_logo" ) var eventHomeTeamLogo : String?           = null,
    @SerializedName("event_away_team_logo" ) var eventAwayTeamLogo : String?           = null,
    @SerializedName("scores"               ) var scores            : Scores?           = Scores(),
)

data class Scores (
    @SerializedName("1stQuarter" ) var Quarter1 : ArrayList<Quarter1> = arrayListOf(),
    @SerializedName("2ndQuarter" ) var Quarter2 : ArrayList<Quarter2> = arrayListOf(),
    @SerializedName("3rdQuarter" ) var Quarter3 : ArrayList<Quarter3> = arrayListOf(),
    @SerializedName("4thQuarter" ) var Quarter4 : ArrayList<Quarter4> = arrayListOf()
)

data class Quarter1 (
    @SerializedName("score_home" ) var scoreHome : String? = null,
    @SerializedName("score_away" ) var scoreAway : String? = null
)

data class Quarter2 (
    @SerializedName("score_home" ) var scoreHome : String? = null,
    @SerializedName("score_away" ) var scoreAway : String? = null
)

data class Quarter3 (
    @SerializedName("score_home" ) var scoreHome : String? = null,
    @SerializedName("score_away" ) var scoreAway : String? = null
)

data class Quarter4 (
    @SerializedName("score_home" ) var scoreHome : String? = null,
    @SerializedName("score_away" ) var scoreAway : String? = null
)