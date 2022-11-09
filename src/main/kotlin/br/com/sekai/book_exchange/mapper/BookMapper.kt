package br.com.sekai.book_exchange.mapper

import br.com.sekai.book_exchange.data.vo.v1.BookVO
import br.com.sekai.book_exchange.model.Book

fun BookVO.toEntity() = Book(this.key,this.title,this.author,this.publisher,this.imgURL,this.resume)
fun Book.toVO() = BookVO(this.id,this.title,this.author,this.publisher,this.imgURL,this.resume)

fun bookVOListToListBook(booksVO: ArrayList<BookVO>): ArrayList<Book> {
        val toBook = ArrayList<Book>()
        booksVO.forEach { book ->
           toBook.add( book.toEntity())
        }

    return toBook
}
fun bookListToListBookVO(booksList: ArrayList<Book>): ArrayList<BookVO> {
        val bookVo = ArrayList<BookVO>()
        booksList.forEach { book ->
           bookVo.add( book.toVO())
        }

    return bookVo
}
