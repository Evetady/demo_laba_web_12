package com.example.demo_laba.DataSource

import com.example.demo_laba.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.http.ResponseEntity

interface UserRepository: JpaRepository<User, Int> {
    fun findByEmail(email: String): User?
}