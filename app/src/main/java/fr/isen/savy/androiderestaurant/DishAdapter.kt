package fr.isen.savy.androiderestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.savy.androiderestaurant.model.Items

internal class DishAdapter(
    private var myArrayList: ArrayList<Items>,
    val onItemClickListener: (Items) -> Unit) :
    RecyclerView.Adapter<DishAdapter.MyViewHolder>() {

    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val contentName: TextView = view.findViewById(R.id.dishTitle)
        val priceView: TextView = view.findViewById(R.id.itemPriceView)
        val contentImage: ImageView = view.findViewById(R.id.image_meal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.food_list, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = myArrayList[position]
        holder.contentName.text = item.nameFr
        holder.contentName.setOnClickListener {
            onItemClickListener(item)
        }

        if (item.images[0].isNotEmpty()) {
            Picasso.get().load(myArrayList[position].images[0])
                .placeholder(R.drawable.android_logo)
                .into(holder.contentImage)
        }

        val price = item.prices[0]
        holder.priceView.text = price.price
        holder.priceView.setOnClickListener {
            onItemClickListener(item)
        }
    }
    override fun getItemCount(): Int {
        return myArrayList.size
    }

    fun refreshList(dishFromAPI: ArrayList<Items>) {
        myArrayList = dishFromAPI
        notifyDataSetChanged()
    }
}