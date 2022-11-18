package br.com.sekai.book_exchange.controller

import br.com.sekai.book_exchange.data.vo.v1.AccountCredentialsVO
import br.com.sekai.book_exchange.service.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.websocket.server.PathParam

//@Tag(name = "Authentication Endpoint")
@RestController
@RequestMapping("/api/auth")
class AuthController {
    @Autowired
    lateinit var authService: AuthService

    @PostMapping(value = ["/sign"])
    fun sign(@RequestBody data: AccountCredentialsVO?): ResponseEntity<*> {
        return if (data!!.email.isNullOrBlank() || data.password.isNullOrBlank())
            ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Client Request")
        else authService.sign(data)

    }
    @PutMapping(value = ["/refresh/{email}"])
    fun refreshToken(@PathVariable("email") email: String?,
                     @RequestHeader("Authorization") refreshToken: String? ): ResponseEntity<*> {
        return if (email.isNullOrBlank() || refreshToken.isNullOrBlank())
            ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Client Request")
        else authService.refreshToken(email,refreshToken!!)

    }
}
