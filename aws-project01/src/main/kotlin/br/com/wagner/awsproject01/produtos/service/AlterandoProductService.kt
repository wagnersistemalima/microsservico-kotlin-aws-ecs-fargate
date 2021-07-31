package br.com.wagner.awsproject01.produtos.service

import br.com.wagner.awsproject01.handler.ResourceNotFoundException
import br.com.wagner.awsproject01.produtos.repository.ProductRepository
import br.com.wagner.awsproject01.produtos.request.UpdateProductRequest
import br.com.wagner.awsproject01.produtos.response.BuscarProductPorCodResponse
import br.com.wagner.awsproject01.produtos.response.UpdateProductResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AlterandoProductService(@field:Autowired val productRepository: ProductRepository) {

    val logger = LoggerFactory.getLogger(AlterandoProductService::class.java)

    // metodo contendo a logica para alterar dados do product

    @Transactional
    fun update(cod: String, request: UpdateProductRequest): UpdateProductResponse {
        logger.info("--- Execultando a atualização do produto $cod--------")

        // validação

        val product = productRepository.findByCod(cod).orElseThrow { ResourceNotFoundException("codigo do produto não encontrado") }

        product.name = request.name
        product.model = request.model
        product.price = request.price
        product.update()

        productRepository.save(product)

        val response = UpdateProductResponse(product)

        logger.info("---Produto alterado com sucesso cod: $cod----")
        return response

    }
}