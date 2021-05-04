package totenhund.com.foodscanner.scanner


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import totenhund.com.foodscanner.R
import totenhund.com.foodscanner.databinding.FragmentScannerBinding


class ScannerFragment : Fragment() {

    private lateinit var codeScanner: CodeScanner
    private lateinit var binding: FragmentScannerBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_scanner, container, false
        )


        val activity = requireActivity()
        codeScanner = CodeScanner(activity, binding.scannerView)
        codeScanner.decodeCallback = DecodeCallback {
            requireActivity().runOnUiThread {
                val action = ScannerFragmentDirections.actionScannerFragmentToProductFragment(it.text)
                findNavController(this).navigate(action)
            }
        }

        codeScanner.errorCallback = ErrorCallback {
            requireActivity().runOnUiThread{
                findNavController(this).navigate(R.id.startFragment)
            }
        }

        binding.scannerView.setOnClickListener {  codeScanner.startPreview()}



        return binding.root
    }


    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }


}