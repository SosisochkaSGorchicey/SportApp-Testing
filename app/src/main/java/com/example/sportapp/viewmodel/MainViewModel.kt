package com.example.sportapp.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sportapp.model.MainRepository
import com.example.sportapp.model.SportData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    // To not get api response after configuration change (rotating)
    val dataHasBeenLoaded = MutableLiveData(false)

    //To show OneSignal message only once
    val toShowOneSignal = MutableLiveData(true)

    // To show problems: no connection, etc.
    val errorMessageForDisplay = MutableLiveData<String>()

    // Our data from api
    val data = MutableLiveData<SportData>()

    var job: Job? = null
    val loading = MutableLiveData<Boolean>()
    val currentDate = MutableLiveData("")


    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        errorMessageForDisplay.postValue(throwable.localizedMessage)
        Log.v("CoroutineException", "Exception handled: ${throwable.localizedMessage}")
    }

    // Main function to get data from api
    fun getData(date: String) {

        dataHasBeenLoaded.value = true

        // If current date changed - change date for api call
        @SuppressLint("SimpleDateFormat")
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val newCurrentDate = sdf.format(Date())
        if (newCurrentDate != currentDate.value) {
            currentDate.value = newCurrentDate
        }

        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {

            val response = mainRepository.getData(date)
            //Log.v("responseFromApi", response.toString())

            if (response.isSuccessful) {
                errorMessageForDisplay.postValue("")
                data.postValue(response.body())
                loading.postValue(false)
            } else {
                onError("Error : ${response.message()} ")
            }
        }
    }

    private fun onError(message: String) {
        errorMessageForDisplay.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}