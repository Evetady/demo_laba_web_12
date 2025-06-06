package com.example.demo_laba.model

data class Bank(
    val accountNumber: String,
    val trust: Double,
    val transactionFee: Int
)

data class BankPatchRequest(
    val trust: Double?,
    val transactionFee: Int?
)
