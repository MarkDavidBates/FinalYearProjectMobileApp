package org.wit.wishlistandroid.models

import timber.log.Timber.i

var lastId = 0L

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
            foundNodelist.minTemp = node.minTemp
            foundNodelist.maxTemp = node.maxTemp
            foundNodelist.minHumid = node.minHumid
            foundNodelist.maxHumid = node.maxHumid
            foundNodelist.minMoisture = node.minMoisture
            foundNodelist.maxMoisture = node.maxMoisture
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