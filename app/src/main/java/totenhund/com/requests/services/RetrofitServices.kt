package totenhund.com.requests.services

import retrofit2.Call
import retrofit2.http.GET
import totenhund.com.requests.models.Product

interface RetrofitServices {
    @GET("products/")
    fun getProduct(qrCode: String): Call<Product>
}