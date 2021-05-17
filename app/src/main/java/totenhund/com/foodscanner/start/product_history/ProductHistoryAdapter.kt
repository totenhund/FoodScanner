package totenhund.com.foodscanner.start.product_history

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.view.marginBottom
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_start.view.*
import kotlinx.android.synthetic.main.product_history_item.view.*
import totenhund.com.database.Product
import totenhund.com.foodscanner.R
import totenhund.com.foodscanner.start.StartFragmentDirections

class ProductHistoryAdapter(private var fragment: Fragment): RecyclerView.Adapter<ProductHistoryAdapter.ProductHistoryViewHolder>() {

    private var products: List<Product> = emptyList()
    var navController: NavController? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHistoryViewHolder {
        val inflatedView = parent.inflate(R.layout.product_history_item, false)
        return ProductHistoryViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ProductHistoryViewHolder, position: Int) {
        holder.itemView.titleProductHistory.text = products[position].title
        holder.itemView.caloriesProductHistory.text = products[position].calories.toString()
        var eAddsString = ""
//        products[position].productAdditives.forEach {
//            eAddsString += "$it "
//        }
        holder.itemView.eAddsProductHistory.text = products[position].productAdditives
        holder.itemView.hazardTextView.text = products[position].hazardLevel

        holder.apply {
            with(holder.itemView){
                itemView.setOnClickListener {
                    val action = StartFragmentDirections.actionStartFragmentToProductFragment2()
                    NavHostFragment.findNavController(fragment).navigate(action)
                }
            }
        }

    }

    override fun getItemCount(): Int = products.size

    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }

    fun setProducts(products: List<Product>){
        this.products = products
        notifyDataSetChanged()
    }

    inner class ProductHistoryViewHolder(v: View) : RecyclerView.ViewHolder(v)



}