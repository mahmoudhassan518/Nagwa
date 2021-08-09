package com.mahmoud.nagwa.data.datasources.locale

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mahmoud.nagwa.data.models.dto.MoviesDto
import com.mahmoud.nagwa.di.qualifiers.ApplicationContext
import com.mahmoud.nagwa.di.scopes.ActivityScope
import java.io.IOException
import javax.inject.Inject

class OfflineDataProvider @Inject constructor(@ActivityScope val context: Context){

    private fun getJsonDataFromAsset(fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }



    private val jsonFileString = getJsonDataFromAsset( "getListOfFilesResponse.json")
    private val listPersonType = object : TypeToken<MoviesDto>() {}.type

    var moviesDto: MoviesDto = Gson().fromJson(jsonFileString, listPersonType)
}