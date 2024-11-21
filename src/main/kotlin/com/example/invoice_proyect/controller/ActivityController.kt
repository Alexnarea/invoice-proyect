package com.example.invoice_proyect.controller

import com.example.invoice_proyect.dto.ActivityDto
import com.example.invoice_proyect.response.ErrorResponse
import com.example.invoice_proyect.response.FailResponse
import com.example.invoice_proyect.response.SuccessResponse
import com.example.invoice_proyect.service.ActivityService
import jakarta.persistence.EntityNotFoundException
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/activities")
class ActivityController {

    @Autowired
    lateinit var activityService: ActivityService

    // Obtener todas las actividades
    @GetMapping
    fun getAllActivities(): ResponseEntity<Any> {
        return try {
            val activities = activityService.findAll()
            ResponseEntity(SuccessResponse(data = activities), HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(ErrorResponse(message = "Error al obtener las actividades", code = 500), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    // Obtener una actividad por ID
    @GetMapping("/{id}")
    fun getActivityById(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            val activity = activityService.findById(id)
            ResponseEntity(SuccessResponse(data = activity), HttpStatus.OK)
        } catch (e: EntityNotFoundException) {
            ResponseEntity(FailResponse(data = e.message ?: "Actividad no encontrada"), HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            ResponseEntity(ErrorResponse(message = "Error al obtener la actividad", code = 500), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    // Crear una nueva actividad
    @PostMapping
    fun createActivity(@RequestBody @Valid activityDto: ActivityDto): ResponseEntity<Any> {
        return try {
            val activity = activityService.save(activityDto)
            ResponseEntity(SuccessResponse(data = activity), HttpStatus.CREATED)
        } catch (e: Exception) {
            ResponseEntity(ErrorResponse(message = "Error al crear la actividad", code = 500), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    // Actualizar una actividad existente
    @PutMapping("/{id}")
    fun updateActivity(@PathVariable id: Long, @RequestBody @Valid activityDto: ActivityDto): ResponseEntity<Any> {
        return try {
            val updatedActivity = activityService.updateActivity(id, activityDto)
            ResponseEntity(SuccessResponse(data = updatedActivity), HttpStatus.OK)
        } catch (e: EntityNotFoundException) {
            ResponseEntity(FailResponse(data = e.message ?: "Actividad no encontrada"), HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            ResponseEntity(ErrorResponse(message = "Error al actualizar la actividad", code = 500), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    // Eliminar una actividad
    @DeleteMapping("/{id}")
    fun deleteActivity(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            activityService.deleteActivity(id)
            ResponseEntity(SuccessResponse(data = "Actividad eliminada correctamente"), HttpStatus.OK)
        } catch (e: EntityNotFoundException) {
            ResponseEntity(FailResponse(data = e.message ?: "Actividad no encontrada"), HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            ResponseEntity(ErrorResponse(message = "Error al eliminar la actividad", code = 500), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}
