package br.com.wagner.awsproject01.unitario

import br.com.wagner.awsproject01.handler.ExceptionGenericValidated
import br.com.wagner.awsproject01.produtos.repository.ProductRepository
import br.com.wagner.awsproject01.produtos.request.ProductRequest
import br.com.wagner.awsproject01.produtos.service.InsertProductService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.math.BigDecimal
import java.util.*

@ExtendWith(SpringExtension::class)
class InsertProductServiceTest {

    @field:InjectMocks
    lateinit var insertProductService: InsertProductService

    @field:Mock
    lateinit var productRepository: ProductRepository


    // 1 cenario de teste caminho feliz

    @Test
    fun `deve cadastrar no banco um novo produto`() {

        // cenario

        val request = ProductRequest(
            name = "Pc game",
            model = "Dell",
            cod = "20",
            price = BigDecimal("3500.0")
        )

        val product = request.toModel()

        // ação

        // comportamento
        Mockito.`when`(productRepository.save(product)).thenReturn(product)

        // assertiva

        // nao deve lançar exceção
        Assertions.assertDoesNotThrow { insertProductService.insert(request) }

        // verifica se foi chamado o save
        Mockito.verify(productRepository, Mockito.times(1)).save(product)
    }

    // 2 cenario de teste

    @Test
    fun `deve lançar exception ExceptionGenericValidation, quando tentar cadastrar um produto com codigo existente`() {

        // cenario

        val codigoExistente = "20"

        val request = ProductRequest(
            name = "Pc game",
            model = "Dell",
            cod = codigoExistente,
            price = BigDecimal("3500.0")
        )

        val product = request.toModel()

        // ação

        // comportamento
        Mockito.`when`(productRepository.existsByCod(codigoExistente)).thenReturn(true)

        // comportamento
        Mockito.`when`(productRepository.save(product)).thenReturn(product)

        // assertiva

        // nao deve lançar exceção
        Assertions.assertThrows(ExceptionGenericValidated::class.java) {insertProductService.insert(request)}

        // verifica se foi chamado o save
        Mockito.verify(productRepository, Mockito.times(0)).save(product)
    }
}