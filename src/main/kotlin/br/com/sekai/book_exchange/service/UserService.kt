package br.com.sekai.book_exchange.service

import br.com.sekai.book_exchange.data.vo.v1.BookVO
import br.com.sekai.book_exchange.exceptions.ResourceNotFoundException
import br.com.sekai.book_exchange.mapper.DozerMapper
import br.com.sekai.book_exchange.mapper.toArrayVO
import br.com.sekai.book_exchange.model.Book
import br.com.sekai.book_exchange.repository.BookRepository
import br.com.sekai.book_exchange.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class UserService(@field:Autowired var userRepository: UserRepository): UserDetailsService {


    private val logger = Logger.getLogger(UserService::class.java.name)


    override fun loadUserByUsername(email: String?): UserDetails {
        logger.info("FINDING ONE USER WITH EMAIL!! ${email}")
        val user = userRepository.findByEmail(email)
        return user ?: throw UsernameNotFoundException("User with email '$email' not found")
    }


}
