package com.mahmoud.nagwa.data.datasources.remote

import com.mahmoud.nagwa.data.models.dto.MoviesDto
import retrofit2.http.GET

interface ApiService {

    @GET("/movies")
    suspend fun requestMovies(): MoviesDto

}