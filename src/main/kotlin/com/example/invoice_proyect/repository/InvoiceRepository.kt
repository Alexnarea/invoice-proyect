package com.example.invoice_proyect.repository

import com.example.invoice_proyect.entity.Invoice
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface InvoiceRepository: JpaRepository<Invoice, Long> {
    fun findById(id: Long?): Invoice?
    fun findByClientId(clientId: Long): List<Invoice>
}