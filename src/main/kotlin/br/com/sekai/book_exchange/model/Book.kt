package br.com.sekai.book_exchange.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table


@Entity
@Table(name= "book")
data class Book (
    @Id
    @Column(name="book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(nullable = false, length = 250)
    var title : String  = "",
    @Column(nullable = false, length = 180)
    var author : String= "",
    @Column(nullable = false, length = 180)
    var publisher : String= "",
    @Column(nullable = false)
    var imgURL : String= "",
    @Column(length = 1000)
    var resume : String= "",
)
