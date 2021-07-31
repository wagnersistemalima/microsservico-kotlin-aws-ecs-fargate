package br.com.wagner.awsproject01.integracao

import br.com.wagner.awsproject01.produtos.model.Product
import br.com.wagner.awsproject01.produtos.repository.ProductRepository
import br.com.wagner.awsproject01.produtos.response.BuscarProductPorCodResponse
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
import org.springframework.web.util.UriComponentsBuilder
import java.math.BigDecimal
import javax.transaction.Transactional

@SpringBootTest
@AutoConfigureDataJpa
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class BuscarProdutoCodigoControllerTest {

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

    // 1 cenario de teste

    @Test
    fun `deve retornar 200, com os dados do produto, quando buscado pelo codigo`() {

        // cenario

        val product = Product(
            name = "Pc game",
            model = "Dell",
            cod = "20",
            price = BigDecimal("3500.0")
        )
        productRepository.save(product)

        val codValido = product.cod

        val uri = UriComponentsBuilder.fromUriString("/api/products/{cod}").buildAndExpand(codValido).toUri()

        // ação

        val response = BuscarProductPorCodResponse(product)

        mockMvc.perform(MockMvcRequestBuilders.get(uri)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().`is`(200))
            .andExpect(MockMvcResultMatchers.content().json(toJson(response)))

        // assertivas
    }

    // 2 cenario de teste

    @Test
    fun `deve retornar 404, qqquando codigo do produto não encontrado`() {

        // cenario

        val product = Product(
            name = "Pc game",
            model = "Dell",
            cod = "20",
            price = BigDecimal("3500.0")
        )
        productRepository.save(product)

        val codInvalido = "500000"

        val uri = UriComponentsBuilder.fromUriString("/api/products/{cod}").buildAndExpand(codInvalido).toUri()

        // ação

        mockMvc.perform(MockMvcRequestBuilders.get(uri)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().`is`(404))

        // assertivas
    }

    // metodo para desserializar objeto de resposta
    fun toJson(response: BuscarProductPorCodResponse): String {
        return objectMapper.writeValueAsString(response)
    }
}