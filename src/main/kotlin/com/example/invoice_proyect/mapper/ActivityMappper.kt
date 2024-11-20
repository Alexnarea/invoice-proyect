package com.example.invoice_proyect.mapper

import com.example.invoice_proyect.dto.ActivityDto
import com.example.invoice_proyect.entity.Activity
import org.springframework.stereotype.Component

@Component
object ActivityMappper {
    fun toEntity(activityDto: ActivityDto): Activity {
        val activity = Activity()
        activity.type = activityDto.type
        activity.description = activityDto.description
        activity.scheduledAt = activityDto.scheduledAt
        activity.status = activityDto.status
        activity.createdAt = activityDto.updatedAt!!
        activity.updatedAt = activity.updatedAt
        activity.lead = activity.lead
        return activity
    }

    fun toDto(activity: Activity): ActivityDto {
        val activityDto = ActivityDto()
        activityDto.id = activity.id
        activityDto.type = activityDto.type
        activityDto.description = activityDto.description
        activityDto.scheduledAt = activityDto.scheduledAt
        activityDto.status = activityDto.status
        activityDto.createdAt = activityDto.createdAt
        activity.updatedAt = activity.updatedAt
        activityDto.leadId = activity.lead?.id
        return activityDto
    }
}