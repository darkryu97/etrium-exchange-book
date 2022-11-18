package br.com.sekai.book_exchange.service

import br.com.sekai.book_exchange.data.vo.v1.AccountCredentialsVO
import br.com.sekai.book_exchange.data.vo.v1.TokenVO
import br.com.sekai.book_exchange.repository.UserRepository
import br.com.sekai.book_exchange.security.jwt.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class AuthService {
    @Autowired
    private lateinit var tokenProvider: JwtTokenProvider

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var repository: UserRepository

    private val logger = Logger.getLogger(AuthService::class.java.name)

    fun sign(data: AccountCredentialsVO): ResponseEntity<*>{
        logger.info("Trying log user ${data.email}")
        return try {
            val email = data.email
            val password = data.password
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(email, password))
            val user = repository.findByEmail(email)
            val tokenResponse : TokenVO = if(user != null){
                tokenProvider.createAccessToken(email, user.role)
            }else{
                throw UsernameNotFoundException("User with $email not found!")
            }
            ResponseEntity.ok(tokenResponse)

        }catch (e: AuthenticationException){
            throw BadCredentialsException("Invalid email or password!")
        }
    }


}
