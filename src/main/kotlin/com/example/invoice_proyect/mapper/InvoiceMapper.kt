package com.example.invoice_proyect.mapper

import com.example.invoice_proyect.dto.InvoiceDto
import com.example.invoice_proyect.entity.Client
import com.example.invoice_proyect.entity.Invoice
import org.springframework.stereotype.Component

@Component
object InvoiceMapper {

    fun toEntity(invoiceDto: InvoiceDto, client: Client): Invoice {
        val invoice = Invoice()
        invoice.code = invoiceDto.code
        invoice.total = invoiceDto.total
        invoice.client = client
        return invoice
    }

    fun toDto(invoice: Invoice): InvoiceDto {
        val invoiceDto = InvoiceDto()
        invoiceDto.id = invoice.id
        invoiceDto.code = invoice.code
        invoiceDto.total = invoice.total
        invoiceDto.createdAt = invoice.createdAt
        invoiceDto.clientId = invoice.client?.id
        return invoiceDto
    }
}