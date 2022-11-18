package br.com.sekai.book_exchange.model

import jakarta.persistence.*
import java.io.Serializable

@Embeddable
data class PostId(
                  @OneToOne(fetch = FetchType.LAZY)
                  @JoinColumn( name = "id_user", )
                  var userID: User = User(),

                  @OneToOne(fetch = FetchType.LAZY)
                  @JoinColumn( name = "id_book",)
                  var bookID: Book = Book(),): Serializable
