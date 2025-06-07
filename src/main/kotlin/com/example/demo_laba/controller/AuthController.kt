package com.example.demo_laba.controller

import com.example.demo_laba.dtos.LoginDTO
import com.example.demo_laba.dtos.Message
import com.example.demo_laba.dtos.RegisterDTO
import com.example.demo_laba.model.User
import com.example.demo_laba.service.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.http.HttpResponse
import java.security.KeyPair
import java.util.Date

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(private val userService: UserService) {

    @PostMapping("register")
    fun register(@RequestBody body: RegisterDTO): ResponseEntity<User> {
        val user = User()
        user.name = body.name
        user.email = body.email
        user.password = body.password

        return ResponseEntity.ok(this.userService.save(user))
    }

    @PostMapping("login")
    fun login(@RequestBody body: LoginDTO, response: HttpServletResponse): ResponseEntity<Any> {
        val user = this.userService.findByEmail(body.email)
            ?: return ResponseEntity.badRequest().body(Message("User not found"))

        if (user.comparePassword(body.password)) {
            return ResponseEntity.badRequest().body(Message("Passwords do not match"))
        }

        val issuer = user.id.toString()

       // val keyPair: KeyPair = Keys.keyPairFor(SIG.ES512)
        val jwt = Jwts.builder()
            .setIssuer(issuer)
            .setExpiration(Date(System.currentTimeMillis() + 24*60*60*1000))
            .signWith(SignatureAlgorithm.ES512, "secret").compact()

        val cookie = Cookie("jwt", jwt)
        cookie.isHttpOnly = true

        response.addCookie(cookie)


        return ResponseEntity.ok(user)
    }
}