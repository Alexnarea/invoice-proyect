package com.example.invoice_proyect.controller

import com.example.invoice_proyect.dto.InvoiceDto
import com.example.invoice_proyect.response.ErrorResponse
import com.example.invoice_proyect.response.FailResponse
import com.example.invoice_proyect.response.SuccessResponse
import com.example.invoice_proyect.service.InvoiceService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration
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

    @GetMapping
    fun getAllInvoice(): SuccessResponse {
        val invoices = invoiceService.getAllInvoices()
        return SuccessResponse(data = invoices)
    }

    @GetMapping("/{id}")
    fun getInvoiceById(@PathVariable id: Long): Any {
        return try {
            val invoice = invoiceService.getInvoiceById(id)
            invoice?.let {
                SuccessResponse(data = invoice)
            } ?: FailResponse(data = "Trabajador no encontrado")
        } catch (e: Exception) {
            ErrorResponse(message = "Error al ontener una invoice", code = 500)
        }
    }

    @PostMapping("/{id}")
    fun create(@RequestBody @Valid invoiceDto: InvoiceDto, @PathVariable id: String): Any {
        return try {
            val invoice = invoiceService.save(invoiceDto)
            SuccessResponse(data = invoice)
        } catch (e: Exception) {
            ErrorResponse(message = "Error al crear una invoice", code = 500)
        }
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody @Valid invoiceDto: InvoiceDto): Any {
        return try {
            val invoice = invoiceService.updateInvoice(id, invoiceDto)
            SuccessResponse(data = invoice)
        }catch (e: Exception){
            ErrorResponse(message = "Error al actualizar el invoice", code = 500)
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): Any{
        return try {
            invoiceService.deleteInvoice(id)
            SuccessResponse(data = "Invoice eliminado correctamente")
        }catch (e: Exception) {
            ErrorResponse(message = "Erro al eliminar el invoice", code = 500)
        }
    }
}
