package com.mahmoud.nagwa.presentation.utils.others

import android.content.Context

class ResourcesHandler(private val context: Context) {
    fun getString(stringId: Int) = context.getString(stringId)
}