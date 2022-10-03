import Clothes
import Collectible
import Eletronics
import Products

enum class Categories(val cat: String){
    COLECIONAVEL("C"), ROUPA("R"), ELETRONICO("E")
}

class Inventory {
    var inventory: MutableMap<Categories, MutableMap<String, Products>> = mutableMapOf(Categories.COLECIONAVEL to mutableMapOf<String, Products>(), Categories.ROUPA to mutableMapOf<String, Products>(), Categories.ELETRONICO to mutableMapOf<String, Products>())

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
        this.inventory[category]?.put(cod, aux)
    }

    fun print_inventory(){
        for (category in this.inventory.keys){
            var aux = this.inventory[category]
            println("--------------- ($category)")
            for(r in aux!!){
                println(r.value)
            }
        }
    }

    fun get_buy_balance(): Float{
        var sum: Float = 0f
        this.inventory.forEach { it.value.forEach{ sum += it.value.get_buy_balance()} }
        return sum
    }

    fun get_sale_balance(): Float{
        var sum: Float = 0f
        this.inventory.forEach { it.value.forEach{ sum += it.value.get_sale_balance()} }
        return sum
    }

    fun update_product_sale(cat: Categories, cod: String, sale: Int){
        this.inventory[cat]?.get(cod)?.update_qnt_sale(sale)
    }

    fun get_estoque_geral(): List<Products>{
        var list: List<Products> = mutableListOf()
        for(prods in this.inventory.values){
            list += prods.values
        }
        return list
    }

    fun get_estoque_cat(): Map<Categories, Int>{
        var map: Map<Categories, Int> = mutableMapOf()
        for(cat in this.inventory.keys){
            var sum = 0
            for(prod in this.inventory[cat]?.values!!){
                sum += prod.qnt
            }
            map += Pair(cat, sum)
        }
        return map
    }
    fun get_busca(info: Map<String, Any?>): List<Products>{
        var list: List<Products> = listOf()
        val cat = info["Categoria"].toString().uppercase()
        if(cat != "-"){
            for(prod in this.inventory[Categories.valueOf(cat)]?.values!!){
                if(prod.check_search(info as Map<String, String>)){
                    list += prod
                }
            }
        }
        else{
            for(cat in this.inventory.keys){
                for(prod in this.inventory[cat]?.values!!){
                    if(prod.check_search(info as Map<String, String>)){
                        list += prod
                    }
                }
            }
        }
        return list
    }

}