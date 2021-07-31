package br.com.wagner.awsproject01.unitario

import br.com.wagner.awsproject01.handler.ResourceNotFoundException
import br.com.wagner.awsproject01.produtos.model.Product
import br.com.wagner.awsproject01.produtos.repository.ProductRepository
import br.com.wagner.awsproject01.produtos.response.BuscarProductPorCodResponse
import br.com.wagner.awsproject01.produtos.service.BuscarProductService
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
class BuscarProductServiceTest {

    @field:InjectMocks
    lateinit var buscarProductService: BuscarProductService

    @field:Mock
    lateinit var productRepository: ProductRepository


    // 1 cenario de teste caminho feliz

    @Test
    fun `deve retornar objeto de resposta, quendo solicitado a busca pelo produto por codigo`() {

        // cenario

        val product = Product(
            name = "Pc game",
            model = "Dell",
            cod = "20",
            price = BigDecimal("3500.0")

        )
        val codValido = product.cod

        // ação

        val response = BuscarProductPorCodResponse(product)

        // comportamento
        Mockito.`when`(productRepository.findByCod(codValido)).thenReturn(Optional.of(product))

        // assertivas

        // naõ deve lançar exceção
        Assertions.assertDoesNotThrow { buscarProductService.findByCod(codValido) }
        Assertions.assertEquals(response, buscarProductService.findByCod(codValido))
    }

    // 2 cenario de teste

    @Test
    fun `deve lançar exception ResourceNotFoundException 404, quando não encontrar o codigo do produto`() {

        // cenario

        val codInvalido = "5000000"

        // ação

        // comportamento
        Mockito.`when`(productRepository.findByCod(codInvalido)).thenReturn(Optional.empty())

        // assertivas

        //deve lançar exceção
        Assertions.assertThrows(ResourceNotFoundException::class.java) {buscarProductService.findByCod(codInvalido)}

    }
}