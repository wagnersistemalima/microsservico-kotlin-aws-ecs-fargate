package br.com.wagner.awsproject01.produtos.request

import java.math.BigDecimal
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Positive
import javax.validation.constraints.Size

data class UpdateProductRequest(

    @field:NotBlank
    @field:Size(max = 32)
    val name: String,

    @field:NotBlank
    @field:Size(max = 24)
    val model: String,

    @field:Positive
    val price: BigDecimal
)
