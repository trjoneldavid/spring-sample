package com.example.springsample.repository

import com.example.springsample.entity.BookEntity
import com.example.springsample.model.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : JpaRepository<BookEntity, Int>{

    @Query("SELECT b FROM BookEntity b WHERE b.name = :name")
    fun findBookByName(@Param("name") name: String): BookEntity

}

