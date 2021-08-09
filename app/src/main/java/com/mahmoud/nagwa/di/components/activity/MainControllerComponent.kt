package com.mahmoud.nagwa.di.components.activity

import com.mahmoud.nagwa.di.components.application.ApplicationComponent
import com.mahmoud.nagwa.di.modules.activity.ActivityContextModule
import com.mahmoud.nagwa.di.modules.activity.CompositeDisposableModule
import com.mahmoud.nagwa.di.scopes.ActivityScope
import com.mahmoud.nagwa.presentation.ui.main.MainActivity
import dagger.Component

@Component(
    modules = [ActivityContextModule::class,
        CompositeDisposableModule::class],
    dependencies = [ApplicationComponent::class]
)
@ActivityScope
interface MainControllerComponent {
    fun injectActivity(activity: MainActivity?)
}