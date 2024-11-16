package com.example.social_network

import com.example.social_network.data.model.LoginRequest
import com.example.social_network.data.model.LoginResponse
import com.example.social_network.data.model.SearchResponse
import com.example.social_network.data.network.ApiService
import com.example.social_network.data.network.RetrofitInstance
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.runBlocking

class ApiServiceBDDTest : BehaviorSpec({

    RetrofitInstance.setBaseUrl("http://localhost:8080/api/v1/")
    val apiService: ApiService = RetrofitInstance.getApiService()

    given("a user wants to login") {
        `when`("the login credentials are correct") {
            then("the user should be logged in successfully") {
                testLoginSuccessful(apiService)
            }
        }

        `when`("the login credentials are incorrect") {
            then("the login should fail") {
                testLoginFailed(apiService)
            }
        }
    }

    given("a user wants to search for other users by name") {
        `when`("the name exists in the database") {
            then("the search should return matching users") {
                testSearchUsersByNameSuccessful(apiService)
            }
        }

        `when`("the name does not exist in the database") {
            then("the search should return no users") {
                testSearchUsersByNameFailed(apiService)
            }
        }
    }
})

private fun testLoginSuccessful(apiService: ApiService) = runBlocking {
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
    actualResponse.code() shouldBe 200
    actualResponse.body() shouldBe expectedResponse
}

private fun testLoginFailed(apiService: ApiService) = runBlocking {
    val loginRequest = LoginRequest("invalid_user", "wrong_password")

    val actualResponse = apiService.login(loginRequest)
    actualResponse.code() shouldBe 401
}

private fun testSearchUsersByNameSuccessful(apiService: ApiService) = runBlocking {
    val name = "John"
    val expectedResponse = listOf(
        SearchResponse(1L, "John Doe", "https://example.com/profiles/johndoe.jpg"),
        SearchResponse(5L, "Michael Johnson", "https://example.com/profiles/michaeljohnson.jpg"),
    )

    val actualResponse = apiService.searchUsersByName(name)
    actualResponse.code() shouldBe 200
    actualResponse.body() shouldBe expectedResponse
}

private fun testSearchUsersByNameFailed(apiService: ApiService) = runBlocking {
    val name = "InvalidName"

    val actualResponse = apiService.searchUsersByName(name)
    actualResponse.code() shouldBe 404
}