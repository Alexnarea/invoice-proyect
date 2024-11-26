package com.example.invoice_proyect.controller
import com.example.invoice_proyect.dto.ClientDto
import com.example.invoice_proyect.response.ErrorResponse
import com.example.invoice_proyect.response.FailResponse
import com.example.invoice_proyect.response.SuccessResponse
import com.example.invoice_proyect.service.ClientService
import jakarta.persistence.EntityNotFoundException
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/client")
class ClientController {

    @Autowired
    lateinit var clientService: ClientService

    @GetMapping
    fun findAll(): ResponseEntity<Any>{
        return try {
            val client = clientService.findAll()
            client?.let {
                ResponseEntity(SuccessResponse(data = client), HttpStatus.OK)
            }?: ResponseEntity(FailResponse(data = "Clientes no encontrados"), HttpStatus.NOT_FOUND)
        } catch (e: Exception){
            ResponseEntity(ErrorResponse(message = "Erro al ontener los clientes" , code = 500), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            val client = clientService.findById(id)
            ResponseEntity(SuccessResponse(data = client), HttpStatus.OK)
        } catch (e: EntityNotFoundException) {
            ResponseEntity(FailResponse(data = e.message ?: "Cliente no encontrado"), HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            ResponseEntity(ErrorResponse(message = "Error al obtener el cliente", code = 500), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/{id}/invoices")
    fun findInvoicesByClientId(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            val invoices = clientService.findInvoicesByClientId(id)
            if (invoices.isNotEmpty()) {
                ResponseEntity(SuccessResponse(data = invoices), HttpStatus.OK)
            } else {
                ResponseEntity(FailResponse(data = "No se encontraron facturas para el cliente con ID $id"), HttpStatus.NOT_FOUND)
            }
        } catch (e: EntityNotFoundException) {
            ResponseEntity(FailResponse(data = e.message ?: "Cliente no encontrado"), HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            ResponseEntity(ErrorResponse(message = "Error al obtener las facturas del cliente", code = 500), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping
    fun create(@RequestBody @Valid clientDto: ClientDto): Any {
        return try {
            val client = clientService.save(clientDto)
            SuccessResponse(data = client)
        }  catch (ex:Exception){
            ErrorResponse(message = "Error al crear el cliente", code = 500)
        }
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody @Valid clientDto: ClientDto): Any {
        return try {
            val client = clientService.updateClient(id, clientDto)
            SuccessResponse(data = client)
        }  catch (e: Exception){
            ErrorResponse(message = "Error al update cliente", code = 500)
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            clientService.deleteClient(id)
            ResponseEntity(SuccessResponse(data = "Cliente eliminado correctamente"), HttpStatus.OK)
        } catch (e: EntityNotFoundException) {
            ResponseEntity(FailResponse(data = e.message ?: "Cliente no encontrado"), HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            ResponseEntity(ErrorResponse(message = "Error al eliminar el cliente", code = 500), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}
