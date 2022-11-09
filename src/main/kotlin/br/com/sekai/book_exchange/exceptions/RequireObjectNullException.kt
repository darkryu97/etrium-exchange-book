package br.com.sekai.book_exchange.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class RequireObjectNullException: RuntimeException {
    constructor(): super("It is not allowed to persist a null object")
    constructor(exception: String?): super(exception)
}
