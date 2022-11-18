package br.com.sekai.book_exchange.data.vo.v1

import br.com.sekai.book_exchange.model.Book
import br.com.sekai.book_exchange.model.User
import com.fasterxml.jackson.annotation.JsonProperty
import com.github.dozermapper.core.Mapping


data class PostsVO(
    var postCount: Int = 0,
    var date: String = "",
    var userID: UserVO= UserVO(),
    var bookID: BookVO = BookVO(),
    var bookInterests : String?= null,
)
