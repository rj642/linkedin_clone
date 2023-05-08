package com.example.linkedinclone.main.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.linkedinclone.R
import com.example.linkedinclone.databinding.ActivityMainBinding
import com.example.linkedinclone.main.viewmodel.MainViewModel
import com.example.linkedinclone.messages.ui.MessageActivity
import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            setUpToolbar(toolbar)

            val navController = findNavController(R.id.fragment_content)

            val appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.layout.fragment_home,
                    R.layout.fragment_my_network,
                    R.layout.fragment_post,
                    R.layout.fragment_notification,
                    R.layout.fragment_job
                )
            )

            setupActionBarWithNavController(navController, appBarConfiguration)

            bottomNavbar.setupWithNavController(navController)

            chatIcon.setOnClickListener {
                startActivity(Intent(this@MainActivity, MessageActivity::class.java))
            }

        }

    }

    private fun setUpToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(false)
            it.setDisplayShowTitleEnabled(false)
        }
    }
}