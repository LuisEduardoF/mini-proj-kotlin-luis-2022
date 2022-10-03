package products

private enum class Type(val type: String) {
    VIDEO_GAME("video-game"), JOGO("jogos"), OUTROS("outros")
}
class Eletronics(name: String, p_buy: Float, p_sale: Float, cod: String, qnt: Int, type: String, private var ver: Int, private var year: Int): Products(name, p_buy, p_sale, cod, qnt){
    private lateinit var type: Type
    init {
        this.category = "E"
        this.type = Type.valueOf(type.uppercase().replace("-", "_"))
    }
    override fun check_search(info: Map<String, String>): Boolean{
        val type = info["Tipo"]?.uppercase()?.replace("-", "_");
        var ver = info["Versão"];
        var year = info["Ano de frabricação"];

        if(type != "-") {
            try {
                if (this.type != Type.valueOf(type!!)) {
                    return false
                }
            } catch (e: Exception){
                return false
            }
        }
        if(ver != "-") {
            if (this.ver != ver?.toInt()) {
                return false
            }
        }
        if(year != "-") {
            if (this.year != year?.toInt()) {
                return false
            }
        }
        return true
    }
}