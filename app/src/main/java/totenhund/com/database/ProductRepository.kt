package totenhund.com.database

import androidx.lifecycle.LiveData

class ProductRepository (private val productDao: ProductDao){

    val getAllProducts: LiveData<List<Product>> = productDao.getAllProducts()
    fun getProductById(qrCode: String): Product = productDao.getProductById(qrCode)

    fun addProduct(product: Product){
        productDao.addProduct(product)
    }

    fun deleteAll() = productDao.deleteAll()

}