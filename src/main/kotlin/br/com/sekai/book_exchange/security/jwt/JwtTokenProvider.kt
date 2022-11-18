package br.com.sekai.book_exchange.security.jwt

import br.com.sekai.book_exchange.data.vo.v1.TokenVO
import br.com.sekai.book_exchange.exceptions.InvalidJwtAuthenticationException
import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import jakarta.annotation.PostConstruct
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*


@Service
class JwtTokenProvider {

    @Value("\${security.jwt.token.secret-key}")
    private var secretKey = "secret"

    @Value("\${security.jwt.token.expire-length: 3600000}")
    private var validituInMilliseconds: Long = 3_600_000 //1hr

    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    private lateinit var  algorithm: Algorithm

    @PostConstruct
    protected fun init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.toByteArray())
        algorithm = Algorithm.HMAC256(secretKey.toByteArray())
    }

    fun createAccessToken(email: String, roles: List<String?>): TokenVO {

        val now = Date()
        val validity = Date(now.time + validituInMilliseconds)
        val accessToken = getAcessToken(email, roles, now, validity)
        val refreshToken = getRefreshToken(email, roles, now)


        return TokenVO(
            email = email,
            authenticated = true,
            accessToken = accessToken,
            refreshToken = refreshToken,
            created = now,
            expiration = validity
        )
    }
    private fun getAcessToken(email: String, roles: List<String?>, now: Date, validity: Date): String {
        val issuerURL : String = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString()
        return JWT.create()
            .withClaim("roles", roles)
            .withIssuedAt(now)
            .withExpiresAt(validity)
            .withSubject(email)
            .withIssuer(issuerURL)
            .sign(algorithm)
            .trim()
    }

    private fun getRefreshToken(email: String, roles: List<String?>, now: Date): String {
        val validityRefreshToken = Date(now.time + validituInMilliseconds * 3)
        return JWT.create()
            .withClaim("roles", roles)
            .withExpiresAt(validityRefreshToken)
            .withSubject(email)
            .sign(algorithm)
            .trim()
    }

    fun getAuthentication(token: String): Authentication{
        val decodedJWT : DecodedJWT = decodedToken(token)
        val userdetails: UserDetails = userDetailsService.loadUserByUsername(decodedJWT.subject)
        return UsernamePasswordAuthenticationToken(userdetails, "", userdetails.authorities)
    }

    private fun decodedToken(token: String): DecodedJWT {
        val algorithm = Algorithm.HMAC256(secretKey.toByteArray())
        val verifier: JWTVerifier = JWT.require(algorithm).build()
        return verifier.verify(token)
    }

    fun resolveToken (req: HttpServletRequest): String?{
        val bearerToken = req.getHeader("Authorization")

            return if (!bearerToken.isNullOrBlank() && bearerToken.startsWith("Bearer ")){
                bearerToken.substring("Bearer ".length)
            } else null
    }

    fun validateToken (token: String): Boolean {
        val decodedJWT = decodedToken(token)
        try {
            if (decodedJWT.expiresAt.before(Date())) return false
            return true
        }catch (e: Exception){
            throw InvalidJwtAuthenticationException("Expired or Invalid Token")
        }
    }


}
