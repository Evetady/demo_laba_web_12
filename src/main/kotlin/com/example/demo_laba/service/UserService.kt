package com.example.demo_laba.service

import com.example.demo_laba.DataSource.UserRepository
import com.example.demo_laba.model.User
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun save(user: User): User {
        return this.userRepository.save(user)
    }

    fun findByEmail(email: String): User? {
        return this.userRepository.findByEmail(email)
    }
}