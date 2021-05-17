package totenhund.com.foodscanner.start

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import totenhund.com.foodscanner.R
import totenhund.com.foodscanner.databinding.FragmentStartBinding
import totenhund.com.foodscanner.start.product_history.ProductHistoryAdapter


class StartFragment : Fragment() {


    private lateinit var binding: FragmentStartBinding
    private lateinit var adapter: ProductHistoryAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var viewModel: StartViewModel
    private lateinit var viewModelFactory: StartViewModelFactory

    companion object {
        private const val CAMERA_PERMISSION_CODE = 100
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_start,
            container,
            false
        )

        viewModelFactory = StartViewModelFactory(requireActivity().application)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(StartViewModel::class.java)

        linearLayoutManager = LinearLayoutManager(requireContext())
        binding.productsHistoryListView.layoutManager = linearLayoutManager

        adapter = ProductHistoryAdapter(this)

        viewModel.allProducts.observe(viewLifecycleOwner, Observer {
            adapter.setProducts(it)
        })

        binding.productsHistoryListView.adapter = adapter



        binding.scanButton.setOnClickListener {
            requestPermissions(arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_CODE)
        }


        return binding.root
    }


    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            CAMERA_PERMISSION_CODE -> {

                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)) {

                    val action = StartFragmentDirections.actionStartFragmentToScannerFragment()
                    findNavController(this).navigate(action)
                } else {
                    // Explain to the user that the feature is unavailable because
                    // the features requires a permission that the user has denied.
                    // At the same time, respect the user's decision. Don't link to
                    // system settings in an effort to convince the user to change
                    // their decision.

                }
                return
            }

            else -> {
                // Ignore all other requests.
            }
        }
    }


}