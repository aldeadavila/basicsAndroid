package com.aldeadavila.jetpackcomposebasics.datasource

import com.aldeadavila.jetpackcomposebasics.model.ApiResponse
import retrofit2.http.GET

interface RestDataSource {

    @GET("?inc=name")
    suspend fun getUserName(): ApiResponse

    @GET("?inc=location")
    suspend fun getUserLocation(): ApiResponse

    @GET("?inc=picture")
    suspend fun getUserPictures(): ApiResponse
}