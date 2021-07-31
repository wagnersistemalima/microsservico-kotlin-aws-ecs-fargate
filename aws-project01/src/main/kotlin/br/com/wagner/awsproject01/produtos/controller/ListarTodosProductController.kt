package br.com.wagner.awsproject01.produtos.controller

import br.com.wagner.awsproject01.produtos.service.ListandoProductService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/products")
class ListarTodosProductController(@field:Autowired val listandoProductService: ListandoProductService) {

    val logger = LoggerFactory.getLogger(ListarTodosProductController::class.java)

    // end point para listar todos os produtos

    @GetMapping
    fun findAll(): ResponseEntity<Any> {
        logger.info("----Iniciando a busca pelos produtos----")

        val response = listandoProductService.findAll()

        return ResponseEntity.ok().body(response)
    }
}