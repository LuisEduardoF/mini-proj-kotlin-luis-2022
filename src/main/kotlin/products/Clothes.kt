package products

private enum class Size(val size: String) {
    PP("PP"), P("P"), M("M"), G("G"), GG("GG"), XG("XG"), XXG("XXG")
}
class Clothes(name: String, p_buy: Float, p_sale: Float, cod: String, qnt: Int, private var type: String, size: String, private var p_color: String, private var s_color: String): Products(name, p_buy, p_sale, cod, qnt){
    private lateinit var size: Size
    init {
        this.category = "R"
        this.size = Size.valueOf(size.uppercase())
    }

    fun get_type(): String{
        return this.type
    }
}