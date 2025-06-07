package com.example.demo_laba

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class DemoLabaApplication

fun main(args: Array<String>) {
	runApplication<DemoLabaApplication>(*args)
}