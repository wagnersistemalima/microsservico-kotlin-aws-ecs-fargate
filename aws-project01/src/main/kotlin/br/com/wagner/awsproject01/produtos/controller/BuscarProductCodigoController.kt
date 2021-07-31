package br.com.wagner.awsproject01.produtos.controller

import br.com.wagner.awsproject01.produtos.service.BuscarProductService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/products")
class BuscarProductCodigoController(@field:Autowired val buscarProductService: BuscarProductService) {

    val logger = LoggerFactory.getLogger(BuscarProductCodigoController::class.java)

    // end point para buscar produto pelo codigo

    @GetMapping("/{cod}")
    fun findByCod(@PathVariable("cod") cod: String) : ResponseEntity<Any> {
        logger.info("----Iniciando a busca do produto por codigo----- $cod ")

        val response = buscarProductService.findByCod(cod)

        return ResponseEntity.ok().body(response)
    }

}