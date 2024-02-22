package com.example.springsample.model

import com.example.springsample.entity.AuthorEntity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import lombok.Builder
import lombok.Data

@Builder
@Data
data class Author(
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int? = null,
    val author_name: String,
)