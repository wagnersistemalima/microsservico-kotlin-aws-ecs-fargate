package br.com.wagner.awsproject01.produtos.response

import br.com.wagner.awsproject01.produtos.model.Product

class UpdateProductResponse(

    val cod: String
) {
    constructor(product: Product): this(product.cod)
}