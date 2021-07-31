package br.com.wagner.awsproject01.produtos.service

import br.com.wagner.awsproject01.produtos.repository.ProductRepository
import br.com.wagner.awsproject01.produtos.response.ListarTodosProductResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors

@Service
class ListandoProductService(@field:Autowired val productRepository: ProductRepository) {

    val logger = LoggerFactory.getLogger(ListandoProductService::class.java)

    // metodo contendo a logica para buscar todos os produtos

    @Transactional
    fun findAll(): MutableList<ListarTodosProductResponse> {
        logger.info("---Execultando a busca de todos os produtos----")

        val listaProduct = productRepository.findAll()

        val response = listaProduct.stream().map { product -> ListarTodosProductResponse(product) }.collect(Collectors.toList())

        logger.info("--Listagem de produtos execultada com sucesso--")
        return response

    }
}