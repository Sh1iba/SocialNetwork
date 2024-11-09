package com.example.social_network.data.model

data class LoginResponse(
    val userId: Long,
    val username: String,
    val email: String,
    val fullName: String,
    val bio: String,
    val profilePictureUrl: String,
    val dateOfBirth: String,
    val followersCount: Int
)