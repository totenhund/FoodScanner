package totenhund.com.foodscanner.start.product_history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.view.marginBottom
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_start.view.*
import kotlinx.android.synthetic.main.product_history_item.view.*
import totenhund.com.foodscanner.R

class ProductHistoryAdapter(private val products: List<ProductHistory>): RecyclerView.Adapter<ProductHistoryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHistoryViewHolder {
        val inflatedView = parent.inflate(R.layout.product_history_item, false)
        return ProductHistoryViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ProductHistoryViewHolder, position: Int) = holder.itemView.run {
        titleProductHistory.text = products[position].title
        caloriesProductHistory.text = products[position].calories.toString()
        var eAddsString = ""
        products[position].eAdds.forEach {
            eAddsString += "$it "
        }
        eAddsProductHistory.text = eAddsString
        hazardTextView.text = products[position].hazard
    }

    override fun getItemCount(): Int = products.size

    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }

}