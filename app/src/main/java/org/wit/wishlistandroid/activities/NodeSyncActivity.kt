package org.wit.wishlistandroid.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.wit.wishlistandroid.R
import org.wit.wishlistandroid.databinding.ActivityHomegrowerSyncBinding
import timber.log.Timber


class NodeSyncActivity : AppCompatActivity(){

    private lateinit var binding: ActivityHomegrowerSyncBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = ActivityHomegrowerSyncBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.syncButton.setOnClickListener(){

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://192.168.4.1"))
            startActivity(intent)
            finish()
        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }



}