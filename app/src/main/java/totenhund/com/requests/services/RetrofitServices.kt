package totenhund.com.requests.services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Part
import retrofit2.http.Path
import totenhund.com.requests.models.Product

interface RetrofitServices {
    @GET("products/{qrCode}")
    fun getProduct(@Path(value = "qrCode") qrCode: String): Call<Product>
}