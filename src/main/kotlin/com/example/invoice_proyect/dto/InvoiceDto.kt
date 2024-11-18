package com.example.invoice_proyect.dto

import jakarta.validation.constraints.NotNull
import java.time.LocalDate

class InvoiceDto {
    @NotNull(message = "Code is required")
    var code: String? = null

    @NotNull(message = "CreatedAt is required")
    var createdAt: LocalDate? = null

    @NotNull(message = "Total is required")
    var total: Double? = null
}