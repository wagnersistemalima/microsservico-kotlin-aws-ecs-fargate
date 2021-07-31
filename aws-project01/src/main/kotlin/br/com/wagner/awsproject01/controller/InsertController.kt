package br.com.wagner.awsproject01.controller

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class InsertController {

    val logger = LoggerFactory.getLogger(InsertController::class.java)

    // end point para cadastrar dados

    @PostMapping("/cadastro")
    fun insert() : ResponseEntity<Any> {
        logger.info("----Cadastrado dados------")

        return ResponseEntity.ok().body("Cadastrando dados")
    }
}