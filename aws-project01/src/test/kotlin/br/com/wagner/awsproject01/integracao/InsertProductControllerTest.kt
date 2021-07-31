package br.com.wagner.awsproject01.integracao

import br.com.wagner.awsproject01.produtos.model.Product
import br.com.wagner.awsproject01.produtos.repository.ProductRepository
import br.com.wagner.awsproject01.produtos.request.ProductRequest
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

@SpringBootTest
@AutoConfigureDataJpa
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class InsertProductControllerTest {

    @field:Autowired
    lateinit var productRepository: ProductRepository

    @field:Autowired
    lateinit var mockMvc: MockMvc

    @field:Autowired
    lateinit var objectMapper: ObjectMapper

    // rodar antes de cada teste
    @BeforeEach
    internal fun setUp() {

        val product = Product(
            name = "Cadeira Game",
            model = "XT",
            cod = "66",
            price = BigDecimal("555.0")
        )

        productRepository.save(product)

    }

    // rodar depois de cada teste
    @AfterEach
    internal fun tearDown() {
        productRepository.deleteAll()
    }

    // 1 teste caminho feliz

    @Test
    fun `deve retornar 201, cadastrar um produto`() {
        // cenario

        val uri = URI("/api/products")

        val request = ProductRequest(
            name = "Pc game",
            model = "Dell",
            cod = "20",
            price = BigDecimal("3500.0")
        )
        // ação


        mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .content(toJson(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(201))

        // assertiva

    }

    // 2 teste

    @Test
    fun `deve retornar 400, cadastrar um produto nome vazio`() {
        // cenario

        val uri = URI("/api/products")

        val request = ProductRequest(
            name = "",
            model = "Dell",
            cod = "20",
            price = BigDecimal("3500.0")
        )
        // ação

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .content(toJson(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(400))

        // assertiva

    }

    // 3 teste

    @Test
    fun `deve retornar 400, cadastrar um produto com model vazio`() {
        // cenario

        val uri = URI("/api/products")

        val request = ProductRequest(
            name = "Pc game",
            model = "",
            cod = "20",
            price = BigDecimal("3500.0")
        )
        // ação

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .content(toJson(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(400))

        // assertiva

    }

    // 4 teste

    @Test
    fun `deve retornar 400, cadastrar um produto com preço zerado`() {
        // cenario

        val uri = URI("/api/products")

        val request = ProductRequest(
            name = "Pc game",
            model = "Dell",
            cod = "20",
            price = BigDecimal("0.0")
        )
        // ação

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .content(toJson(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(400))

        // assertiva

    }

    // 5 teste

    @Test
    fun `deve retornar 400, cadastrar um produto com codigo zerado`() {
        // cenario

        val uri = URI("/api/products")

        val request = ProductRequest(
            name = "Pc game",
            model = "Dell",
            cod = "",
            price = BigDecimal("2500.0")
        )
        // ação

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .content(toJson(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(400))

        // assertiva

    }

    // 6 teste

    @Test
    fun `deve retornar 400, cadastrar um produto com codigo com mais de 8 caracteres`() {
        // cenario

        val uri = URI("/api/products")

        val request = ProductRequest(
            name = "Pc game",
            model = "Dell",
            cod = "123456789",
            price = BigDecimal("2500.0")
        )
        // ação

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .content(toJson(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(400))

        // assertiva

    }


    // metodo para desserializar objeto de request

    fun toJson(request: ProductRequest): String {
        return objectMapper.writeValueAsString(request)
    }
}