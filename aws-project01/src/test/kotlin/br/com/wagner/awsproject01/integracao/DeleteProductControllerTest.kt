package br.com.wagner.awsproject01.integracao

import br.com.wagner.awsproject01.produtos.model.Product
import br.com.wagner.awsproject01.produtos.repository.ProductRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.util.UriComponentsBuilder
import java.math.BigDecimal

@SpringBootTest
@AutoConfigureDataJpa
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class DeleteProductControllerTest {

    @field:Autowired
    lateinit var mockMvc: MockMvc

    @field:Autowired
    lateinit var productRepository: ProductRepository

    // rodar antes de cada teste
    @BeforeEach
    internal fun setUp() {
        productRepository.deleteAll()
    }

    // rodar depois de cada teste
    @AfterEach
    internal fun tearDown() {
        productRepository.deleteAll()
    }

    // 1 cenario de teste

    @Test
    fun `deve retornar 204, deletar product quando chamado pelo codigo`() {

        // cenario

        val codValido = "20"

        val uri = UriComponentsBuilder.fromUriString("/api/products/{cod}").buildAndExpand(codValido).toUri()

        val product1 = Product(
            name = "Pc game",
            model = "Dell",
            cod = codValido,
            price = BigDecimal("3500.0")
        )
        productRepository.save(product1)

        // ação

        mockMvc.perform(MockMvcRequestBuilders.delete(uri)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().`is`(204))

        // assertivas

    }

    // 2 cenario de teste

    @Test
    fun `deve retornar 404, ao tentar deletar codigo de produto que nao existe`() {

        // cenario

        val codInvalido = "400000"

        val uri = UriComponentsBuilder.fromUriString("/api/products/{cod}").buildAndExpand(codInvalido).toUri()

        val product1 = Product(
            name = "Pc game",
            model = "Dell",
            cod = "20",
            price = BigDecimal("3500.0")
        )
        productRepository.save(product1)

        // ação

        mockMvc.perform(MockMvcRequestBuilders.delete(uri)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().`is`(404))

        // assertivas

    }

}