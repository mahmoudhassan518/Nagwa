package com.mahmoud.nagwa.di.components.application

import com.mahmoud.nagwa.core.BaseApplication
import com.mahmoud.nagwa.data.datasources.remote.ApiService
import com.mahmoud.nagwa.di.modules.application.ApiInterFaceModule
import com.mahmoud.nagwa.di.modules.application.ApplicationContextModule
import com.mahmoud.nagwa.di.modules.application.NetworkModule
import com.mahmoud.nagwa.di.scopes.ApplicationScope
import com.mahmoud.nagwa.presentation.ui.main.MainActivity
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        ApplicationContextModule::class,
        NetworkModule::class,
        ApiInterFaceModule::class
    ]
)
interface ApplicationComponent {

    fun getApiInterface(): ApiService

    fun injectApplication(application: BaseApplication)
}