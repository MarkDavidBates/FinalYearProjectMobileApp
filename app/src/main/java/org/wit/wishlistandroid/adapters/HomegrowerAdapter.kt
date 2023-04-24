package org.wit.wishlistandroid.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.wit.wishlistandroid.databinding.CardHomegrowerBinding
import org.wit.wishlistandroid.main.MainApp
import org.wit.wishlistandroid.models.NodeModel
import org.wit.wishlistandroid.models.DataModel

interface WishlistListener{
    fun onWishlistClick(wishlist: NodeModel, position: Int)
}

class WishlistAdapter constructor(private var wishlists: List<NodeModel>, private var values: DataModel, private val listener: WishlistListener) :
    RecyclerView.Adapter<WishlistAdapter.MainHolder>() {

    lateinit var app: MainApp

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardHomegrowerBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val wishlist = wishlists[holder.adapterPosition]
        holder.bind(wishlist, values, listener)
    }

    override fun getItemCount(): Int = wishlists.size

    class MainHolder(private val binding : CardHomegrowerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(node: NodeModel, value: DataModel, listener: WishlistListener) {
            binding.nodeTitle.text = node.title
            binding.nodeSerialnum.text = node.penID
            binding.nodeMinTemp.text = value.temperature.toString() + "°"
            binding.nodeMinHumid.text = value.humidity.toString() + "%"
            binding.nodeMinMoisture.text = value.moisture.toString() + "%"
            binding.editButton.setOnClickListener{listener.onWishlistClick(node, adapterPosition)}
        }
    }













}
