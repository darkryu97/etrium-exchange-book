package br.com.sekai.book_exchange.controller

import br.com.sekai.book_exchange.data.vo.v1.BookVO
import br.com.sekai.book_exchange.service.BookService
import br.com.sekai.book_exchange.uitl.MediaType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = ["http://127.0.0.1:5173/", "https://book-exchange-1kvv.vercel.app/"])
@RestController
@RequestMapping("/api/books/v1")
class BookController {
    @Autowired
    private lateinit var bookService: BookService

    @GetMapping(produces = [MediaType.APPLICATION_JSON])
    fun findAll(): List<BookVO>{
        return bookService.findAll()
    }

    @PostMapping(produces = [MediaType.APPLICATION_JSON], consumes = [MediaType.APPLICATION_JSON])
    fun create(@RequestBody bookVO: BookVO): BookVO{
         return  bookService.createBook(bookVO)
    }
}
