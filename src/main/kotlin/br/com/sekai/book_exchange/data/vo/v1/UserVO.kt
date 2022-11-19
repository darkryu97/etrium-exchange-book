package br.com.sekai.book_exchange.data.vo.v1

import com.fasterxml.jackson.annotation.JsonProperty
import com.github.dozermapper.core.Mapping

data class UserVO(
    @Mapping("id")
    @field:JsonProperty("id")
    var key: Long = 0,
    var name: String = "",
    var email: String = "",
    var password: String = "",
)
