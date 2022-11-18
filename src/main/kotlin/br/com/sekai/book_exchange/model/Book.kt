package br.com.sekai.book_exchange.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table



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
