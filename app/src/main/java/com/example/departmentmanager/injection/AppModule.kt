package com.example.departmentmanager.injection

import android.content.Context
import com.example.departmentmanager.navigation.NavigationManager
import com.example.departmentmanager.preferences.PreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideNavigationManager(@ApplicationContext context: Context) = NavigationManager(context)

    @Singleton
    @Provides
    fun providePreferenceManager(@ApplicationContext context: Context) = PreferencesManager(context)
}