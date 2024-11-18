package com.example.invoice_proyect.mapper

import com.example.invoice_proyect.dto.InvoiceDto
import com.example.invoice_proyect.entity.Invoice
import org.springframework.stereotype.Component

@Component
object InvoiceMapper {

    fun toEntity(invoiceDto: InvoiceDto): Invoice {
        val invoice = Invoice()
        invoice.code = invoiceDto.code
        invoice.createdAt = invoiceDto.createdAt
        invoice.total = invoiceDto.total
        return invoice
    }

    fun toDto(invoice: Invoice): InvoiceDto {
        val invoiceDto = InvoiceDto()
        invoiceDto.code = invoice.code
        invoiceDto.createdAt = invoice.createdAt
        invoiceDto.total = invoice.total
        return invoiceDto
    }
}