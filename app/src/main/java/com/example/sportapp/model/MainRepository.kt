package com.example.sportapp.model

class MainRepository(private val retrofitService: RetrofitService) {
    suspend fun getData(date: String) = retrofitService.getData(from = date, to = date)
}