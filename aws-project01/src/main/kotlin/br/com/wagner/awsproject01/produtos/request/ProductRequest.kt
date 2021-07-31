package br.com.wagner.awsproject01.produtos.request

import br.com.wagner.awsproject01.produtos.model.Product
import java.math.BigDecimal
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Positive
import javax.validation.constraints.Size

data class ProductRequest(

    @field:NotBlank
    @field:Size(max = 32)
    val name: String,

    @field:NotBlank
    @field:Size(max = 24)
    val model: String,

    @field:NotBlank
    @field:Size(max = 8)
    val cod: String,

    @field:Positive
    val price: BigDecimal
) {

    // metodo para converter request em entidade

    fun toModel(): Product {
        return  Product(name = name, model = model, cod = cod, price = price)
    }
}
