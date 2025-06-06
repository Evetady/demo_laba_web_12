package com.example.demo_laba.DataSource

import com.example.demo_laba.model.Bank
import com.example.demo_laba.model.BankPatchRequest

interface BankDataSource {
    fun retrieveBanks(): Collection<Bank>
    fun retrieveBank(accountNumber: String): Bank?
    fun addBank(bank: Bank): Bank
    fun updateBank(accountNumber: String, patchRequest: BankPatchRequest): Bank?
}