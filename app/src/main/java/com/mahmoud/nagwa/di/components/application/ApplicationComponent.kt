package com.mahmoud.nagwa.di.components.application

import com.mahmoud.nagwa.core.BaseApplication
import com.mahmoud.nagwa.data.datasources.remote.ApiService
import com.mahmoud.nagwa.di.modules.application.NetworkModule
import com.mahmoud.nagwa.di.modules.application.*
import com.mahmoud.nagwa.di.scopes.ApplicationScope
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        ApplicationContextModule::class,
        NetworkModule::class,
        ApiInterFaceModule::class,
    ]
)
interface ApplicationComponent {

    fun getApiInterface(): ApiService


    fun injectApplication(application: BaseApplication)
}