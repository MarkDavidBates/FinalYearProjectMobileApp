package org.wit.wishlistandroid.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.wit.wishlistandroid.databinding.CardHomegrowerBinding
import org.wit.wishlistandroid.models.NodeModel

interface WishlistListener{
    fun onWishlistClick(wishlist: NodeModel, position: Int)
}

class WishlistAdapter constructor(private var wishlists: List<NodeModel>, private val listener: WishlistListener) :
    RecyclerView.Adapter<WishlistAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardHomegrowerBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val wishlist = wishlists[holder.adapterPosition]
        holder.bind(wishlist, listener)
    }

    override fun getItemCount(): Int = wishlists.size

    class MainHolder(private val binding : CardHomegrowerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(node: NodeModel, listener: WishlistListener) {
            binding.nodeTitle.text = node.title
            binding.nodeMinTemp.text = node.minTemp.toString()
            binding.nodeMinHumid.text = node.minHumid.toString()
            binding.nodeMinMoisture.text = node.minMoisture.toString()
            binding.editButton.setOnClickListener{listener.onWishlistClick(node, adapterPosition)}
        }
    }
}