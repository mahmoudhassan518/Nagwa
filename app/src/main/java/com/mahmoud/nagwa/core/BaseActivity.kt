package com.mahmoud.nagwa.core

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.mahmoud.nagwa.di.components.activity.DaggerMainControllerComponent
import com.mahmoud.nagwa.di.components.activity.MainControllerComponent
import com.mahmoud.nagwa.di.components.application.ApplicationComponent
import com.mahmoud.nagwa.di.modules.activity.ActivityContextModule


abstract class BaseActivity<T : ViewDataBinding, VM : BaseViewModel>(
    private val layoutId: Int
) : AppCompatActivity() {


    protected abstract val viewModel: VM

    //in case we needed to access the views
    lateinit var binder: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = DataBindingUtil.setContentView(this, layoutId)


        val component: MainControllerComponent = DaggerMainControllerComponent.builder()
            .activityContextModule(ActivityContextModule(this))
            .applicationComponent(getApplicationComponent())
            .build()
        injectActivity(component)


        setup()
        viewModel.state.observe(this, { baseRender(it) })

    }

    protected open fun getApplicationComponent(): ApplicationComponent? {
        return (application as BaseApplication)
            .getApplicationComponent()
    }

    abstract fun injectActivity(component: MainControllerComponent)


    override fun onDestroy() {

        viewModel.compositeDisposable.dispose()

        super.onDestroy()
    }
    abstract fun setup()

    private fun baseRender(state: ViewState) {
        when (state) {
            is ViewState.Loading -> showLoading()
            is ViewState.IDLE -> hideLoading()
            is ViewState.Error -> showError(state.error)
            else -> {
                hideLoading()
                render(state)
            }
        }
    }

    abstract fun render(state: ViewState)

    // not private for the sake of overriding in case of custom implementation for specific screens
    open fun showLoading() {
    }

    // not private for the sake of overriding in case of custom implementation for specific screens
    open fun hideLoading() {
    }

    open fun showError(errorModel: String?) {
        hideLoading()
        Toast.makeText(applicationContext, errorModel, Toast.LENGTH_SHORT).show()
    }
}
