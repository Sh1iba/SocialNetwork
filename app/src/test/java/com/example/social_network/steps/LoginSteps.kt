package com.example.social_network.steps

import com.example.social_network.data.model.LoginRequest
import com.example.social_network.data.model.LoginResponse
import com.example.social_network.data.network.ApiService
import com.example.social_network.data.network.RetrofitInstance
import io.cucumber.java.en.When
import io.cucumber.java.en.Then
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import retrofit2.Response

class LoginSteps {
    private var apiService: ApiService = RetrofitInstance.getApiService()
    private lateinit var response: Response<LoginResponse>


    @When("the user sends a login request with username {string} and password {string}")
    fun sendLoginRequest(username: String, password: String) = runBlocking {
        val loginRequest = LoginRequest(username, password)
        response = apiService.login(loginRequest)
    }

    @Then("the login response code should be {int}")
    fun verifyLoginResponseCode(expectedCode: Int) {
        assertEquals(expectedCode, response.code())
    }

    @Then("the response body should match:")
    fun verifyResponseBody(expectedBody: Map<String, String>) {
        val actualBody = response.body()
        assertNotNull(actualBody)
        assertEquals(expectedBody["id"]?.toLong(), actualBody?.userId)
        assertEquals(expectedBody["username"], actualBody?.username)
        assertEquals(expectedBody["email"], actualBody?.email)
        assertEquals(expectedBody["fullName"], actualBody?.fullName)
        assertEquals(expectedBody["bio"], actualBody?.bio)
        assertEquals(expectedBody["profilePic"], actualBody?.profilePictureUrl)
        assertEquals(expectedBody["birthday"], actualBody?.dateOfBirth)
        assertEquals(expectedBody["followers"]?.toInt(), actualBody?.followersCount)
    }
}