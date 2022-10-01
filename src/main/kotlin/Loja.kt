package products
import org.apache.commons.csv.CSVFormat
import org.jetbrains.kotlinx.dataframe.*
import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.head
import org.jetbrains.kotlinx.dataframe.api.rows
import org.jetbrains.kotlinx.dataframe.io.read
import org.jetbrains.kotlinx.dataframe.io.toCsv
import org.jetbrains.kotlinx.dataframe.io.writeCSV

class Loja() {
    var inv = Inventory()

    fun read_compras(csv: String){
        val df = DataFrame.read(csv)
        print(df.head())
        for (row in df.rows()){
            val cod = row["Código"].toString();
            val qnt = row["Quantidade"].toString();
            val name = row["Nome do produto"].toString();
            val p_buy = row["Preço de compra"].toString();
            val p_sale = row["Preço de venda"].toString();
            val cat = Categories.valueOf(row["Categoria"].toString().uppercase());
            val type = row["Tipo"].toString();
            val size = row["Tamanho"].toString();
            val c_p = row["Cor primaria"].toString();
            val c_s = row["Cor secundário"].toString();
            val ver = row["Versão"].toString();
            val year = row["Ano de frabricação"].toString();
            val mat = row["Material de fabricação"].toString();
            val rel = row["Relevância"].toString();

            if(cat == Categories.COLECIONAVEL){
                this.inv.new_product(cat, name, p_buy, p_sale, cod, qnt, type, mat, size, rel)
            }
            else if(cat == Categories.ROUPA){
                this.inv.new_product(cat, name, p_buy, p_sale, cod, qnt, type, size, c_p, c_s)
            }
            else{
                this.inv.new_product(cat, name, p_buy, p_sale, cod, qnt, type, ver, year)
            }
            this.inv.print_inventory()
            println("\n")
        }
    }

    fun read_vendas(csv: String){
        val df = DataFrame.read(csv)
        for(row in df.rows()){
            val full_cod = row["Código"].toString().split("-")
            val qnt_sale = row["Quantidade"].toString().toInt()

            var cat = full_cod[0]
            val cod = full_cod[1]
            var category: Categories

            if(cat == "C"){
                category = Categories.COLECIONAVEL
            }
            else if(cat == "R"){
                category = Categories.ROUPA
            }
            else{
                category = Categories.ELETRONICO
            }
            this.inv.update_product_sale(category, cod, qnt_sale)
        }
        this.inv.print_inventory()
        println("\n")
    }

    fun get_balancente(){
        val buy = this.inv.get_buy_balance()
        val sale = this.inv.get_sale_balance()
        val df = dataFrameOf(Pair("", listOf("COMPRAS", "VENDAS", "BALANCETE")), Pair("", listOf(buy, sale, buy-sale)))

        df.toCsv(CSVFormat.DEFAULT.withSkipHeaderRecord())
    }

}
