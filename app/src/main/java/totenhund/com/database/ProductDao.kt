package totenhund.com.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProduct(product: Product)

    @Query("select * from PRODUCTS")
    suspend fun getAllProducts():List<Product>

    @Query("select * from PRODUCTS where qrCode=:qrCode")
    suspend fun getProductById(qrCode: String): Product

    @Query("delete from PRODUCTS")
    suspend fun deleteAll()

}