package com.mazer.exhbuy.di

import android.content.Context
import com.mazer.exhbuy.EXHBuyApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApp(@ApplicationContext app: Context): EXHBuyApp {
        return app as EXHBuyApp
    }

}