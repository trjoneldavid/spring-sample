package com.example.springsample.entity

import com.example.springsample.model.Author
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

@Entity
@Table(name="author")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
data class AuthorEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Int? = null,
    @Column
    private var author_name: String,
){
    fun id(): Int? {
        return this.id
    }

    fun author(): String{
        return this.author_name
    }

    fun setAuthorName(name: String){
        this.author_name = name
    }


}



