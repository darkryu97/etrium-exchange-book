package br.com.sekai.book_exchange.model

import jakarta.persistence.*

@Entity
@Table(name="posts")
@Embeddable()
data class Posts(
    @Column(nullable = false, scale = 3)
    var postCount: Int = 0,
    @Column(nullable = false)
    var date: String = "",

    @EmbeddedId
    var postId: PostId  = PostId(),

//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn( name = "id_user", )
//    var userID: User = User(),
//    @EmbeddedId
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn( name = "id_book",)
//    var bookID: Book = Book(),

    @Column(nullable = true)
    var bookInterests : String?= null,
)
