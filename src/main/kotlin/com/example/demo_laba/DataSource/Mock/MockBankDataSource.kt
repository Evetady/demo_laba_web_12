package com.example.demo_laba.DataSource.Mock

import com.example.demo_laba.DataSource.BankDataSource
import com.example.demo_laba.model.Bank
import com.example.demo_laba.model.BankPatchRequest
import org.springframework.stereotype.Repository

@Repository
class MockBankDataSource : BankDataSource {

    private  val banks = mutableListOf(
        Bank("1234",3.14,17),
        Bank("1010",17.0,0),
        Bank("5678",0.0,100),
    )

    override fun retrieveBanks(): Collection<Bank> = banks

    override fun retrieveBank(accountNumber: String): Bank? {
        return banks.firstOrNull { it.accountNumber == accountNumber }
    }

    override fun addBank(bank: Bank): Bank {
        require(banks.none { it.accountNumber == bank.accountNumber }) {
            "Bank with account number ${bank.accountNumber} already exists"
        }
        banks.add(bank)
        return bank
    }

    override fun updateBank(accountNumber: String, patchRequest: BankPatchRequest): Bank? {
        val bank = banks.firstOrNull { it.accountNumber == accountNumber } ?: return null

        val updatedBank = bank.copy(
            trust = patchRequest.trust ?: bank.trust,
            transactionFee = patchRequest.transactionFee ?: bank.transactionFee
        )

        banks.replaceAll { if (it.accountNumber == accountNumber) updatedBank else it }
        return updatedBank
    }

    // Добавлен новый метод для удаления
    fun deleteBank(accountNumber: String): Boolean {
        return banks.removeIf { it.accountNumber == accountNumber }
    }
}