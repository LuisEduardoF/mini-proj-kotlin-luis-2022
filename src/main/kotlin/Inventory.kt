package products

class Inventory {
    var inventory: MutableMap<String, MutableList<Products>> = mutableMapOf("colecionavel" to mutableListOf<Products>(), "roupa" to mutableListOf<Products>(), "eletronico" to mutableListOf<Products>())

    fun new_product(category: String, vararg input: String){
        var aux: Products
        if(category == "colecionavel"){
            aux = Collectible(input[0], input[1].toFloat(), input[2].toFloat(), input[3], input[4].toInt(), input[5], input[6], input[7], input[8])
        }
        else if(category == "roupa"){
            aux = Clothes(input[0], input[1].toFloat(), input[2].toFloat(), input[3], input[4].toInt(), input[5], input[6], input[7], input[8])
        }
        else{
            aux = Eletronics(input[0], input[1].toFloat(), input[2].toFloat(), input[3], input[4].toInt(), input[5], input[6].toInt(), input[7].toInt())
        }
        this.inventory[category]?.add(aux)
    }

    fun print_inventory(){
        for (category in this.inventory.keys){
            var aux = this.inventory[category]
            println("--------------- ($category)")
            if(category == "roupa"){
                for(r in aux!!){
                    r as Clothes
                    println(r)
                }
            }
            else if(category == "colecionavel"){
                for(r in aux!!){
                    r as Collectible
                    println(r)
                }
            }
            else{
                for(r in aux!!){
                    r as Eletronics
                    println(r)
                }
            }
        }
    }
}