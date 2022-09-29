package products

private enum class Type_C(val type: String) {
    LIVRO("livro"), BONECO("boneco"), OUTROS("outros")
}

private enum class Mat_f(val material: String){
    PAPEL("papel"), PLASTICO("plastico"), AÇO("aço"), MISTURADO("misturado"), OUTROS("outros")
}

private enum class Relevance(val relevance: String) {
    COMUM("comum"), MEDIO("medio"), RARO("raro"), RARISSIMO("rarissimo")
}

inline fun <reified T : Enum<T>> printAllValues() {
    print(enumValues<T>().joinToString { it.name })
}

class Collectible(name: String, p_buy: Float, p_sale: Float, cod: String, qnt: Int, type: String, material: String, private var size: String, relevance: String): Products(name, p_buy, p_sale, cod, qnt){
    private lateinit var type: Type_C
    private lateinit var material: Mat_f
    private lateinit var relevance: Relevance

    init {
        this.category = "C"
        this.type = Type_C.valueOf(type.uppercase())
        this.material = Mat_f.valueOf(material.uppercase())
        this.relevance = Relevance.valueOf(relevance.uppercase())
    }
}