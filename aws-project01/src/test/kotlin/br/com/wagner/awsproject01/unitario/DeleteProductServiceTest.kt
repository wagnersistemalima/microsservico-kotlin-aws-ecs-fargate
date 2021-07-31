package br.com.wagner.awsproject01.unitario

import br.com.wagner.awsproject01.handler.ResourceNotFoundException
import br.com.wagner.awsproject01.produtos.model.Product
import br.com.wagner.awsproject01.produtos.repository.ProductRepository
import br.com.wagner.awsproject01.produtos.service.DeleteProductService
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
class DeleteProductServiceTest {

    @field:InjectMocks
    lateinit var deleteProductService: DeleteProductService

    @field:Mock
    lateinit var productRepository: ProductRepository

    // 1 cenario de teste

    @Test
    fun `deve deletar um product, e nao lançar exception`() {

        // cenario

        val product1 = Product(
            name = "Pc game",
            model = "Dell",
            cod = "20",
            price = BigDecimal("3500.0")
        )

        val codValido = product1.cod

        // ação

        // comportamento
        Mockito.`when`(productRepository.findByCod(codValido)).thenReturn(Optional.of(product1))

        // assertiva

        // nao deve lançar exception
        Assertions.assertDoesNotThrow { deleteProductService.delete(codValido) }

        // verifica se foi chamado metodo delete
        Mockito.verify(productRepository, Mockito.times(1)).delete(product1)
    }

    // 2 cenario de teste

    @Test
    fun `deve lançar exception, quando tentar deletar produto que codigo não exista`() {

        // cenario

        val codInvalido = "500000"

        // ação

        // comportamento
        Mockito.`when`(productRepository.findByCod(codInvalido)).thenReturn(Optional.empty())

        // assertiva

        // deve lançar exception
        Assertions.assertThrows(ResourceNotFoundException::class.java) {deleteProductService.delete(codInvalido)}

    }
}