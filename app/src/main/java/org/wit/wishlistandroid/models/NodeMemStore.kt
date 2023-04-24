package org.wit.wishlistandroid.models

import org.wit.wishlistandroid.main.MainApp
import timber.log.Timber.i

var lastId = 0L
var values = MainApp()

internal fun getId(): Long{
    return lastId++
}

class WishlistMemStore : NodeStore{
    val wishlists = ArrayList<NodeModel>()

    override fun findAll(): List<NodeModel> {
        return wishlists
    }

    override fun create(wishlist: NodeModel) {
        wishlist.id = getId()
        wishlists.add(wishlist)
        logAll()
    }

    override fun update(node: NodeModel) {
        var foundNodelist: NodeModel? = wishlists.find { p -> p.id == node.id }
        if (foundNodelist != null){
            foundNodelist.title = node.title
            foundNodelist.penID = node.penID
            foundNodelist.temperature = node.temperature
            foundNodelist.humidity = node.humidity
            foundNodelist.moisture = node.moisture
            logAll()
        }
    }

    override fun delete(wishlist: NodeModel) {
        wishlists.remove(wishlist)
    }

    fun logAll(){
        wishlists.forEach{i("$it")}
    }
}