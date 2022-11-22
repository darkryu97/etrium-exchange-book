package br.com.sekai.book_exchange

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Startup

fun main(args: Array<String>) {
    runApplication<Startup>(*args)
//
//    /
//    val encoders: MutableMap<String, PasswordEncoder> = HashMap()
//    encoders["pbkdf2"] = Pbkdf2PasswordEncoder()
//    val passwordEncoder = DelegatingPasswordEncoder("pbkdf2", encoders)
//    passwordEncoder.setDefaultPasswordEncoderForMatches(Pbkdf2PasswordEncoder())
//
//    val result = passwordEncoder.encode("foo-bar")
//    println("My hash $result")

}
