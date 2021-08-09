package com.mahmoud.nagwa.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable


abstract class BaseViewModel(
//    private val compositeDisposable: CompositeDisposable
) : ViewModel() {

    protected val internalState = MutableLiveData<ViewState>()
    val state: LiveData<ViewState> = internalState


}
