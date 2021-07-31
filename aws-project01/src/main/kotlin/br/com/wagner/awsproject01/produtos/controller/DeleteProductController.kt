package br.com.wagner.awsproject01.produtos.controller

import br.com.wagner.awsproject01.produtos.service.DeleteProductService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/products")
class DeleteProductController(@field:Autowired val deleteProductService: DeleteProductService) {

    val logger = LoggerFactory.getLogger(DeleteProductController::class.java)

    // end point para excluir produtos pelo codigo

    @DeleteMapping("/{cod}")
    fun delete(@PathVariable("cod") cod: String): ResponseEntity<Any> {
        logger.info("Iniciando a deleção do produto de codigo: $cod")

        deleteProductService.delete(cod)

        return  ResponseEntity.noContent().build()
    }
}