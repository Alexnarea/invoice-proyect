package com.example.invoice_proyect.service
import com.example.invoice_proyect.dto.LeadDto
import com.example.invoice_proyect.entity.Activity
import com.example.invoice_proyect.entity.Invoice
import com.example.invoice_proyect.mapper.LeadMapper
import com.example.invoice_proyect.repository.ActivityRepository
import com.example.invoice_proyect.repository.LeadRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LeadService {

    @Autowired
    private lateinit var activityRepository: ActivityRepository

    @Autowired
    lateinit var leadRepository: LeadRepository

    @Autowired
    lateinit var leadMapper: LeadMapper

    // Obtener todos los leads
    fun findAll(): List<LeadDto> {
        val leads = leadRepository.findAll()
        return leads.map { leadMapper.toDto(it) }
    }

    // Obtener un lead por su ID
    fun findById(id: Long): LeadDto {
        val lead = leadRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Lead with ID $id not found") }
        return leadMapper.toDto(lead)
    }


    // Guardar un nuevo lead
    fun save(leadDto: LeadDto): LeadDto {
        val lead = leadMapper.toEntity(leadDto)
        val savedLead = leadRepository.save(lead)
        return leadMapper.toDto(savedLead)
    }

    // Actualizar un lead existente
    fun updateLead(id: Long, leadDto: LeadDto): LeadDto {
        val lead = leadRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Lead with ID $id not found") }

        lead.apply {
            name = leadDto.name
            email = leadDto.email
            phone = leadDto.phone
            status = leadDto.status
            updatedAt = leadDto.updatedAt ?: updatedAt // Si updatedAt es null, mantiene el valor actual
        }

        val updatedLead = leadRepository.save(lead)
        return leadMapper.toDto(updatedLead)
    }

    // Eliminar un lead
    fun deleteLead(id: Long) {
        val lead = leadRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Lead with ID $id not found") }
        leadRepository.delete(lead)
    }
}
