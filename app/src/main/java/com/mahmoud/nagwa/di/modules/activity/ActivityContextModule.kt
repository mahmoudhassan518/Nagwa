package com.mahmoud.nagwa.di.modules.activity

import android.app.Activity
import android.content.Context
import com.mahmoud.nagwa.di.scopes.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class ActivityContextModule {
    private val activity: Activity?
    private val context: Context?

    constructor(activity: Activity) {
        this.activity = activity
        context = activity
    }

    constructor(context: Context?) {
        activity = null
        this.context = context
    }

    @Provides
    @ActivityScope
    fun context(): Context? {
        return context
    }

    @Provides
    @ActivityScope
    fun activity(): Activity? {
        return activity
    }
}