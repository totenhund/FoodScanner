package totenhund.com.foodscanner.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import totenhund.com.database.Product
import totenhund.com.database.ProductDatabase
import totenhund.com.database.ProductRepository

class StartViewModel(application: Application): AndroidViewModel(application) {

    private var _allProducts = MutableLiveData<List<Product>>()
    val allProducts: LiveData<List<Product>>
        get() = _allProducts
    private lateinit var repository: ProductRepository

    init {
        val productDao = ProductDatabase.getDatabase(application).productDao()
        val repository = ProductRepository(productDao)

        if(repository.getAllProducts.value != null){
            _allProducts.value = repository.getAllProducts.value
        }else{
            _allProducts.value = emptyList()
        }

    }

    fun deleteAll(){
        repository.deleteAll()
    }

    fun getProductById(qrCode: String){
        repository.getProductById(qrCode)
    }

}