package br.com.sekai.book_exchange.repository

import br.com.sekai.book_exchange.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface UserRepository : JpaRepository<User?, Long?>{

    @Query("SELECT u FROM User u WHERE u.email =:email")
    fun findByEmail(@Param("email") email: String?): User?

//    @Query("INSERT INTO User (name,email, password,accountNonExpired, accountNonLocked,credentialsNonExpired,enabled) VALUES (:user.name,:user.email,:user.password,:1,:1,:1,:1")
//    fun createNewUser(@Param("user") user: User?): User?
}
