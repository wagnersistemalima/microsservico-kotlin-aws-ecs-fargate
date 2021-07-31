package br.com.wagner.awsproject01.produtos.response

import br.com.wagner.awsproject01.produtos.model.Product

data class ListarTodosProductResponse(

    val id: Long?,
    val name: String,
    val cod: String,
) {
    constructor(product: Product): this(product.id, product.name, product.cod)
}