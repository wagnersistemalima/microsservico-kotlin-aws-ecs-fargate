package br.com.wagner.awsproject01.unitario

import br.com.wagner.awsproject01.handler.ResourceNotFoundException
import br.com.wagner.awsproject01.produtos.model.Product
import br.com.wagner.awsproject01.produtos.repository.ProductRepository
import br.com.wagner.awsproject01.produtos.request.UpdateProductRequest
import br.com.wagner.awsproject01.produtos.service.AlterandoProductService
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
class UpdateProductServiceTest {

    @field:InjectMocks
    lateinit var alterandoProductService: AlterandoProductService

    @field:Mock
    lateinit var productRepository: ProductRepository

    // 1 cenario de teste

    @Test
    fun `deve atualizar o product, nao lançar exception`() {

        // cenario

        val codValido = "50"

        val product1 = Product(
            name = "Pc game",
            model = "Dell",
            cod = codValido,
            price = BigDecimal("3500.0")
        )

        val request = UpdateProductRequest(
            name = "Ventilador",
            model = "Turbo",
            price = BigDecimal("600.0")
        )

        product1.name = request.name
        product1.model = request.model
        product1.price = request.price

        // ação

        // comportamento
        Mockito.`when`(productRepository.findByCod(codValido)).thenReturn(Optional.of(product1))
        Mockito.`when`(productRepository.save(product1)).thenReturn(product1)

        // assertiva

        // nao deve lançar exception
        Assertions.assertDoesNotThrow { alterandoProductService.update(codValido, request) }

        // verifica se o save foi chamado
        Mockito.verify(productRepository, Mockito.times(1)).save(product1)
    }

    // 2 cenario de teste

    @Test
    fun `deve lançar exception, quando tentar atualizar um produto com codigo inexistente`() {

        // cenario

        val codInexistente = "40000000"

        val product1 = Product(
            name = "Pc game",
            model = "Dell",
            cod = "15",
            price = BigDecimal("3500.0")
        )

        val request = UpdateProductRequest(
            name = "Ventilador",
            model = "Turbo",
            price = BigDecimal("600.0")
        )


        // ação

        // comportamento
        Mockito.`when`(productRepository.findByCod(codInexistente)).thenReturn(Optional.empty())

        // assertiva

        // deve lançar exception
        Assertions.assertThrows(ResourceNotFoundException::class.java) {alterandoProductService.update(codInexistente, request)}

        // verifica se o save foi chamado
        Mockito.verify(productRepository, Mockito.times(0)).save(product1)
    }
}