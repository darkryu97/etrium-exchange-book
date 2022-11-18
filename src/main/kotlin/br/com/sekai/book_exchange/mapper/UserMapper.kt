package br.com.sekai.book_exchange.mapper

import br.com.sekai.book_exchange.data.vo.v1.UserVO
import br.com.sekai.book_exchange.model.User

fun User.toVO()= UserVO(this.id,this.name,this.email,this.password)
fun User.toVOnoPassword()= UserVO(this.id,this.name,this.email)
fun UserVO.toEntity()= User(this.key,this.name,this.email)

//fun bookVOListToListBook(booksVO: ArrayList<BookVO>): ArrayList<Book> {
//        val toBook = ArrayList<Book>()
//        booksVO.forEach { book ->
//           toBook.add( book.toEntity())
//        }
//
//    return toBook
//}
//fun bookListToListBookVO(booksList: ArrayList<Book>): ArrayList<BookVO> {
//        val bookVo = ArrayList<BookVO>()
//        booksList.forEach { book ->
//           bookVo.add( book.toVO())
//        }
//
//    return bookVo
//}
