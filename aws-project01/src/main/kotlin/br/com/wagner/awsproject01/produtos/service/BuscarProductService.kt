package br.com.wagner.awsproject01.produtos.service

import br.com.wagner.awsproject01.handler.ResourceNotFoundException
import br.com.wagner.awsproject01.produtos.repository.ProductRepository
import br.com.wagner.awsproject01.produtos.response.BuscarProductPorCodResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BuscarProductService(@field:Autowired val productRepository: ProductRepository) {

    val logger = LoggerFactory.getLogger(BuscarProductService::class.java)

    // metodo para buscar um produto pelo codigo

    @Transactional
    fun findByCod(cod: String): BuscarProductPorCodResponse {
        logger.info("---Execultando a busca por produto de codigo: $cod")

        val possivelProduct = productRepository.findByCod(cod)

        if(possivelProduct.isEmpty) {
            logger.error("----produto não encontrado $cod")
            throw ResourceNotFoundException("codigo do produto não encontrado")
        }

        val product = possivelProduct.get()

        val response = BuscarProductPorCodResponse(product)

        logger.info("--Busca realizada com sucesso--")
        return response

    }

}