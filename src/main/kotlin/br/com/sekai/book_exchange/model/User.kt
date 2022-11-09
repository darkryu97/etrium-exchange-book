package br.com.sekai.book_exchange.model

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name="user")
data class User(
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(nullable = false, length = 180)
    var name: String = "",
    @Column(nullable = false)
    var email: String = "",
    @Column(nullable = false)
    var password: String = "",

)
