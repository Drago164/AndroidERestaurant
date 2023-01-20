package fr.isen.savy.androiderestaurant

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.savy.androiderestaurant.model.Items

internal class DishAdapter(private var myArrayList: ArrayList<Items>, val onItemClickListener: () -> Unit) : RecyclerView.Adapter<DishAdapter.MyViewHolder>() {

    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dishTitle: TextView = view.findViewById(R.id.dishTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.food_list, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = myArrayList[position]
        holder.dishTitle.text = item.nameFr
        holder.itemView.setOnClickListener {
            onItemClickListener()
        }
    }
    override fun getItemCount(): Int = myArrayList.size

    @SuppressLint("NotifyDataSetChanged")
    fun refreshList(dishFromAPI: ArrayList<Items>) {
        myArrayList = dishFromAPI
        notifyDataSetChanged()
    }
}