package com.example.invoice_proyect.controller
import com.example.invoice_proyect.dto.ClientDto
import com.example.invoice_proyect.response.ErrorResponse
import com.example.invoice_proyect.response.FailResponse
import com.example.invoice_proyect.response.SuccessResponse
import com.example.invoice_proyect.service.ClientService
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
    fun getAllClient(): ResponseEntity<Any>{
        return try {
            val client = clientService.getAllClient()
            client?.let {
                ResponseEntity(SuccessResponse(data = client), HttpStatus.OK)
            }?: ResponseEntity(FailResponse(data = "Clientes no encontrados"), HttpStatus.NOT_FOUND)
        } catch (e: Exception){
            ResponseEntity(ErrorResponse(message = "Erro al ontener los clientes" , code = 500), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/{id}")
    fun getClientById(@PathVariable id: Long): Any{
        return try {
            val client = clientService.getClientById(id)
            client?.let {
                SuccessResponse(data = client)
            } ?: FailResponse(data = "Trabajador no encontrado")
        } catch (e: Exception) {
            ErrorResponse(message = "Error al obtener el cliente", code = 500)
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
    fun delete(@PathVariable id: Long): Any {
        return try {
            clientService.deleteClient(id)
            SuccessResponse(data = "Cliente eliminado correctamente")
        }  catch (e: Exception){
            ErrorResponse(message = "Error al eliminar el cliente", code = 500)
        }
    }
}