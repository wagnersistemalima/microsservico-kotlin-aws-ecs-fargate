package br.com.wagner.awsproject01.produtos.controller

import br.com.wagner.awsproject01.produtos.request.ProductRequest
import br.com.wagner.awsproject01.produtos.service.InsertProductService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid

@Validated
@RestController
@RequestMapping("/api")
class InsertProductController(@field:Autowired val insertProductService: InsertProductService) {

    val logger = LoggerFactory.getLogger(InsertProductController::class.java)

    // end point para cadastrar dados

    @PostMapping("/products")
    fun insert(@Valid @RequestBody request: ProductRequest) : ResponseEntity<Any> {
        logger.info("----Cadastrado produto------")

        val response = insertProductService.insert(request)

        val uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(response.id).toUri()

        return ResponseEntity.created(uri).build()

    }
}