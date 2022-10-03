# mini-proj-kotlin-luis-2022

The objective of this mini project is basically to put into practice the knowledge acquired in the Kotlin programming language. The project was designed for you to exercise, mainly, the concepts of object orientation and collections using the language. In addition, this mini-project will count as **10% of the final grade of the course and must be done individually.**

# Description
Imagine you were hired to build a management system for a geek shop. The store has several products that are classified into three categories: **clothes**, **electronics** and **collectibles**. Regardless of the category, each product must always have the following information:
* Product's name
* Price that was bought to resell
* Price to be sold
* Product code

Depending on the category, the product has other information. If it's a **clothes**, it has:
* Type
  * Shirt, sweatshirt or accessory
* Size
  * PP, S, M, L, XL, XL, XXL
* Primary color
* Secondary color, if any

If it's an **electronic**:
* Type
  * Video game, games, portable or others
* Version
* Year of manufacture

If it's a **collectable**:
* Type
  * Book, doll or others
* Manufacturing material
  * Paper, plastic, steel, mixed or others
* Size in cm
* Relevance
  * Common, medium, rare or very rare

The product code, which is already assigned by the store supplier, can consist of letters and numbers. However, for ease of viewing, the customer wants you to add the first letter of the main category separated by a hyphen to the original code. Therefore, if the product code is CAM02 and it belongs to the collectible category, the system must transform it into C-CAM02. Codes will always be in uppercase.

# Program features
The store management program has 4 major functionalities: product purchase and sale management, inventory management, store balance sheet and search system. Each of the items will be detailed below. Also, along with this description, an example of input and output is provided.

## Purchase and sale management
The first functionality of your program is to manage the purchase and sale of the store. Purchases and sales will be obtained through two .csv files of the same name. The purchase file contains all the product information, in addition to the quantity that was purchased; The sales file has only the code and quantity of the product sold. In both cases, each line in the file represents a buy or sell.

## Inventory management
At the end of all purchase and sale operations, the program needs to generate a consolidated store inventory. To do so, it must generate two files called stock_general.csv and stock_categories.csv. The first file must contain the entire store's inventory with one product per line. For simplicity, the product is only represented by the code, name and quantity. However, you must keep the full inventory in memory. The second file is just a consolidation of categories, that is, each line of the file will have the category and the amount of products it has in it.

## Store balance sheet
The program must also generate a general balance sheet for the store to consolidate purchases and sales. For this, the program must generate a file called balance sheet.csv that must contain, each line, the amount spent on purchases, sales and the balance sheet showing the store's profit or loss.

## Search system
Finally, the program allows searches to be carried out in the store's virtual stock. To perform the search, the program will receive a file called Busca.csv that contains the information that must be searched in the stock. Once the search is performed, your program should return a file called search_result.csv that contains the amount of each of the requested information (see example for better understanding).
