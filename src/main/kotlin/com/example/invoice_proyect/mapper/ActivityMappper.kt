package com.example.invoice_proyect.mapper

import com.example.invoice_proyect.dto.ActivityDto
import com.example.invoice_proyect.entity.Activity
import com.example.invoice_proyect.entity.Lead
import org.springframework.stereotype.Component

@Component
object ActivityMappper {
    fun toEntity(activityDto: ActivityDto, lead: Lead): Activity {
        val activity = Activity()
        activity.description = activityDto.description
        activity.lead = lead
        return activity
    }

    fun toDto(activity: Activity): ActivityDto {
        val activityDto = ActivityDto()
        activityDto.id = activity.id
        activityDto.description = activity.description
        activityDto.scheduledAt = activity.scheduledAt
        activityDto.createdAt = activity.createdAt
        activity.updatedAt = activity.updatedAt
        activityDto.leadId = activity.lead?.id
        return activityDto
    }
}