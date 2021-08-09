package com.mahmoud.nagwa.presentation.utils.others

import com.google.gson.Gson
import com.mahmoud.nagwa.R
import com.mahmoud.nagwa.data.others.MyException
import com.mahmoud.nagwa.domain.models.ErrorResponse
import kotlinx.coroutines.TimeoutCancellationException
import okhttp3.ResponseBody
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

class ErrorHandler(resourcesHandler: ResourcesHandler) {

    private val unknownError = resourcesHandler.getString(R.string.error)
    private val networkError = resourcesHandler.getString(R.string.internet_error_message)
    private val networkTimeOutError = resourcesHandler.getString(R.string.time_out_message)


    fun handleError(throwable: Throwable): String {
        return when (throwable) {

            is TimeoutCancellationException -> {
                networkTimeOutError
            }
            is IOException -> {
                networkError
            }
            is HttpException -> {
                handleHttpException(throwable)
            }
            is MyException -> {
                throwable.message!!
            }
            else -> {
                unknownError
            }
        }
    }

    private fun handleHttpException(throwable: Throwable): String {
        val exception = throwable as HttpException
        val body: ResponseBody? = exception.response()?.errorBody()
        return try {
            val errorData = body?.string()
            val errorsResponse: ErrorResponse = Gson().fromJson(
                errorData,
                ErrorResponse::class.java
            )


            Timber.e("error from me: $errorData")
            errorsResponse.message
        } catch (e: Exception) {
            e.printStackTrace()
            unknownError
        }
    }


}