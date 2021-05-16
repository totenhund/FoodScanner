package totenhund.com.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addProduct(product: Product)

    @Query("select * from PRODUCTS")
    fun getAllProducts():LiveData<List<Product>>

    @Query("select * from PRODUCTS where qrCode=:qrCode")
    fun getProductById(qrCode: String): Product

    @Query("delete from PRODUCTS")
    fun deleteAll()

}