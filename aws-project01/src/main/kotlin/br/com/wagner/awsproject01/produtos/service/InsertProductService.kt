package br.com.wagner.awsproject01.produtos.service

import br.com.wagner.awsproject01.handler.ExceptionGenericValidated
import br.com.wagner.awsproject01.produtos.repository.ProductRepository
import br.com.wagner.awsproject01.produtos.request.ProductRequest
import br.com.wagner.awsproject01.produtos.response.InsertProductResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class InsertProductService(@field:Autowired val productRepository: ProductRepository) {

    val logger = LoggerFactory.getLogger(InsertProductService::class.java)

    // metodo contendo a logica para cadastrar um novo produto

    @Transactional
    fun insert(request: ProductRequest): InsertProductResponse {
        logger.info("-----Execultando o cadastro de um novo produto-----")

        // validação

        if(productRepository.existsByCod(request.cod)) {
            logger.error("--Entrou no if, codigo do produto já cadastrado ${request.cod}")
            throw ExceptionGenericValidated("codigo do produto unico, já cadastrado!")
        }

        val product = request.toModel()
        productRepository.save(product)

        val response = InsertProductResponse(product)

        logger.info("---Cadastro do produto concluido com sucesso---")
        return  response

    }
}