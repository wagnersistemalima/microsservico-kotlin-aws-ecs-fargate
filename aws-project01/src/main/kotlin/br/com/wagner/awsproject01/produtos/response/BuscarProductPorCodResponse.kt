package br.com.wagner.awsproject01.produtos.response

import br.com.wagner.awsproject01.produtos.model.Product
import java.math.BigDecimal
import java.time.LocalDateTime

data class BuscarProductPorCodResponse(

    val name: String,

    val model: String,

    val cod: String,

    val price: BigDecimal,

    val dataRegistro: LocalDateTime?,

    val updateDataRegistro: LocalDateTime?
) {
    constructor(product: Product): this(product.name, product.model, product.cod, product.price, product.dataRegistro, product.updateDataRegistro)
}