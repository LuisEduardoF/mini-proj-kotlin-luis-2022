package products
import org.jetbrains.kotlinx.dataframe.*
import org.jetbrains.kotlinx.dataframe.api.append
import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.rows
import org.jetbrains.kotlinx.dataframe.api.toMap
import org.jetbrains.kotlinx.dataframe.io.read
import org.jetbrains.kotlinx.dataframe.io.toCsv
import org.jetbrains.kotlinx.dataframe.io.writeCSV
import java.text.DecimalFormat

class Loja() {
    var inv = Inventory()

    fun read_compras(csv: String){
        val df = DataFrame.read(csv)
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
    }

    fun process_busca(csv: String){
        val df = DataFrame.read(csv)
        var res = dataFrameOf(Pair("BUSCAS", listOf()), Pair("QUANTIDADE", listOf()))
        for(row in df.rows()){
            var list = this.inv.get_busca(row.toMap())
            var sum = 0
            list.forEach{ sum += it.qnt }
            res = res.append(row.index() + 1, sum)
        }
        res.writeCSV("src/main/resources/saida/resultado_busca.csv")
    }
    fun get_balancente(){
        val dformat = DecimalFormat("#.##")
        val buy: Float = this.inv.get_buy_balance()
        val sale: Float = this.inv.get_sale_balance()
        val balancete: Float = sale - buy

        val df = dataFrameOf(Pair("COMPRAS", listOf("VENDAS", "BALANCETE")), Pair(dformat.format(buy), listOf(dformat.format(sale), dformat.format(balancete))))

        df.writeCSV("src/main/resources/saida/balancete.csv")
    }

    fun get_estoque_geral(){
        var df = dataFrameOf(Pair("CODIGO", listOf()), Pair("NOME", listOf()), Pair("QUANTIDADE", listOf()))
        for(prod in this.inv.get_estoque_geral()){
            df = df.append("${prod.category + "-" + prod.cod}", prod.name, prod.qnt)
        }
        df.writeCSV("src/main/resources/saida/estoque_geral.csv")
    }

    fun get_estoque_cat(){
        var df = dataFrameOf(Pair("CATERGORIA", listOf()), Pair("QUANTIDADE", listOf()))
        val result = this.inv.get_estoque_cat()
        for(prod in result){
            df = df.append(prod.key, prod.value)
        }
        df.writeCSV("src/main/resources/saida/estoque_categorias.csv")
    }
}
