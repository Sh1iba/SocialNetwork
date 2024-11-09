package com.example.social_network.data.model

data class LoginRequest(
    val username: String,
    val passwordHash: String
)