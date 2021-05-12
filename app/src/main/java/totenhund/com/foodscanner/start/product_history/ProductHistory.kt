package totenhund.com.foodscanner.start.product_history

data class ProductHistory(
    val title: String,
    val eAdds: List<String>,
    val calories: Int,
    val hazard: String
)