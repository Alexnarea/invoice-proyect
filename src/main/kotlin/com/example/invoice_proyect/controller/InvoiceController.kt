package com.example.invoice_proyect.controller

import com.example.invoice_proyect.dto.InvoiceDto
import com.example.invoice_proyect.response.ErrorResponse
import com.example.invoice_proyect.response.FailResponse
import com.example.invoice_proyect.response.SuccessResponse
import com.example.invoice_proyect.service.InvoiceService
import jakarta.persistence.EntityNotFoundException
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/invoices")
class InvoiceController {

    @Autowired
    lateinit var invoiceService: InvoiceService

    // Obtener todas las facturas
    @GetMapping
    fun getAllInvoices(): ResponseEntity<Any> {
        return try {
            val invoices = invoiceService.findAll()
            ResponseEntity(SuccessResponse(data = invoices), HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(ErrorResponse(message = "Error al obtener las facturas", code = 500), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    // Obtener una factura por ID
    @GetMapping("/{id}")
    fun getInvoiceById(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            val invoice = invoiceService.findById(id)
            ResponseEntity(SuccessResponse(data = invoice), HttpStatus.OK)
        } catch (e: EntityNotFoundException) {
            ResponseEntity(FailResponse(data = e.message ?: "Factura no encontrada"), HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            ResponseEntity(ErrorResponse(message = "Error al obtener la factura", code = 500), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    // Crear una nueva factura
    @PostMapping
    fun createInvoice(@RequestBody @Valid invoiceDto: InvoiceDto): ResponseEntity<Any> {
        return try {
            val invoice = invoiceService.save(invoiceDto)
            ResponseEntity(SuccessResponse(data = invoice), HttpStatus.CREATED)
        } catch (e: EntityNotFoundException) {
            ResponseEntity(FailResponse(data = e.message ?: "Cliente no encontrado"), HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            ResponseEntity(ErrorResponse(message = "Error al crear la factura", code = 500), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    // Actualizar una factura existente
    @PutMapping("/{id}")
    fun updateInvoice(@PathVariable id: Long, @RequestBody @Valid invoiceDto: InvoiceDto): ResponseEntity<Any> {
        return try {
            val updatedInvoice = invoiceService.updateInvoice(id, invoiceDto)
            ResponseEntity(SuccessResponse(data = updatedInvoice), HttpStatus.OK)
        } catch (e: EntityNotFoundException) {
            ResponseEntity(FailResponse(data = e.message ?: "Factura no encontrada"), HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            ResponseEntity(ErrorResponse(message = "Error al actualizar la factura", code = 500), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    // Eliminar una factura
    @DeleteMapping("/{id}")
    fun deleteInvoice(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            invoiceService.deleteInvoice(id)
            ResponseEntity(SuccessResponse(data = "Factura eliminada correctamente"), HttpStatus.OK)
        } catch (e: EntityNotFoundException) {
            ResponseEntity(FailResponse(data = e.message ?: "Factura no encontrada"), HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            ResponseEntity(ErrorResponse(message = "Error al eliminar la factura", code = 500), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}
