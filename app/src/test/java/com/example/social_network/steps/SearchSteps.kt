package com.example.social_network.steps

import com.example.social_network.data.model.SearchResponse
import com.example.social_network.data.network.ApiService
import com.example.social_network.data.network.RetrofitInstance
import io.cucumber.java.en.When
import io.cucumber.java.en.Then
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import retrofit2.Response

class SearchSteps {
    private var apiService: ApiService = RetrofitInstance.getApiService()
    private lateinit var response: Response<List<SearchResponse>>

    @When("the user searches for users with name {string}")
    fun searchUsersByName(name: String) = runBlocking {
        response = apiService.searchUsersByName(name)
    }

    @Then("the search response code should be {int}")
    fun verifySearchResponseCode(expectedCode: Int) {
        assertEquals(expectedCode, response.code())
    }

    @Then("the response body should contain:")
    fun verifySearchResults(expectedResults: List<Map<String, String>>) {
        val actualResults = response.body()
        assertNotNull(actualResults)
        assertEquals(expectedResults.size, actualResults!!.size)

        for ((index, expected) in expectedResults.withIndex()) {
            val actual = actualResults[index]
            assertEquals(expected["id"]?.toLong(), actual.userId)
            assertEquals(expected["name"], actual.fullName)
            assertEquals(expected["profilePic"], actual.profilePictureUrl)
        }
    }
}