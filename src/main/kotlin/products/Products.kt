package products

open class Products(var name: String, var p_buy: Float, var p_sale: Float, var cod: String, var qnt: Int){
    lateinit var category: String

    fun get_buy_balance(): Float {
        return this.p_buy * this.qnt
    }

    fun get_sale_balance(): Float {
        return this.p_sale * this.qnt
    }

    fun get_qnt(): Int {
        return this.qnt
    }

    override fun toString(): String{
        return "${this.category}-${this.cod}"
    }
}

