package br.com.sekai.book_exchange.repository

import br.com.sekai.book_exchange.model.Posts
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface PostsRepository : JpaRepository<Posts, Long?>
