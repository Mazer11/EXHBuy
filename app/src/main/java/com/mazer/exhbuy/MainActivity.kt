package com.mazer.exhbuy

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.mazer.exhbuy.ui.components.BottomBar
import com.mazer.exhbuy.ui.navigation.NavGraph
import com.mazer.exhbuy.ui.theme.EXHBuyTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var application: EXHBuyApp

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            EXHBuyTheme(
                useDarkTheme = isSystemInDarkTheme()
            ) {
                Scaffold(
                    bottomBar = {
                        BottomBar(
                            navController = navController,
                        )
                    }
                ) {
                    NavGraph(
                        navController = navController,
                        activity = this,
                        application = application
                    )
                }
            }
        }
    }
}
