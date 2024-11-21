package com.example.invoice_proyect.controller

import com.example.invoice_proyect.dto.LeadDto
import com.example.invoice_proyect.entity.Lead
import com.example.invoice_proyect.response.ErrorResponse
import com.example.invoice_proyect.response.FailResponse
import com.example.invoice_proyect.response.SuccessResponse
import com.example.invoice_proyect.service.LeadService
import jakarta.persistence.EntityNotFoundException
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/lead")
class LeadController {

    @Autowired
    lateinit var leadService: LeadService

    // Obtener todos los leads
    @GetMapping
    fun findAll(): ResponseEntity<Any> {
        return try {
            val leads = leadService.findAll()
            leads?.let {
                ResponseEntity(SuccessResponse(data = leads), HttpStatus.OK)
            } ?: ResponseEntity(FailResponse(data = "Leads no encontrados"), HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            ResponseEntity(ErrorResponse(message = "Error al obtener los leads", code = 500), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    // Obtener un lead por su ID
    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            val lead = leadService.findById(id)
            ResponseEntity(SuccessResponse(data = lead), HttpStatus.OK)
        } catch (e: EntityNotFoundException) {
            ResponseEntity(FailResponse(data = e.message ?: "Lead no encontrado"), HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            ResponseEntity(ErrorResponse(message = "Error al obtener el lead", code = 500), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }


    // Crear un nuevo lead
    @PostMapping
    fun create(@RequestBody @Valid leadDto: LeadDto): ResponseEntity<Any> {
        return try {
            val lead = leadService.save(leadDto)
            ResponseEntity(SuccessResponse(data = lead), HttpStatus.CREATED)
        } catch (e: Exception) {
            ResponseEntity(ErrorResponse(message = "Error al crear el lead", code = 500), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    // Actualizar un lead existente
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody @Valid leadDto: LeadDto): ResponseEntity<Any> {
        return try {
            val lead = leadService.updateLead(id, leadDto)
            ResponseEntity(SuccessResponse(data = lead), HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(ErrorResponse(message = "Error al actualizar el lead", code = 500), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    // Eliminar un lead
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            leadService.deleteLead(id)
            ResponseEntity(SuccessResponse(data = "Lead eliminado correctamente"), HttpStatus.OK)
        } catch (e: EntityNotFoundException) {
            ResponseEntity(FailResponse(data = e.message ?: "Lead no encontrado"), HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            ResponseEntity(ErrorResponse(message = "Error al eliminar el lead", code = 500), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}
