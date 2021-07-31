package br.com.wagner.awsproject01.controller

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/test")
class TesteController {

    val logger = LoggerFactory.getLogger(TesteController::class.java)

    @GetMapping("/dog/{name}")
    fun find(@PathVariable("name") name: String): ResponseEntity<Any> {
        logger.info("-----Iniciando busca dos dados $name -----")

        return ResponseEntity.ok().body("Iniciando a busca dos dados de: $name")
    }
}