package com.example.invoice_proyect.dto
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

class ActivityDto {
    var id: Long? = null

    @NotNull(message = "Type is required")

    var description: String? = null

    var scheduledAt: LocalDate? = null


    var createdAt: LocalDate? = null

    var updatedAt: LocalDate? = null

    @NotNull(message = "Lead ID is required")
    var leadId: Long? = null
}
