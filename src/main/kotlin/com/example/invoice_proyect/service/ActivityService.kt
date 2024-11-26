package com.example.invoice_proyect.service
import com.example.invoice_proyect.dto.ActivityDto
import com.example.invoice_proyect.entity.Activity
import com.example.invoice_proyect.mapper.ActivityMappper
import com.example.invoice_proyect.repository.ActivityRepository
import com.example.invoice_proyect.repository.LeadRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ActivityService {

    @Autowired
    lateinit var activityRepository: ActivityRepository

    @Autowired
    lateinit var leadRepository: LeadRepository

    @Autowired
    lateinit var activityMapper: ActivityMappper

    // Obtener todas las actividades
    fun findAll(): List<ActivityDto> {
        val activities = activityRepository.findAll()
        return activities.map { activityMapper.toDto(it) }
    }

    // Obtener una actividad por su ID
    fun findById(id: Long): ActivityDto {
        val activity = activityRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Activity with ID $id not found") }
        return activityMapper.toDto(activity)
    }

    fun findActivityByLeadId(id: Long): List<Activity> {
        return activityRepository.findByLeadId(id)
    }
    // Guardar una nueva actividad
    fun save(activityDto: ActivityDto): ActivityDto {
        val lead = leadRepository.findById(activityDto.leadId!!)
            .orElseThrow { EntityNotFoundException("Lead not found with ID: ${activityDto.leadId}") }
        val activity = activityMapper.toEntity(activityDto, lead)
        return activityMapper.toDto(activityRepository.save(activity))
    }

    // Actualizar una actividad existente
    fun updateActivity(id: Long, activityDto: ActivityDto): ActivityDto {
        val activity = activityRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Activity not found with ID $id") }
            activity.description = activityDto.description
        return activityMapper.toDto(activityRepository.save(activity))
    }

    // Eliminar una actividad
    fun deleteActivity(id: Long) {
        if (!activityRepository.existsById(id)) {
            throw EntityNotFoundException("Activity not found with ID $id")
        }
        activityRepository.deleteById(id)
    }
}
