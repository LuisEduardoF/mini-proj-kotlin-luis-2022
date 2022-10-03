package products

private enum class Size(val size: String) {
    PP("PP"), P("P"), M("M"), G("G"), GG("GG"), XG("XG"), XXG("XXG")
}
private enum class Type_Clothes(val size: String) {
    CAMISA("camisa"), MOLETON("moleton"), ACESSORIO("acessorio")
}
class Clothes(name: String, p_buy: Float, p_sale: Float, cod: String, qnt: Int, type: String, size: String, private var p_color: String, private var s_color: String): Products(name, p_buy, p_sale, cod, qnt){
    private lateinit var size: Size
    private lateinit var type: Type_Clothes
    init {
        this.category = "R"
        this.type = Type_Clothes.valueOf(type.uppercase())
        this.size = Size.valueOf(size.uppercase())
    }

    override fun check_search(info: Map<String, String>): Boolean {
        val type = info["Tipo"].toString();
        val size = info["Tamanho"].toString();
        val c_p = info["Cor primaria"].toString();
        val c_s = info["Cor secundário"].toString();

        if(type != "-") {
            try {
                if (this.type != Type_Clothes.valueOf(type)) {
                    return false
                }
            } catch (e: Exception){
                return false
            }
        }
        if(size != "-") {
            try {
                if (this.size != Size.valueOf(size)) {
                    return false
                }
            } catch (e: Exception){
                return false
            }
        }
        if(c_p != "-") {
            if (this.p_color != c_p) {
                return false
            }
        }
        if(c_s != "-") {
            if (this.s_color != c_s) {
                return false
            }
        }
        return true
    }
}