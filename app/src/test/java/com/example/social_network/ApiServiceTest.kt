package com.example.social_network

import com.example.social_network.data.model.LoginRequest
import com.example.social_network.data.model.LoginResponse
import com.example.social_network.data.model.SearchResponse
import com.example.social_network.data.network.ApiService
import com.example.social_network.data.network.RetrofitInstance
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class ApiServiceTest {
    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        RetrofitInstance.setBaseUrl("http://localhost:8080/api/v1/")
        apiService = RetrofitInstance.getApiService()
    }

    @Test
    fun testLoginSuccessful() = runBlocking {
        val loginRequest = LoginRequest("johndoe", "hash_johndoe")
        val expectedResponse = LoginResponse(
            1L,
            "johndoe",
            "johndoe@example.com",
            "John Doe",
            "Love to explore the world",
            "https://example.com/profiles/johndoe.jpg",
            "1990-05-10",
            0)

        val actualResponse = apiService.login(loginRequest)
        assertEquals(200, actualResponse.code())
        assertEquals(expectedResponse, actualResponse.body())
    }

    @Test
    fun testLoginFailed() = runBlocking {
        val loginRequest = LoginRequest("invalid_user", "wrong_password")

        val actualResponse = apiService.login(loginRequest)
        assertEquals(401, actualResponse.code())
    }

    @Test
    fun testSearchUsersByNameSuccessful() = runBlocking {
        val name = "John"
        val expectedResponse = listOf(
            SearchResponse(1L, "John Doe", "https://example.com/profiles/johndoe.jpg"),
            SearchResponse(5L, "Michael Johnson", "https://example.com/profiles/michaeljohnson.jpg"),
            )

        val actualResponse = apiService.searchUsersByName(name)
        assertEquals(200, actualResponse.code())
        assertEquals(expectedResponse, actualResponse.body())
    }

    @Test
    fun testSearchUsersByNameFailed() = runBlocking {
        val name = "InvalidName"

        val actualResponse = apiService.searchUsersByName(name)
        assertEquals(404, actualResponse.code())
    }
}