package com.example.demo_laba.service

import com.example.demo_laba.DataSource.BankDataSource
import com.example.demo_laba.DataSource.Mock.MockBankDataSource
import com.example.demo_laba.model.Bank
import com.example.demo_laba.model.BankPatchRequest
import org.springframework.stereotype.Service

@Service
class BankService(private val dataSource: BankDataSource){

    fun getBanks() : Collection<Bank> = dataSource.retrieveBanks()

    fun getBank(accountNumber: String): Bank {
        return dataSource.retrieveBank(accountNumber)
            ?: throw NoSuchElementException("Bank with account number $accountNumber not found")
    }

    fun createBank(bank: Bank): Bank {
        require(bank.trust >= 0) { "Trust value cannot be negative" }
        require(bank.transactionFee >= 0) { "Transaction fee cannot be negative" }
        return dataSource.addBank(bank)
    }

    fun updateBank(accountNumber: String, patchRequest: BankPatchRequest): Bank {
        patchRequest.trust?.let { require(it >= 0) { "Trust value cannot be negative" } }
        patchRequest.transactionFee?.let { require(it >= 0) { "Transaction fee cannot be negative" } }

        return dataSource.updateBank(accountNumber, patchRequest)
            ?: throw NoSuchElementException("Bank with account number $accountNumber not found")
    }

    fun deleteBank(accountNumber: String) {
        if (dataSource is MockBankDataSource) {
            if (!dataSource.deleteBank(accountNumber)) {
                throw NoSuchElementException("Bank with account number $accountNumber not found")
            }
        } else {
            throw UnsupportedOperationException("Delete operation not supported for this data source")
        }
    }
}