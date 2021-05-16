package totenhund.com.foodscanner.product

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import totenhund.com.database.DataConverter
import totenhund.com.database.Product
import totenhund.com.database.ProductDatabase
import totenhund.com.database.ProductRepository
import totenhund.com.requests.models.ProductVO

class ProductViewModel(application: Application, var qrCode: String): AndroidViewModel(application) {

    private lateinit var repository: ProductRepository
    private var db: ProductDatabase = ProductDatabase.getDatabase(application)

    init {
        repository = ProductRepository(db.productDao())
    }

    fun addProduct(product: ProductVO){
        var dataConverter = DataConverter()
        repository.addProduct(Product(product.idQrCode, product.productName, "medium", dataConverter.fromCountryLangList(product.productComposition.foodAdditives), 100))
    }



}