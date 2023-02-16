package org.wit.wishlistandroid.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import com.google.android.material.snackbar.Snackbar
import org.wit.wishlistandroid.R
import org.wit.wishlistandroid.databinding.ActivityHomegrowerBinding
import org.wit.wishlistandroid.main.MainApp
import org.wit.wishlistandroid.models.NodeModel
import timber.log.Timber.i

class HomegrowerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomegrowerBinding
    var node = NodeModel()
    lateinit var app : MainApp
    var edit = false


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityHomegrowerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)

        app = application as MainApp
        i("Activity started...")



        if(intent.hasExtra("node_edit")){
            edit = true
            node = intent.extras?.getParcelable("node_edit")!!
            binding.nodeTitle.setText(node.title)
            binding.nodeMinTemp.setText(node.minTemp.toString())
            binding.nodeMaxTemp.setText(node.maxTemp.toString())
            binding.nodeMinHumid.setText(node.minHumid.toString())
            binding.nodeMaxHumid.setText(node.maxHumid.toString())
            binding.nodeMinMoisture.setText(node.minMoisture.toString())
            binding.nodeMaxMoisture.setText(node.maxMoisture.toString())
            binding.btnAdd.setText(R.string.save_node)
        }


        binding.btnAdd.setOnClickListener() {
            node.title = binding.nodeTitle.text.toString()
            node.minTemp = Integer.parseInt(binding.nodeMinTemp.text.toString())
            node.maxTemp = Integer.parseInt(binding.nodeMaxTemp.text.toString())
            node.minHumid= Integer.parseInt(binding.nodeMinHumid.text.toString())
            node.maxHumid = Integer.parseInt(binding.nodeMaxHumid.text.toString())
            node.minMoisture = Integer.parseInt(binding.nodeMinMoisture.text.toString())
            node.maxMoisture = Integer.parseInt(binding.nodeMaxMoisture.text.toString())
            if (node.title.isEmpty()) {
                Snackbar.make(it, R.string.enter_title, Snackbar.LENGTH_LONG)
                    .show()
            }
            else {
                if(edit){

                    app.nodes.update(node.copy())
                } else{
                    app.nodes.create(node.copy())
                }
            }
            setResult(RESULT_OK)
            finish()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_homegrower, menu)
        if (edit) menu.getItem(0).isVisible = true
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_delete -> {
                setResult(99)
                app.nodes.delete(node)
                finish()
            }
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}