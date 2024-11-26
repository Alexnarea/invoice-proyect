package com.example.invoice_proyect.mapper
import com.example.invoice_proyect.dto.LeadDto
import com.example.invoice_proyect.entity.Lead
import org.springframework.stereotype.Component

@Component
object LeadMapper {

    fun toEntity(leadDto: LeadDto): Lead {
        val lead = Lead()
        lead.name = leadDto.name
        lead.email = leadDto.email
        lead.phone = leadDto.phone
        return lead
    }

    fun toDto(lead: Lead): LeadDto {
        val leadDto = LeadDto()
        leadDto.id = lead.id
        leadDto.name = lead.name
        leadDto.email = lead.email
        leadDto.phone = lead.phone
        leadDto.createdAt = lead.createdAt
        leadDto.updatedAt = lead.updatedAt
        return leadDto
    }
}
