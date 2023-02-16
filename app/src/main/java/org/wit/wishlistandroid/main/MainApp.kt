package org.wit.wishlistandroid.main

import android.app.Application
import org.wit.wishlistandroid.models.NodeStore
import org.wit.wishlistandroid.models.NodesJSONStore
import timber.log.Timber
import timber.log.Timber.i

class MainApp: Application() {

    //val wishlists = WishlistMemStore()
    lateinit var nodes: NodeStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        nodes = NodesJSONStore(applicationContext)
        i("App started")
    }
}