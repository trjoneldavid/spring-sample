package com.example.springsample.service

import com.example.springsample.entity.AuthorEntity
import com.example.springsample.entity.BookEntity
import com.example.springsample.model.Author
import com.example.springsample.model.Book
import com.example.springsample.repository.AuthorRepository
import com.example.springsample.repository.BookRepository
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class BookService(private val bookRepository: BookRepository, private val authorRepository: AuthorRepository) {
    fun getBooks(): List<Book> {
        return bookRepository.findAll().stream()
            .map { bookEntity ->
                Book(
                    id = bookEntity.getId(),
                    name = bookEntity.getName(),
                    author = Author(
                        id = bookEntity.getAuthor().id(),
                        author_name = bookEntity.getAuthor().author(),
                    )
                )
            }
            .collect(Collectors.toList())
    }

    fun addBook(title: String, author: String): Book{
        val existingAuthor = authorRepository.findAuthorByName(author).firstOrNull()

        val finalAuthor = existingAuthor ?: authorRepository.save(AuthorEntity(author_name = author))
        val bookEntity = BookEntity(name = title, author = finalAuthor)

        return bookRepository.save(bookEntity).toBook()
    }

    fun getBookByName(title: String): Book{
        return bookRepository.findBookByName(title).let { bookEntity ->
            Book(
                id = bookEntity.getId(),
                name = bookEntity.getName(),
                author = Author(
                    author_name = bookEntity.getAuthor().author(),
                    id = bookEntity.getAuthor().id()
                )
            )
        }
    }

    fun getBookById(id: Int): BookEntity {
        return bookRepository.findById(id).get()
    }

    fun updateBook(title: String, author: String, id: Int): BookEntity {
        val book = bookRepository.findById(id).get()

        if(book.getAuthor().author() != author){

            val existingAuthor = book.getAuthor().id() ?: 0

            val updatedAuthor = authorRepository.findById(existingAuthor)
                .orElseThrow{NoSuchElementException("Author not found")}
                .apply { setAuthorName(author) }

            val newAuthor = authorRepository.save(updatedAuthor)
            book.apply {
                setName(title)
                setAuthor(newAuthor)
            }
        }

        else{
            book.apply {
                setName(title)
            }
        }

        return bookRepository.save(book)
    }

    fun deleteBook(title: String){
        val book = bookRepository.findBookByName(title)
        bookRepository.delete(book)
    }

}
