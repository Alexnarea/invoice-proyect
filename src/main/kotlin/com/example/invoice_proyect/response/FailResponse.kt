package com.example.invoice_proyect.response

data class FailResponse(
    val status: String = "fail",
    val data: Any? = null
)