package com.mahmoud.nagwa.di.modules.activity

import android.content.Context
import com.mahmoud.nagwa.di.scopes.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class ActivityContextModule constructor(val context: Context) {
    @Provides
    @ActivityScope
    fun provideContext(): Context =
        context.applicationContext
}