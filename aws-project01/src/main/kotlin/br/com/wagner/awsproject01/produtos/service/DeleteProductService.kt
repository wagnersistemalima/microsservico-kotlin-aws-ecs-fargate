package br.com.wagner.awsproject01.produtos.service

import br.com.wagner.awsproject01.handler.ResourceNotFoundException
import br.com.wagner.awsproject01.produtos.repository.ProductRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DeleteProductService(@field:Autowired val productRepository: ProductRepository) {

    val logger = LoggerFactory.getLogger(DeleteProductService::class.java)

    // metodo para deletar um produto

    fun delete(cod: String) {
        logger.info("---Execultando a deleção do produto de codigo $cod -----")

        // validação

        val product = productRepository.findByCod(cod).orElseThrow { ResourceNotFoundException("codigo do produto não encontrado") }

        productRepository.delete(product)
        logger.info("---Produto deletado com sucesso $cod------")

    }
}