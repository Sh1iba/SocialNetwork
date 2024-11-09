package com.example.social_network.data.network


import com.example.social_network.data.model.LoginRequest
import com.example.social_network.data.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("login")  // или другой endpoint
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @GET("search/users")
    suspend fun searchUsersByName(@Query("name") name: String): List<LoginResponse>

}