package com.example.linkedinclone.main.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.compose.ui.input.key.Key
import androidx.core.widget.doOnTextChanged
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.linkedinclone.R
import com.example.linkedinclone.databinding.ActivityMainBinding
import com.example.linkedinclone.main.interfaces.CustomToolbarActionInterface
import com.example.linkedinclone.main.interfaces.LoaderInterface
import com.example.linkedinclone.main.model.SearchType
import com.example.linkedinclone.main.viewmodel.MainViewModel
import com.example.linkedinclone.messages.ui.MessageActivity
import com.example.linkedinclone.utils.Extensions.logs
import com.example.linkedinclone.utils.Extensions.showSnackBar
import com.example.linkedinclone.utils.Extensions.toast
import com.example.linkedinclone.utils.Extensions.viewEnabled
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), LoaderInterface, CustomToolbarActionInterface {

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

            /**
             * Instead of overriding onBackPressed() method we'll simply use the below method to override it
             */
            onBackPressedDispatcher.addCallback {
                handleBackPress()
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

    private fun handleBackPress() {
        toast("Press once again to exit")
    }

    override fun onLoad() {
        if (this@MainActivity::binding.isInitialized) {
            binding.apply {
                progressBar.viewEnabled(true)
            }
        }
    }

    override fun onSuccess() {
        if (this@MainActivity::binding.isInitialized) {
            binding.apply {
                progressBar.viewEnabled(false)
            }
        }
    }

    override fun onFailed() {
        if (this@MainActivity::binding.isInitialized) {
            binding.apply {
                progressBar.viewEnabled(false)
                root.showSnackBar("Some error has occurred")
            }
        }
    }

    override fun searchFor(type: SearchType) {
        when (type) {
            SearchType.HOME -> {

            }
            SearchType.NETWORK -> {
                if (this@MainActivity::binding.isInitialized) {
                    binding.search.doOnTextChanged { text, _, _, _ ->
                        viewModel.filterUserList(text.toString().trim())
                    }
                }
            }
            SearchType.POST -> {

            }
            SearchType.NOTIFICATION -> {

            }
            SearchType.JOBS -> {

            }
        }
    }

}