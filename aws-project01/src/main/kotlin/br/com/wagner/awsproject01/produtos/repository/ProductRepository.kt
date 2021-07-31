package br.com.wagner.awsproject01.produtos.repository

import br.com.wagner.awsproject01.produtos.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProductRepository: JpaRepository<Product, Long> {

    fun existsByCod(cod: String): Boolean

    fun findByCod(codigo: String): Optional<Product>
}