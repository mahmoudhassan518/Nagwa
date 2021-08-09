package com.mahmoud.nagwa.di.components.application

import com.mahmoud.nagwa.core.BaseApplication
import com.mahmoud.nagwa.di.modules.application.ApplicationContextModule
import com.mahmoud.nagwa.di.scopes.ApplicationScope
import dagger.Component

@ApplicationScope
@Component(modules = [ApplicationContextModule::class])
interface ApplicationComponent {
    fun injectApplication(application: BaseApplication)
}