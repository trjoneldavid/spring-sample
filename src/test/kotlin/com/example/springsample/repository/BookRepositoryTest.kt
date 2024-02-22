package com.example.springsample.repository

import com.example.springsample.entity.AuthorEntity
import com.example.springsample.entity.BookEntity
import com.example.springsample.model.Book
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BookRepositoryTest {
    @Autowired
    lateinit var bookRepository: BookRepository

    @Test
    fun findAll_ShouldReturn_AllBooks(){
        val books = bookRepository.findAll()
        assertThat(books).containsExactlyInAnyOrder(
            BookEntity(
                id = 1,
                name = "Final Empire",
                author = AuthorEntity(
                    id = 1,
                    author_name = "Brandon"
                )
            )
        )
    }

}