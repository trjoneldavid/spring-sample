package com.example.springsample.service

import com.example.springsample.entity.AuthorEntity
import com.example.springsample.entity.BookEntity
import com.example.springsample.model.Author
import com.example.springsample.model.Book
import com.example.springsample.repository.AuthorRepository
import com.example.springsample.repository.BookRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.*
import org.springframework.boot.test.context.SpringBootTest
import java.util.*


@SpringBootTest
class BookServiceTest {
    private val bookRepository: BookRepository = mock(BookRepository::class.java)
    private val authorRepository: AuthorRepository = mock(AuthorRepository::class.java)
    private val bookService: BookService = BookService(bookRepository, authorRepository)


    @Test
    fun getBooks_ShouldReturn_AllBooks(){
        `when`(bookRepository.findAll()).thenReturn(
            listOf(
                BookEntity(
                    id = 1,
                    name = "Final Empire",
                    author = AuthorEntity(
                        id = 1,
                        author_name = "Brandon"
                    )
                )
            )
        )
        assertThat(bookService.getBooks()).containsExactlyInAnyOrder(
            Book(
                id = 1,
                name="Final Empire",
                author = Author(
                    id = 1,
                    author_name = "Brandon"
                )
            )
        )
    }

    @Test
    fun addBook_ShouldAdd_Book() {
        val title = "Final Empire"
        val author = "Brandon"

        `when`(authorRepository.findAuthorByName(author)).thenReturn(emptyList())
        `when`(authorRepository.save(AuthorEntity(author_name = author))).thenReturn(AuthorEntity(id = 1, author_name = author))
        `when`(bookRepository.save(BookEntity(name = title, author = AuthorEntity(id = 1, author_name = author)))).thenReturn(
            BookEntity(id = 1, name = title, author = AuthorEntity(id = 1, author_name = author))
        )

        assertThat(bookService.addBook(title, author)).isEqualTo(
            Book(
                id = 1,
                name = title,
                author = Author(
                    id = 1,
                    author_name = author
                )
            )
        )
    }

    @Test
    fun getBookByName_ShouldReturn_Book() {
        val title = "Final Empire"
        `when`(bookRepository.findBookByName(title)).thenReturn(
            BookEntity(
                id = 1,
                name = title,
                author = AuthorEntity(
                    id = 1,
                    author_name = "Brandon"
                )
            )
        )
        assertThat(bookService.getBookByName(title)).isEqualTo(
            Book(
                id = 1,
                name = title,
                author = Author(
                    id = 1,
                    author_name = "Brandon"
                )
            )
        )
    }

    @Test
    fun testUpdateBook() {
        val originalTitle = "Original Title"
        val originalAuthor = "Original Author"
        val title = "Updated Book Title"
        val author = "Updated Author Name"
        val id = 1

        val existingAuthor = AuthorEntity(id = 1, author_name = "Original Author")
        val existingBook = BookEntity(id = 1, name = originalTitle, author = AuthorEntity(id = 1, author_name = originalAuthor))

        `when`(bookRepository.findById(id)).thenReturn(Optional.of(existingBook))
        `when`(authorRepository.findById(existingAuthor.id() ?: 0)).thenReturn(Optional.of(existingAuthor))
        `when`(authorRepository.save(any(AuthorEntity::class.java))).thenReturn(existingAuthor)
        `when`(bookRepository.save(any(BookEntity::class.java))).thenReturn(existingBook)

        val updatedBook = bookService.updateBook(title, author, id)


        assertNotEquals(originalTitle, updatedBook.getName())
        assertNotEquals(originalAuthor, updatedBook.getAuthor().author())
    }

    @Test
    fun testDeleteBook() {

        val title = "Example Book"
        val bookToDelete =  BookEntity(
            id = 1,
            name = title,
            author = AuthorEntity(
                id = 1,
                author_name = "Someone"
            )
        )
        `when`(bookRepository.findBookByName(title)).thenReturn(bookToDelete)
        bookService.deleteBook(title)
        verify(bookRepository, times(1)).delete(bookToDelete)

    }
}


