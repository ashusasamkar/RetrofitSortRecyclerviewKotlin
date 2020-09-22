package ash.sasamkar.plobaltask.adapter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ash.sasamkar.plobaltask.ChartActivity
import ash.sasamkar.plobaltask.R
import ash.sasamkar.plobaltask.model.Store
import kotlinx.android.synthetic.main.list_items.view.*


class StoreAdapter(val store: List<Store>,val flag : Int) : RecyclerView.Adapter<StoreAdapter.StoreViewHolder> (){
    class StoreViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {

        return StoreViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_items, parent, false))
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int)  {
        val stores = store[position]
        val activity = holder.view.context
        holder.view.tv_company_name.text = stores.name
        holder.view.tv_total_price.text = stores.currency + " " + stores.data?.totalSale?.total.toString()

        val monthData  = when (flag) {
            0 -> stores.data?.totalSale?.monthWise
            1 -> stores.data?.addToCart?.monthWise
            2 -> stores.data?.downloads?.monthWise
            else -> stores.data?.sessions?.monthWise
        }

        holder.view.cardView.setOnClickListener(
                View.OnClickListener {
                    val bundle :(Bundle) = Bundle()
                    val intent :(Intent)= Intent(activity,ChartActivity::class.java)

                    intent.putExtra("jan",monthData?.jan?.toFloat())
                    intent.putExtra("feb",monthData?.feb?.toFloat())
                    intent.putExtra("mar",monthData?.mar?.toFloat())
                    intent.putExtra("apr",monthData?.apr?.toFloat())
                    intent.putExtra("may",monthData?.may?.toFloat())
                    intent.putExtra("jun",monthData?.jun?.toFloat())
                    intent.putExtras(bundle)
                    activity.startActivity(intent)


                })

    }

    override fun getItemCount()= store.size
}


