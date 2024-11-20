package com.example.invoice_proyect.dto

import com.example.invoice_proyect.entity.Lead
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.LocalDateTime

class LeadDto {
    var id: Long? = null

    @NotNull(message = "Name is required")
    @NotBlank(message = "Name cannot be blank")
    var name: String? = null

    @NotNull(message = "Email is required")
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format") // Validación específica para correo electrónico
    var email: String? = null

    @Size(max = 50, message = "Phone number cannot exceed 50 characters")
    var phone: String? = null

    var status: Lead.LeadStatus = Lead.LeadStatus.NEW

    var createdAt: LocalDateTime? = null

    var updatedAt: LocalDateTime? = null
}
