package com.example.invoice_proyect.service

import com.example.invoice_proyect.dto.InvoiceDto
import com.example.invoice_proyect.mapper.InvoiceMapper
import com.example.invoice_proyect.repository.ClientRepository
import com.example.invoice_proyect.repository.InvoiceRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class InvoiceService {

    @Autowired
    private lateinit var clientRepository: ClientRepository

    @Autowired

    lateinit var invoiceRepository: InvoiceRepository

    @Autowired

    lateinit var invoiceMapper: InvoiceMapper


    fun findAll(): List<InvoiceDto> {
        val invoices = invoiceRepository.findAll()
        return invoices.map {invoiceMapper.toDto(it)}
    }

    fun findById(id:Long): InvoiceDto {
        val invoice = invoiceRepository.findById(id)
            .orElseThrow {EntityNotFoundException("Invoice with id $id not found") }
        return invoiceMapper.toDto(invoice)
    }

    fun save(invoiceDto: InvoiceDto): InvoiceDto {
        val client = clientRepository.findById(invoiceDto.clientId!!)
            .orElseThrow { EntityNotFoundException("Client not found with ID: ${invoiceDto.clientId}") }
        val invoice = invoiceMapper.toEntity(invoiceDto, client)
        return invoiceMapper.toDto(invoiceRepository.save(invoice))
    }

    fun updateInvoice(id: Long, invoiceDto: InvoiceDto): InvoiceDto {
        val invoice = invoiceRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Invoice not found with ID $id") }

        // Actualizar los campos permitidos
        invoice.code = invoiceDto.code
        invoice.total = invoiceDto.total

        return invoiceMapper.toDto(invoiceRepository.save(invoice))
    }

    fun deleteInvoice(id: Long) {
        if (!invoiceRepository.existsById(id)) {
            throw EntityNotFoundException("Invoice not found with ID $id")
        }
        invoiceRepository.deleteById(id)
    }
}
