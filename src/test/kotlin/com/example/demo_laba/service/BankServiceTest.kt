package com.example.demo_laba.service

import com.example.demo_laba.DataSource.BankDataSource
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test


internal class BankServiceTest {

    private val dataSource: BankDataSource = mockk(relaxed = true)
    private val bankService = BankService(dataSource)

    @Test
    fun `should call its data source to retrieve banks`() {
        // given

        // when
        bankService.getBanks()
        // then
        verify(exactly = 1) {dataSource.retrieveBanks()}
    }

}