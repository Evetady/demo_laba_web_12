package com.example.demo_laba.controller

import com.example.demo_laba.model.Bank
import com.example.demo_laba.model.BankPatchRequest
import com.example.demo_laba.service.BankService
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/banks")
class BankController(private val service: BankService) {

    @GetMapping
    fun getAllBanks(): ResponseEntity<Collection<Bank>> {
        val banks = service.getBanks()
        return ResponseEntity.ok(banks)
    }

    @GetMapping("/{accountNumber}")
    fun getBank(@PathVariable accountNumber: String): ResponseEntity<Bank> {
        return ResponseEntity.ok(service.getBank(accountNumber))
    }

    @PostMapping
    fun addBank(@RequestBody bank: Bank): ResponseEntity<Bank> {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createBank(bank))
    }

    @PatchMapping("/{accountNumber}")
    fun updateBank(
        @PathVariable accountNumber: String,
        @RequestBody patchRequest: BankPatchRequest
    ): ResponseEntity<Bank> {
        return ResponseEntity.ok(service.updateBank(accountNumber, patchRequest))
    }

    @DeleteMapping("/{accountNumber}")
    fun deleteBank(@PathVariable accountNumber: String): ResponseEntity<Void> {
        service.deleteBank(accountNumber)
        return ResponseEntity.noContent().build()
    }
}