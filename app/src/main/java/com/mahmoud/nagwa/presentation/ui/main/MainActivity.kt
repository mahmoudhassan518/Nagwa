package com.mahmoud.nagwa.presentation.ui.main

import com.mahmoud.nagwa.R
import com.mahmoud.nagwa.core.BaseActivity
import com.mahmoud.nagwa.core.ViewState
import com.mahmoud.nagwa.databinding.ActivityMainBinding
import com.mahmoud.nagwa.di.components.activity.MainControllerComponent
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    @Inject
    override lateinit var viewModel: MainViewModel

    override fun injectActivity(component: MainControllerComponent) {
        component.injectActivity(this)
    }

    override fun setup() {

    }

    override fun render(state: ViewState) {

    }

}