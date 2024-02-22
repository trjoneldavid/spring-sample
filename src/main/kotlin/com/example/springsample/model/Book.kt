package com.example.springsample.model

import com.example.springsample.entity.AuthorEntity
import com.example.springsample.entity.BookEntity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import lombok.Builder

@Builder
data class Book (
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int? = null,
    val name: String,
    val author: Author,
)