package br.com.wagner.awsproject01.produtos.response

import br.com.wagner.awsproject01.produtos.model.Product

data class InsertProductResponse(

    val id: Long?
) {
    constructor(product: Product): this(product.id)
}