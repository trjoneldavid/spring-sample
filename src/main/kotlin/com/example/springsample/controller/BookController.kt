package com.example.springsample.controller

import com.example.springsample.controller.parameters.AddBookParameters
import com.example.springsample.controller.parameters.FindBookParameters
import com.example.springsample.controller.parameters.UpdateBookParameters
import com.example.springsample.entity.BookEntity
import com.example.springsample.model.Book
import com.example.springsample.service.BookService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class BookController(private val bookService: BookService) {
    @GetMapping("/books")
    fun getBooks(): List<Book> {
        return bookService.getBooks()
    }

    @PostMapping("/addBooks")
    fun addBook(@RequestBody parameters: AddBookParameters):Book {
        val (title,author) = parameters
        return bookService.addBook(title,author)
    }

    @PutMapping("/updateBook")
    fun updateBook(@RequestBody parameters: UpdateBookParameters): BookEntity {
        val(title,author,id) = parameters
        return bookService.updateBook(title,author,id)
    }

    @GetMapping("/getBook")
    fun getBookByName(@RequestBody parameters: FindBookParameters): Book {
        val (name) = parameters
        return bookService.getBookByName(name)
    }

    @DeleteMapping("/deleteBook")
    fun deleteBook(@RequestBody parameters: FindBookParameters){
        val (title) = parameters
        return bookService.deleteBook(title)
    }

    fun findBookById( id: Int): BookEntity{
        return bookService.getBookById(id)
    }

}

