package totenhund.com.foodscanner.product

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import retrofit2.Call
import totenhund.com.foodscanner.R
import totenhund.com.foodscanner.databinding.FragmentProductBinding
import totenhund.com.requests.models.Product
import totenhund.com.requests.services.RetrofitServices
import retrofit2.Callback
import retrofit2.Response
import totenhund.com.requests.common.Common


class ProductFragment : Fragment() {

    lateinit var binding: FragmentProductBinding
    private lateinit var mService: RetrofitServices

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_product,
            container,
            false
        )

        mService = Common.retrofitServices
        val productFragmentArgs by navArgs<ProductFragmentArgs>()
        getProduct(productFragmentArgs.qrCode)

        return binding.root
    }

    private fun getProduct(qrCode: String){

        mService.getProduct(qrCode).enqueue(object : Callback<Product>{
            override fun onFailure(call: Call<Product>, t: Throwable) {
                binding.productTitleTextView.text = "Wi-Fi is not connected"
            }

            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                if(response.body() != null){
                    assignProduct(response.body()!!)
                }else{
                    binding.productTitleTextView.text = "EMPTY PRODUCT"
                }
            }
        })

    }

    private fun assignProduct(product: Product){
        binding.productTitleTextView.text = product.productName

        var foodIngredientsText = ""
        product.productComposition.foodIngredients.forEachIndexed { index, element ->
            foodIngredientsText += if(index != product.productComposition.foodIngredients.size - 1){
                "$element, "
            }else{
                element
            }
        }


        var foodAdditivesText = ""
        product.productComposition.foodAdditives.forEachIndexed { index, element ->
            foodAdditivesText += if(index != product.productComposition.foodAdditives.size - 1){
                "$element, "
            }else{
                element
            }
        }

        binding.ingredientsTextView.text = foodIngredientsText
        binding.eAddsTextView.text = foodAdditivesText

    }

}