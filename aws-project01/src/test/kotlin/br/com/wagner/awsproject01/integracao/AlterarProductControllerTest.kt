package br.com.wagner.awsproject01.integracao

import br.com.wagner.awsproject01.produtos.model.Product
import br.com.wagner.awsproject01.produtos.repository.ProductRepository
import br.com.wagner.awsproject01.produtos.request.UpdateProductRequest
import br.com.wagner.awsproject01.produtos.response.UpdateProductResponse
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
import org.springframework.web.util.UriComponentsBuilder
import java.math.BigDecimal

@SpringBootTest
@AutoConfigureDataJpa
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class AlterarProductControllerTest {

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
    fun `deve retornar 200, atualizar um produto, quando solicitado pelo codigo`() {

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

        val response = UpdateProductResponse(product1)
        // ação

        val request = UpdateProductRequest(
            name = "Ventilador",
            model = "turbo",
            price = BigDecimal("260.0")
        )

        mockMvc.perform(MockMvcRequestBuilders.put(uri)
            .contentType(MediaType.APPLICATION_JSON).content(toJsonRequest(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(200))
            .andExpect(MockMvcResultMatchers.content().json(toJsonResponse(response)))

        // assertiva


    }

    // 2 cenario de teste

    @Test
    fun `deve retornar 400, quando tentar atualizar produto pelo codigo, com nome vazio`() {

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

        val request = UpdateProductRequest(
            name = "",
            model = "turbo",
            price = BigDecimal("260.0")
        )

        mockMvc.perform(MockMvcRequestBuilders.put(uri)
            .contentType(MediaType.APPLICATION_JSON).content(toJsonRequest(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(400))

        // assertiva


    }

    // 3 cenario de teste

    @Test
    fun `deve retornar 400, quando tentar atualizar produto pelo codigo, com modelo vazio`() {

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

        val request = UpdateProductRequest(
            name = "Ventilador",
            model = "",
            price = BigDecimal("260.0")
        )

        mockMvc.perform(MockMvcRequestBuilders.put(uri)
            .contentType(MediaType.APPLICATION_JSON).content(toJsonRequest(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(400))

        // assertiva


    }

    // 4 cenario de teste

    @Test
    fun `deve retornar 400, quando tentar atualizar produto pelo codigo, com preço zerado`() {

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

        val request = UpdateProductRequest(
            name = "Ventilador",
            model = "Turbo",
            price = BigDecimal("0.0")
        )

        mockMvc.perform(MockMvcRequestBuilders.put(uri)
            .contentType(MediaType.APPLICATION_JSON).content(toJsonRequest(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(400))

        // assertiva

    }

    // 5 cenario de teste

    @Test
    fun `deve retornar 404, quando tentar atualizar produto pelo codigo, que não existe`() {

        // cenario

        val codInvalido = "20"

        val uri = UriComponentsBuilder.fromUriString("/api/products/{cod}").buildAndExpand(codInvalido).toUri()

        val product1 = Product(
            name = "Pc game",
            model = "Dell",
            cod = "50",
            price = BigDecimal("3500.0")
        )
        productRepository.save(product1)

        // ação

        val request = UpdateProductRequest(
            name = "Ventilador",
            model = "Turbo",
            price = BigDecimal("250.0")
        )

        mockMvc.perform(MockMvcRequestBuilders.put(uri)
            .contentType(MediaType.APPLICATION_JSON).content(toJsonRequest(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(404))

        // assertiva

    }

    // metodo para desserializar objeto da request

    fun toJsonRequest(request: UpdateProductRequest): String {
        return objectMapper.writeValueAsString(request)
    }

    // metodo para desserializar response

    fun toJsonResponse(response: UpdateProductResponse): String {
        return objectMapper.writeValueAsString(response)
    }
}