package com.example.articleria

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListPlantAdapter(private val listPlant: ArrayList<Plant>) :
    RecyclerView.Adapter<ListPlantAdapter.ListViewHolder>() {

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_plant, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listPlant.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val plant = listPlant[position]
        holder.imgPhoto.setImageResource(plant.photo)
        holder.tvName.text = plant.name
        holder.tvDescription.text = plant.description

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ArticleActivity::class.java).apply {
                putExtra("key_plant", plant) // Pass the Plant object
            }
            holder.itemView.context.startActivity(intent)
        }
    }
}
