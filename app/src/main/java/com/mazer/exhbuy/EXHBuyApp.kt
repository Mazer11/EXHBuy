package com.mazer.exhbuy

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EXHBuyApp : Application() {

    val isDarkTheme = mutableStateOf(false)

    override fun onCreate() {
        super.onCreate()
    }

    fun setAppThemeValue(
        themeValue: Boolean
    ){
        isDarkTheme.value = themeValue
    }

    fun switchAppTheme(){
        isDarkTheme.value = !isDarkTheme.value
    }

}