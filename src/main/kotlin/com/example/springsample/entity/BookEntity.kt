package com.example.springsample.entity

import com.example.springsample.model.Author
import com.example.springsample.model.Book
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor


@Entity
@Table(name="book")
@Data
@NoArgsConstructor
@AllArgsConstructor
data class BookEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Int? = null,

    @Column
    private var name: String,

    @OneToOne
    private var author: AuthorEntity,

){
    fun getId() : Int? {
        return this.id
    }

    fun setId(id: Int){
        this.id = id
    }

    fun getName(): String{
        return this.name
    }

    fun setName(name: String){
        this.name = name
    }

    fun getAuthor(): AuthorEntity{
        return this.author
    }

    fun setAuthor(author: AuthorEntity){
        this.author = author
    }

    fun toBook(): Book{
        return Book(
            id = this.id,
            name = this.name,
            author = Author(
                id = this.author.id(),
                author_name = this.author.author(),
            )
        )
    }
}




