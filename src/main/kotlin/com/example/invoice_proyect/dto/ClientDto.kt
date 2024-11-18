package com.example.invoice_proyect.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

class ClientDto {
    @NotNull(message = "Name is required")
    @NotBlank(message = "Name cannot be blank")
    var fullName: String? = null

    @NotNull(message = "Age is required")
    var age: Int? = null

    @NotNull(message = "Address cannot be blank")
    @NotBlank(message = "Address cannot be blank")
    var address: String? = null
}