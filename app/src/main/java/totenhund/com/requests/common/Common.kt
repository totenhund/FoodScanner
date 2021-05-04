package totenhund.com.requests.common

import totenhund.com.requests.client.RetrofitClient
import totenhund.com.requests.services.RetrofitServices

object Common {
    private val BASE_URL = "http://localhost:8080/"
    val retrofitServices: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL)
            .create(RetrofitServices::class.java)

}