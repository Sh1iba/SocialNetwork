package com.example.social_network.steps

import com.example.social_network.data.network.RetrofitInstance
import io.cucumber.java.en.Given

class CommonSteps {

    @Given("the API base URL is {string}")
    fun setApiBaseUrl(url: String) {
        RetrofitInstance.setBaseUrl(url)
    }
}