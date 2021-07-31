package br.com.wagner.awsproject01.produtos.model

import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "tb_product")
data class Product(

    var name: String,

    var model: String,

    @field:Column(unique = true)
    val cod: String,

    var price: BigDecimal
){
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    val dataRegistro: LocalDateTime? = LocalDateTime.now()

    var updateDataRegistro: LocalDateTime? = null

    // metodo auxiliar para atualizar o registro de produtos

    fun update() {
        updateDataRegistro = LocalDateTime.now()
    }
}






