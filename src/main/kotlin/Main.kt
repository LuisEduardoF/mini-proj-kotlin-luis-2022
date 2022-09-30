package products

fun main(args: Array<String>) {
    println("Hello World!")
    val a2 = Clothes("Camisa mestre yoda",22.35f, 41.50f,"CAMYO", 13, "camisa", "GG", "preto", "vermelho")
    val a3 = Eletronics("Playstation 5", 4000f, 4500f, "PLAY5", 2,  "video-game", 1, 2022)
    val a4 = Collectible("Caneca do Mario Bros", 12.57f, 25f, "CANMAR", 20, "outros", "outros", "-", "comum")

    // inv.new_product("roupa", "Camisa mestre yoda", "22.35", "41.50","CAMYO", "13", "camisa", "GG", "preto", "vermelho")
    // inv.print_inventory()

    val loja = Loja()
    loja.read_compras("src/main/resources/entrada/compras.csv")
}