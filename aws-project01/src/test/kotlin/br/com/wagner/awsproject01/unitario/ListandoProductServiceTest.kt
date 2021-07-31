package br.com.wagner.awsproject01.unitario

import br.com.wagner.awsproject01.produtos.model.Product
import br.com.wagner.awsproject01.produtos.repository.ProductRepository
import br.com.wagner.awsproject01.produtos.response.ListarTodosProductResponse
import br.com.wagner.awsproject01.produtos.service.ListandoProductService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.math.BigDecimal
import java.util.stream.Collectors

@ExtendWith(SpringExtension::class)
class ListandoProductServiceTest {

    @field:InjectMocks
    lateinit var listandoProductService: ListandoProductService

    @field:Mock
    lateinit var productRepository: ProductRepository

    // 1 cenario de teste

    @Test
    fun `deve buscar no banco uma lista de produtos`() {

        // cenario

        val product1 = Product(
            name = "Pc game",
            model = "Dell",
            cod = "20",
            price = BigDecimal("3500.0")
        )


        val product2 = Product(
            name = "Mesa",
            model = "Marrmore",
            cod = "520050",
            price = BigDecimal("250.0")
        )

        val lista = mutableListOf<Product>()
        lista.add(product1)
        lista.add(product2)

        val response = lista.stream().map { product -> ListarTodosProductResponse(product) }.collect(Collectors.toList())

        // ação

        // comportamento
        Mockito.`when`(productRepository.findAll()).thenReturn(lista)

        // assertivas

        // retornar objeto contendo lista product de resposta
        Assertions.assertEquals(response, listandoProductService.findAll())

        // verifica se foi chamado findAll()
        Mockito.verify(productRepository, Mockito.times(1)).findAll()
    }
}