package br.com.sekai.book_exchange.service

import br.com.sekai.book_exchange.data.vo.v1.AccountCredentialsVO
import br.com.sekai.book_exchange.data.vo.v1.TokenVO
import br.com.sekai.book_exchange.data.vo.v1.UserVO
import br.com.sekai.book_exchange.mapper.toCreateEntity
import br.com.sekai.book_exchange.mapper.toVOnoPassword
import br.com.sekai.book_exchange.model.User
import br.com.sekai.book_exchange.repository.UserRepository
import br.com.sekai.book_exchange.security.jwt.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.DelegatingPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder
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

//    logar
    fun sign(data: AccountCredentialsVO): ResponseEntity<*>{
        logger.info("Trying log user ${data.email}")
        return try {
            val email = data.email
            val password = data.password
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(email, password))
            val user = repository.findByEmail(email)
            val tokenResponse : TokenVO = if(user != null){
                tokenProvider.createAccessToken(email!!, user.role)
            }else{
                throw UsernameNotFoundException("User with $email not found!")
            }
            ResponseEntity.ok(tokenResponse)

        }catch (e: AuthenticationException){
            logger.info(data.password)
            throw BadCredentialsException("Invalid email or password!")
        }
    }

//    cadastrar
    fun signUp(userVO: UserVO): ResponseEntity<*> {
        logger.info("Trying create new user with ${userVO.email}")
//    val userCredentialsVO = AccountCredentialsVO(userVO.email,userVO.password)
    val encoders: MutableMap<String, PasswordEncoder> = HashMap()
    encoders["pbkdf2"] = Pbkdf2PasswordEncoder()
    val passwordEncoder = DelegatingPasswordEncoder("pbkdf2", encoders)
    passwordEncoder.setDefaultPasswordEncoderForMatches(Pbkdf2PasswordEncoder())

    userVO.password = passwordEncoder.encode(userVO.password)

        val user: User = repository.save(userVO.toCreateEntity())
        logger.info("user Create successfully ${user.email}")


        return  ResponseEntity.ok("user Create successfully ${user.email}")
    }

    fun refreshToken(email: String, refreshToken: String): ResponseEntity<*>{
        logger.info("Trying get refresh token to user $email")

        val user = repository.findByEmail(email)
        val tokenResponse: TokenVO = if(user !=null){
            tokenProvider.refreshToken(refreshToken)
        }else throw UsernameNotFoundException("invalid refresh token")

        return ResponseEntity.ok(tokenResponse)
    }

    fun getUserByEmail(email: String): UserVO {
        logger.info("get user by email");
        return repository.findByEmail(email)?.toVOnoPassword() ?: throw UsernameNotFoundException("User not found")

    }

}
