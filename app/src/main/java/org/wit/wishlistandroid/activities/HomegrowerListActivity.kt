package org.wit.wishlistandroid.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import org.wit.wishlistandroid.R
import org.wit.wishlistandroid.databinding.ActivityHomegrowerListBinding
import org.wit.wishlistandroid.main.MainApp
import org.wit.wishlistandroid.adapters.WishlistAdapter
import org.wit.wishlistandroid.adapters.WishlistListener
import org.wit.wishlistandroid.models.NodeModel
import timber.log.Timber
import java.util.*

class HomegrowerListActivity : AppCompatActivity(), WishlistListener {

    lateinit var app: MainApp
    private lateinit var binding: ActivityHomegrowerListBinding
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomegrowerListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = WishlistAdapter(app.nodes.findAll(), app.data,this)

        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    Timber.i("Activity refreshed")
                    recreate() // Refresh the current activity
                }
            }
        }, 10000, 10000)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, HomegrowerActivity::class.java)
                getResult.launch(launcherIntent)
            }
            R.id.item_sync -> {
                val launcherIntent = Intent(this, NodeSyncActivity::class.java)
                getResult.launch(launcherIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                (binding.recyclerView.adapter)?.
                notifyItemRangeChanged(0,app.nodes.findAll().size)
            }
        }

    override fun onWishlistClick(node: NodeModel, pos: Int) {
        val launcherIntent = Intent(this, HomegrowerActivity::class.java)
        launcherIntent.putExtra("node_edit", node)
        position = pos
        getClickResult.launch(launcherIntent)
    }

    private val getClickResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()
    ){
        if (it.resultCode == Activity.RESULT_OK){
            (binding.recyclerView.adapter)?.notifyItemRangeChanged(0, app.nodes.findAll().size)
        }
        else // Deleting
            if (it.resultCode == 99)
                    (binding.recyclerView.adapter)?.notifyItemRemoved(position)
    }
}