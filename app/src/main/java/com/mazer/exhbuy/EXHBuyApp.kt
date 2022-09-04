package com.mazer.exhbuy

import android.app.Application
import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import com.mazer.exhbuy.ui.components.BottomBar
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EXHBuyApp : Application() {

    val isDarkTheme = mutableStateOf(false)

    override fun onCreate() {

        //API

        super.onCreate()
        Log.d("StartApp", "In App")

    }

    fun getAppThemeFromDataStore(
        themeValue: Boolean
    ){
        isDarkTheme.value = themeValue
    }

    fun switchAppTheme(){
        isDarkTheme.value = !isDarkTheme.value
    }

}