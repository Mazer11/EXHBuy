package com.mazer.exhbuy

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EXHBuyApp : Application() {

    private val isDarkTheme = mutableStateOf(false)

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