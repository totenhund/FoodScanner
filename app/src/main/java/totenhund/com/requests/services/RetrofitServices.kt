package totenhund.com.requests.services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import totenhund.com.requests.models.ProductVO

interface RetrofitServices {
    @GET("products/{qrCode}")
    fun getProduct(@Path(value = "qrCode") qrCode: String): Call<ProductVO>
}