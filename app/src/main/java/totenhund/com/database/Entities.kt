package totenhund.com.database

import androidx.room.*


@Entity(tableName = "PRODUCTS")
data class Product(
    @PrimaryKey
    val qrCode: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "hazard") val hazardLevel: String,
    @ColumnInfo(name = "productAdditives") val productAdditives: String,
    @ColumnInfo(name = "calories") val calories: Int
)