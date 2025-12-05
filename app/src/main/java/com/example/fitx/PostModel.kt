package com.example.fitx

data class PostModel(
    val userName: String,
    val time: String,
    val content: String,
    val likes: String,
    val comments: String,
    val avatarRes: Int,        // Profile Pic (R.drawable.xxx)
    val postImageRes: Int? = null // Post Image (can be null if it's just text)
)