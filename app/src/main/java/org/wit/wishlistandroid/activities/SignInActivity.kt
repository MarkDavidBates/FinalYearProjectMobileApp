package org.wit.wishlistandroid.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.wit.wishlistandroid.databinding.ActivityHomegrowerSigninBinding
import com.google.android.material.snackbar.Snackbar
import org.wit.wishlistandroid.R
import timber.log.Timber.i

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomegrowerSigninBinding
    private lateinit var imageIntentLauncher : ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = ActivityHomegrowerSigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startButton.setOnClickListener()
        {
            val intent = Intent(this, HomegrowerListActivity::class.java)
            startActivity(intent)
        }
    }
}