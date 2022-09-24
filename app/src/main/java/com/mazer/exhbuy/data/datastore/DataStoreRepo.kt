package com.mazer.exhbuy.data.datastore

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okio.IOException
import javax.inject.Singleton

const val DATASTORE_NAME = "exhbuy_datastore"
const val THEME_PREFERENCE = "theme"
const val LOCALISATION_PREFERENCE = "locale"

@Singleton
class DataStoreRepo(
    private val context: Context
) {
    private val TAG = "DataStore"

    companion object {
        private val Context.datastore by preferencesDataStore(
            name = DATASTORE_NAME
        )
    }

    private object PreferenceKeys {
        val theme_key = booleanPreferencesKey(THEME_PREFERENCE)
        val locale_key = stringPreferencesKey(LOCALISATION_PREFERENCE)
    }

    val readThemeFromDataStore: Flow<Boolean> = context.datastore.data
        .catch { e ->
            if (e is IOException) {
                Log.e(TAG, e.message.toString())
                emit(emptyPreferences())
            } else
                throw e
        }
        .map { preferences ->
            val appTheme = preferences[PreferenceKeys.theme_key] ?: false
            appTheme
        }

    val readLocaleFromDataStore: Flow<String> = context.datastore.data
        .catch { e ->
            if (e is IOException) {
                Log.e(TAG, e.message.toString())
                emit(emptyPreferences())
            } else
                throw e
        }
        .map { preferences ->
            val appLocale = preferences[PreferenceKeys.locale_key] ?: "en"
            appLocale
        }

    suspend fun editThemePreference() {
        context.datastore.edit { pref ->
            if(pref[PreferenceKeys.theme_key] == null)
                pref[PreferenceKeys.theme_key] = false
            else
                pref[PreferenceKeys.theme_key] = !pref[PreferenceKeys.theme_key]!!
        }
    }

    suspend fun editLocalePreference(
        locale: String
    ) {
        context.datastore.edit { pref ->
            if(pref[PreferenceKeys.locale_key] == null)
                pref[PreferenceKeys.locale_key] = "en"
            else
                pref[PreferenceKeys.locale_key] =locale
        }
    }

}