package products
import org.jetbrains.kotlinx.dataframe.*
import org.jetbrains.kotlinx.dataframe.api.forEach
import org.jetbrains.kotlinx.dataframe.api.head
import org.jetbrains.kotlinx.dataframe.api.rows
import org.jetbrains.kotlinx.dataframe.io.read

class Loja(){
    var inv = Inventory()

    fun read_compras(csv: String){
        val df = DataFrame.read(csv)
        for (row in df.rows()){
            val cod = row["Código"].toString();
            val qnt = row["Quantidade"].toString();
            val name = row["Nome do produto"].toString();
            val p_buy = row["Preço de compra"].toString();
            val p_sale = row["Preço de venda"].toString();
            val cat = row["Categoria"].toString();
            val type = row["Tipo"].toString();
            val size = row["Tamanho"].toString();
            val c_p = row["Cor primaria"].toString();
            val c_s = row["Cor secundário"].toString();
            val ver = row["Versão"].toString();
            val year = row["Ano de frabricação"].toString();
            val mat = row["Material de fabricação"].toString();
            val rel = row["Relevância"].toString();

            if(cat == "colecionavel"){
                this.inv.new_product(cat, name, p_buy, p_sale, cod, qnt, type, mat, size, rel)
            }
            else if(cat == "roupa"){
                this.inv.new_product(cat, name, p_buy, p_sale, cod, qnt, type, size, c_p, c_s)
            }
            else{
                this.inv.new_product(cat, name, p_buy, p_sale, cod, qnt, type, ver, year)
            }
            this.inv.print_inventory()
            println("\n")
        }
    }
}
