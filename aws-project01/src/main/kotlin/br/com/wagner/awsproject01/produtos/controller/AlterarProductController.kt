package br.com.wagner.awsproject01.produtos.controller

import br.com.wagner.awsproject01.produtos.request.UpdateProductRequest
import br.com.wagner.awsproject01.produtos.service.AlterandoProductService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Validated
@RestController
@RequestMapping("/api/products")
class AlterarProductController(@field:Autowired val alterandoProductService: AlterandoProductService) {

    val logger = LoggerFactory.getLogger(AlterarProductController::class.java)

    // end point para alterar product

    @PutMapping("/{cod}")
    fun update(@PathVariable("cod") cod: String, @Valid @RequestBody request: UpdateProductRequest): ResponseEntity<Any> {
        logger.info("-----Iniciando alteração de um produto pelo cod $cod")

        val response = alterandoProductService.update(cod, request)

        return ResponseEntity.ok().body(response)
    }
}