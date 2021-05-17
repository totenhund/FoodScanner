package totenhund.com.foodscanner.product

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import totenhund.com.foodscanner.R
import totenhund.com.foodscanner.databinding.FragmentProductBinding
import totenhund.com.requests.models.ProductVO
import totenhund.com.requests.services.RetrofitServices
import retrofit2.Callback
import retrofit2.Response
import totenhund.com.requests.common.Common


class ProductFragment : Fragment() {

    lateinit var binding: FragmentProductBinding
    private lateinit var mService: RetrofitServices
    private lateinit var viewModel: ProductViewModel
    private lateinit var viewModelFactory: ProductViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_product,
            container,
            false
        )

        val productFragmentArgs by navArgs<ProductFragmentArgs>()
        viewModelFactory = ProductViewModelFactory(requireActivity().application, productFragmentArgs.qrCode)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ProductViewModel::class.java)

        mService = Common.retrofitServices
        lifecycleScope.launch(Dispatchers.IO){
            getProduct(productFragmentArgs.qrCode)
        }

        binding.historyPageButton.setOnClickListener{
            val action = ProductFragmentDirections.actionProductFragmentToStartFragment()
            findNavController(this).navigate(action)
        }

        return binding.root
    }

    private suspend fun getProduct(qrCode: String){


        mService.getProduct(qrCode).enqueue(object : Callback<ProductVO>{
            override fun onFailure(call: Call<ProductVO>, t: Throwable) {
                binding.productTitleTextView.text = "Wi-Fi is not connected"
            }

            override fun onResponse(call: Call<ProductVO>, response: Response<ProductVO>) {
                if(response.body() != null){
                    assignProduct(response.body()!!)
                    lifecycleScope.launch {
                        viewModel.addProduct(response.body()!!)
                    }
                }else{
                    binding.productTitleTextView.text = "EMPTY PRODUCT"
                }
            }
        })


    }

    private fun assignProduct(product: ProductVO){
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