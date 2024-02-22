package com.example.springsample.controller


import com.example.springsample.controller.parameters.AddBookParameters
import com.example.springsample.controller.parameters.FindBookParameters
import com.example.springsample.controller.parameters.UpdateBookParameters
import com.example.springsample.model.Author
import com.example.springsample.model.Book
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BookControllerTest {
    @Autowired
    lateinit var bookController: BookController

    @Test
    fun getBooks_NoExistingBooks_ReturnEmptyList() {
        val books = bookController.getBooks()
        assertThat(books).isEmpty()
    }

    @Test
    fun getBooks_ExistingBooks_ReturnListOfBook() {
        val books = bookController.getBooks()
        assertThat(books).containsExactlyInAnyOrder(
            Book(
                id = 1, name = "Final Empire", author = Author(
                    id = 2,
                    author_name = "Sanderson"
                )
            )
        )
    }

    @Test
    fun addBooks_BooksAdded_ReturnPostMap() {
        val title = "Hell"
        val author = "Aldree"

        val addedBook = bookController.addBook(AddBookParameters(title, author))
        val retrievedAuthor = bookController.findBookById(addedBook.id ?: 0)
        assertThat(addedBook.id).isEqualTo(retrievedAuthor.getId())
    }

    @Test
    fun findBook_BookFound_ReturnFoundBook() {
        val name = "Age of Chaos"
        val retrievedBook = bookController.getBookByName(FindBookParameters(name))
        assertThat(retrievedBook).isEqualTo(
            Book(
                id = 4,
                name = "Age of Chaos",
                author = Author(id = 7, author_name = "Adrien")
            )
        )
    }

    @Test
    fun updateBook_Updated_ReturnNewBook() {
        val targetId = 4
        val newTitle = "Age of Chaos"
        val newAuthor = "Adrien"
        val retrievedBook = bookController.findBookById(targetId)
        val updatedBook = bookController.updateBook(UpdateBookParameters(newTitle, newAuthor, targetId))
        assertThat(retrievedBook).isNotEqualTo(updatedBook)
    }

    @Test
    fun deleteBook_Deleted() {
        val targetBook = "Hoffman"
        val book = bookController.getBookByName(FindBookParameters(targetBook))
        bookController.deleteBook(FindBookParameters(targetBook))
        assertThat(book).isNotIn(bookController.getBooks())
    }


}