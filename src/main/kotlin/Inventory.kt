import Clothes
import Collectible
import Eletronics
import Products

enum class Categories(val cat: String){
    COLECIONAVEL("C"), ROUPA("R"), ELETRONICO("E")
}

enum class Categories_suffix(val cat: Categories){
    R(Categories.ROUPA), C(Categories.COLECIONAVEL), E(Categories.ELETRONICO)
}

class Inventory {
    var inventory: MutableList<Products> = mutableListOf()
    fun new_product(category: Categories, vararg input: String){
        val aux: Products
        val cod = input[3]

        if(category == Categories.COLECIONAVEL){
            aux = Collectible(
                input[0],
                input[1].toFloat(),
                input[2].toFloat(),
                cod,
                input[4].toInt(),
                input[5],
                input[6],
                input[7],
                input[8]
            )
        }
        else if(category == Categories.ROUPA){
            aux = Clothes(
                input[0],
                input[1].toFloat(),
                input[2].toFloat(),
                cod,
                input[4].toInt(),
                input[5],
                input[6],
                input[7],
                input[8]
            )
        }
        else{
            aux = Eletronics(
                input[0],
                input[1].toFloat(),
                input[2].toFloat(),
                cod,
                input[4].toInt(),
                input[5],
                input[6].toInt(),
                input[7].toInt()
            )
        }
        this.inventory += aux
    }

    fun print_inventory(){
        for (prod in this.inventory){
            println(prod)
        }
    }

    fun get_buy_balance(): Float{
        var sum: Float = 0f
        this.inventory.forEach { sum += it.get_buy_balance() }
        return sum
    }

    fun get_sale_balance(): Float{
        var sum: Float = 0f
        this.inventory.forEach { sum += it.get_sale_balance() }
        return sum
    }

    fun update_product_sale(cod: String, sale: Int){
        this.inventory.find { it.cod == cod }?.update_qnt_sale(sale)
    }

    fun get_estoque_geral(): List<Products>{
        return this.inventory
    }

    fun get_estoque_cat(): Map<Categories, Int>{
        var map: Map<Categories, Int> = mutableMapOf()
        for(cat in listOf<Categories>(Categories.ROUPA, Categories.COLECIONAVEL, Categories.ELETRONICO)){
            var sum = 0
            this.inventory.forEach { if(Categories_suffix.valueOf(it.category).cat == cat) sum += it.qnt }
            map += Pair(cat, sum)
        }
        return map
    }
    fun get_busca(info: Map<String, Any?>): List<Products>{
        var list: List<Products> = listOf()
        val cat = info["Categoria"].toString().uppercase()
        if(cat != "-"){
            for(prod in this.inventory){
                if(prod.check_search(info as Map<String, String>) && Categories_suffix.valueOf(prod.category).cat == Categories.valueOf(cat)){
                    list += prod
                }
            }
        }
        else{
                for(prod in this.inventory){
                    if(prod.check_search(info as Map<String, String>)){
                        list += prod
                    }
                }
            }
        return list
    }
}