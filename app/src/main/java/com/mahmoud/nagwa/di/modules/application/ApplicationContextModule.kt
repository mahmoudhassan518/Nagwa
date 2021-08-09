package com.mahmoud.nagwa.di.modules.application

import android.content.Context
import com.mahmoud.nagwa.di.qualifiers.ApplicationContext
import com.mahmoud.nagwa.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ApplicationContextModule(context: Context) {
    private val context: Context = context.applicationContext

    @Provides
    @ApplicationScope
    @ApplicationContext
    fun context(): Context {
        return context
    }

}