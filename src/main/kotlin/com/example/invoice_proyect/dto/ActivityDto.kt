package com.example.invoice_proyect.dto

import com.example.invoice_proyect.entity.Activity
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

class ActivityDto {
    var id: Long? = null

    @NotNull(message = "Type is required")
    var type: Activity.ActivityType? = null

    var description: String? = null

    var scheduledAt: LocalDate? = null

    var status: Activity.ActivityStatus = Activity.ActivityStatus.PENDING

    var createdAt: LocalDate? = null

    var updatedAt: LocalDate? = null

    @NotNull(message = "Lead ID is required")
    var leadId: Long? = null
}
