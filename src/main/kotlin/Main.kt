package products

import java.util.*

fun main(args: Array<String>) {
    val loja = Loja()

    loja.read_compras("src/main/resources/entrada/compras.csv")
    loja.read_vendas("src/main/resources/entrada/vendas.csv")
    loja.process_busca("src/main/resources/entrada/busca.csv")

    loja.get_balancente()
    loja.get_estoque_geral()
    loja.get_estoque_cat()
}