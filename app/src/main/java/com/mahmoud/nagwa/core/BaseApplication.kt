package com.mahmoud.nagwa.core

import android.app.Application
import androidx.annotation.UiThread
import com.androidnetworking.AndroidNetworking
import com.mahmoud.nagwa.BuildConfig.DEBUG
import com.mahmoud.nagwa.di.components.application.ApplicationComponent
import com.mahmoud.nagwa.di.components.application.DaggerApplicationComponent
import com.mahmoud.nagwa.di.modules.application.ApplicationContextModule
import timber.log.Timber

class BaseApplication : Application() {

    private lateinit var applicationComponent: ApplicationComponent


    override fun onCreate() {
        super.onCreate()

        AndroidNetworking.initialize(applicationContext);
        getApplicationComponent().injectApplication(this)

        if (DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }


    @UiThread
    fun getApplicationComponent(): ApplicationComponent {
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationContextModule(ApplicationContextModule(this.applicationContext))
            .build()
        return applicationComponent
    }
}