package com.example.sportapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelProvider
import com.example.sportapp.model.MainRepository
import com.example.sportapp.model.RetrofitService
import com.example.sportapp.navigation.Navigation
import com.example.sportapp.viewmodel.MainViewModel
import com.example.sportapp.viewmodel.MyViewModelFactory
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : ComponentActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val retrofitService = RetrofitService.getInstance()
        val mainRepository = MainRepository(retrofitService)

        viewModel = ViewModelProvider(
            this,
            MyViewModelFactory(mainRepository)
        ).get(MainViewModel::class.java)

        @SuppressLint("SimpleDateFormat")
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val currentDate = sdf.format(Date())
        viewModel.currentDate.value = currentDate


        if (viewModel.dataHasBeenLoaded.value == false) {
            viewModel.getData(currentDate)
        }

        setContent {
            Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
                Navigation(viewModel)
            }
        }
    }
}

