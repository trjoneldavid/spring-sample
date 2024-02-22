package com.example.springsample.repository

import com.example.springsample.entity.AuthorEntity
import com.example.springsample.entity.BookEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository


@Repository
interface AuthorRepository: JpaRepository<AuthorEntity, Int>{

    @Query("SELECT b FROM AuthorEntity b WHERE b.author_name = :name")
    fun findAuthorByName(@Param("name") name: String): List<AuthorEntity>
}