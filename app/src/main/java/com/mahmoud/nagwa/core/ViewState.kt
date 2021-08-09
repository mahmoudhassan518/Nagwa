package com.mahmoud.nagwa.core


abstract class ViewState {
    object Loading : ViewState()
    data class Error(val error: String?) : ViewState()
    abstract class Loaded<out T>(val result: T) : ViewState()
    object Empty : ViewState()
    object IDLE : ViewState()

}