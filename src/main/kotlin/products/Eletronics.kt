package products

private enum class Type(val type: String) {
    VIDEO_GAME("video-game"), JOGOS("jogos"), OUTROS("outros")
}
class Eletronics(name: String, p_buy: Float, p_sale: Float, cod: String, qnt: Int, type: String, private var ver: Int, private var year: Int): Products(name, p_buy, p_sale, cod, qnt){
    private lateinit var type: Type
    init {
        this.category = "E"
        this.type = Type.valueOf(type.uppercase().replace("-", "_"))
    }
}