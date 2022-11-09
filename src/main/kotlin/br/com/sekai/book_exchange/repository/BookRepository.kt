package br.com.sekai.book_exchange.repository

import br.com.sekai.book_exchange.model.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book, Long?>
