
fun main(args: Array<String>) {
    val loja = Loja()

    loja.read_compras("${args[0]}/compras.csv")
    loja.read_vendas("${args[0]}/vendas.csv")
    loja.process_busca("${args[0]}/busca.csv", args[1])

    loja.get_balancente(args[1])
    loja.get_estoque_geral(args[1])
    loja.get_estoque_cat(args[1])
}