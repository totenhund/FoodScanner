package totenhund.com.database

import androidx.lifecycle.LiveData

class ProductRepository (private val productDao: ProductDao){

    suspend fun getAllProducts(): List<Product> = productDao.getAllProducts()
    suspend fun getProductById(qrCode: String): Product = productDao.getProductById(qrCode)

    suspend fun addProduct(product: Product){
        productDao.addProduct(product)
    }

    suspend fun deleteAll() = productDao.deleteAll()

}