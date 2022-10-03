package products

abstract class Products(var name: String, var p_buy: Float, var p_sale: Float, var cod: String, var qnt: Int){
    lateinit var category: String
    private var qnt_sale = 0

    fun get_buy_balance(): Float {
        return this.p_buy * (this.qnt + this.qnt_sale)
    }

    fun get_sale_balance(): Float {
        return this.p_sale * this.qnt_sale
    }

    fun get_qnt_buy(): Int {
        return this.qnt
    }

    fun update_qnt_sale(diff: Int){
        if(diff > this.qnt){
            throw error("INVALID SALE - SALE > BUY")
        }
        this.qnt -= diff
        this.qnt_sale += diff
    }

    fun get_cat(): String{
        return this.category
    }

    abstract fun check_search(info: Map<String, String>) : Boolean

    override fun toString(): String{
        return "${this.category}-${this.cod}:${this.p_buy}, ${this.p_sale}"
    }
}

