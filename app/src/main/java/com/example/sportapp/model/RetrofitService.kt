package com.example.sportapp.model


import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val MY_KEY = "a2e5bd194dc1dd494dff762bea2a363f6f410939247a665b6cedef0d476c0b79"

interface RetrofitService {

    @GET("basketball")
    suspend fun getData(
        @Query("met") met: String = "Fixtures",
        @Query("APIkey") APIkey: String = MY_KEY,
        @Query("from") from: String,
        @Query("to") to: String,
    ): Response<SportData>

    companion object {

        var retrofitService: RetrofitService? = null

        fun getInstance(): RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://apiv2.allsportsapi.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
                return retrofitService as RetrofitService
            } else {
                return retrofitService as RetrofitService
            }

        }

    }
}