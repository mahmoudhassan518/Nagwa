package com.mahmoud.nagwa.di.modules.application

import android.content.Context
import com.mahmoud.nagwa.data.datasources.locale.OfflineDataProvider
import com.mahmoud.nagwa.data.others.FileManager
import com.mahmoud.nagwa.di.qualifiers.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationContextModule(context: Context) {
    private val context: Context = context.applicationContext

    @Provides
    @Singleton
    @ApplicationContext
    fun context(): Context =
        context

    @Provides
    @Singleton
    fun offlineDataProvider(@ApplicationContext context: Context): OfflineDataProvider =
        OfflineDataProvider(context)

    @Singleton
    @Provides
    fun fileManager(
    ): FileManager =
        FileManager()

}