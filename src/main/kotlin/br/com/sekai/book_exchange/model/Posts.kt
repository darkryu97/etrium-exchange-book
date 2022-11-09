package br.com.sekai.book_exchange.model

import jakarta.persistence.*

@Entity
@Table(name="posts")
data class Posts(
    @Id
    @Column(name="post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(nullable = false, scale = 3)
    var postCount: Int = 0,
    @Column(nullable = false)
    var date: String = "",
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "user_id", )
    var userID: User = User(),
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "book_id",)
    var bookID: Book = Book(),
    @Column(nullable = true)
    var bookInterests : String?= null,
)
