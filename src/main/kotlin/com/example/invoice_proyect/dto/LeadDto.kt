package com.example.invoice_proyect.dto
import jakarta.validation.constraints.NotBlank

import jakarta.validation.constraints.Email
import java.time.LocalDate

class LeadDto {
    var id: Long? = null
    @NotBlank(message = "Name cannot be blank")
    var name: String? = null
    @Email(message = "Invalid email format")
    var email: String? = null

    var phone: String? = null

    var createdAt: LocalDate? = null

    var updatedAt: LocalDate? = null
}
