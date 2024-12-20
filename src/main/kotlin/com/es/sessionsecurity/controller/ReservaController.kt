package com.es.sessionsecurity.controller

import com.es.sessionsecurity.model.Reserva
import com.es.sessionsecurity.service.ReservaService
import com.es.sessionsecurity.service.SessionService
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/reservas")
class ReservaController {

    @Autowired
    private lateinit var reservaService: ReservaService

    @Autowired
    private lateinit var sessionService: SessionService

    /*
    OBTENER TODAS LAS RESERVAS POR EL NOMBRE DE USUARIO DE UN CLIENTE
     */
    @GetMapping("/{nombre}")
    fun getByNombreUsuario(
        @PathVariable nombre: String,
        request: HttpServletRequest
    ): ResponseEntity<List<Reserva>?> {

        /*
        COMPROBAR QUE LA PETICIÓN ESTÁ CORRECTAMENTE AUTORIZADA PARA REALIZAR ESTA OPERACIÓN
         */
        // 1º Extraemos la cookie
        val cookie: Cookie? = request.cookies.find { c: Cookie? -> c?.name == "tokenSession" }
        val token: String? = cookie?.name

        if (sessionService.checkToken(token)) {
            // Realiza la consulta a la base de datos
            return ResponseEntity<List<Reserva>?>(null, HttpStatus.OK)
        }
        // CÓDIGO AQUÍ

        /*
        LLAMAR AL SERVICE PARA REALIZAR LA L.N. Y LA LLAMADA A LA BASE DE DATOS
         */
        // CÓDIGO AQUÍ

        // RESPUESTA
        return ResponseEntity<List<Reserva>?>(null, HttpStatus.OK); // cambiar null por las reservas

    }

    /*
    INSERTAR UNA NUEVA RESERVA
     */
    @PostMapping("/")
    fun insert(
        @RequestBody nuevaReserva: Reserva
    ): ResponseEntity<Reserva?> {

        /*
        COMPROBAR QUE LA PETICIÓN ESTÁ CORRECTAMENTE AUTORIZADA PARA REALIZAR ESTA OPERACIÓN
         */
        // CÓDIGO AQUÍ

        /*
        LLAMAR AL SERVICE PARA REALIZAR LA L.N. Y LA LLAMADA A LA BASE DE DATOS
         */
        // CÓDIGO AQUÍ

        // RESPUESTA
        return ResponseEntity<Reserva?>(null, HttpStatus.CREATED); // cambiar null por la reserva
    }

}