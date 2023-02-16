package org.wit.wishlistandroid.models

interface NodeStore {

    fun findAll(): List<NodeModel>

    fun create(wishlist: NodeModel)

    fun update(wishlist: NodeModel)

    fun delete(wishlist: NodeModel)
}