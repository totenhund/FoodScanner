package totenhund.com.requests.models

data class Product(
    var productName: String,
    var idQrCode: String,
    var productComposition: ProductComposition
)