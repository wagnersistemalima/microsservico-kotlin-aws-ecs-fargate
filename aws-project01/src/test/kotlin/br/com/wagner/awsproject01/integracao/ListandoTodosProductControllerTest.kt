package br.com.wagner.awsproject01.integracao

import br.com.wagner.awsproject01.produtos.model.Product
import br.com.wagner.awsproject01.produtos.repository.ProductRepository
import br.com.wagner.awsproject01.produtos.response.ListarTodosProductResponse
import com.fasterxml.jackson.databind.ObjectMapper
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
import java.math.BigDecimal
import java.net.URI
import java.util.stream.Stream

@SpringBootTest
@AutoConfigureDataJpa
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class ListandoTodosProductControllerTest {

    @field:Autowired
    lateinit var mockMvc: MockMvc

    @field:Autowired
    lateinit var objectMapper: ObjectMapper

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

    @Test
    fun `deve retornar 200, com uma lista de products`() {

        // cenario

        val product1 = Product(
            name = "Pc game",
            model = "Dell",
            cod = "20",
            price = BigDecimal("3500.0")
        )
        productRepository.save(product1)

        val product2 = Product(
            name = "Mesa",
            model = "Marrmore",
            cod = "520050",
            price = BigDecimal("250.0")
        )
        productRepository.save(product2)

        val uri = URI("/api/products")

        // ação

        val lista = productRepository.findAll()

        val response = lista.stream().map { product -> ListarTodosProductResponse(product) }

        mockMvc.perform(MockMvcRequestBuilders.get(uri))
            .andExpect(MockMvcResultMatchers.status().`is`(200))
            .andExpect(MockMvcResultMatchers.content().json(toJson(response)))

        // assertivas

    }

    // metodo para desserializar objeto de resposta

    fun toJson(response: Stream<ListarTodosProductResponse>): String {
        return objectMapper.writeValueAsString(response)
    }
}