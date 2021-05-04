package totenhund.com.foodscanner.requests.models

data class Product(
    var title: String,
    var qrCode: String,
    var foodAdditives: List<String>,
    var foodIngredients: List<String>
)