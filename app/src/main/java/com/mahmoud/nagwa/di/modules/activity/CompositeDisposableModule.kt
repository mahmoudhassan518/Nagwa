package com.mahmoud.nagwa.di.modules.activity

import com.mahmoud.nagwa.di.scopes.ActivityScope
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class CompositeDisposableModule {
    @Provides
    @ActivityScope
    fun compositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }
}