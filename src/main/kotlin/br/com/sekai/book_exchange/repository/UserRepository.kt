package br.com.sekai.book_exchange.repository

import br.com.sekai.book_exchange.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserRepository : JpaRepository<User, Long?>
