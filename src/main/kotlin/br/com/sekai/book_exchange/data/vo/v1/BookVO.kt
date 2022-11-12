package br.com.sekai.book_exchange.data.vo.v1

import com.fasterxml.jackson.annotation.JsonProperty
import com.github.dozermapper.core.Mapping

data class BookVO (
    @Mapping("id")
    @field:JsonProperty("id")
    var key: Long = 0,
    var title : String  = "",
    var author : String= "",
    var publisher : String= "",
    var imgURL : String= "",
    var resume : String= "",
    )
