package com.mazer.exhbuy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.mazer.exhbuy.data.datastore.DataStoreRepo
import com.mazer.exhbuy.ui.navigation.NavGraph
import com.mazer.exhbuy.ui.screens.settings.ChangeLocale
import com.mazer.exhbuy.ui.theme.EXHBuyTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var application: EXHBuyApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val mAuth = FirebaseAuth.getInstance()
            val datastore = DataStoreRepo(context = LocalContext.current)

            val themeValue =
                datastore.readThemeFromDataStore.collectAsState(initial = isSystemInDarkTheme())
            application.setAppThemeValue(themeValue.value)
            val appLocale = datastore.readLocaleFromDataStore.collectAsState(initial = "en")

            EXHBuyTheme(
                useDarkTheme = application.isDarkTheme.value
            ) {
                ChangeLocale(lang = appLocale.value)

                NavGraph(
                    navController = navController,
                    activity = this,
                    application = application,
                    mAuth = mAuth
                )
            }
        }
    }
}
