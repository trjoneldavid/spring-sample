package com.example.springsample.controller.parameters

data class UpdateBookParameters(
    val title: String,
    val author: String,
    val id: Int,
)