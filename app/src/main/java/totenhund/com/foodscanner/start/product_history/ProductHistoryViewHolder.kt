package totenhund.com.foodscanner.start.product_history

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ProductHistoryViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

    private var view: View = v

    init {
        v.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        Log.d("ProductHistoryView", "clicked")
    }

}