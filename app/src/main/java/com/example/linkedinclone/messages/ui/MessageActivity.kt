package com.example.linkedinclone.messages.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.linkedinclone.R
import com.example.linkedinclone.databinding.ActivityMessageBinding
import com.example.linkedinclone.main.ui.post.PostFragment
import com.example.linkedinclone.messages.model.ContentInfo
import com.example.linkedinclone.messages.model.MessageData
import com.example.linkedinclone.messages.model.MessageModel
import com.example.linkedinclone.messages.ui.adapter.MessageListAdapter

class MessageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMessageBinding

    private val messageList = mutableListOf<MessageModel>()

    private lateinit var adapter: MessageListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            setUpToolbar(toolbar)

            adapter = MessageListAdapter(messageList)

            messageRecyclerView.adapter = adapter

            backButton.setOnClickListener {
                finish()
            }

            tabLayout.addTab(tabLayout.newTab().setText("Focused"))
            tabLayout.addTab(tabLayout.newTab().setText("Other"))

        }

    }

    private fun setUpToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayShowTitleEnabled(false)
            it.setDisplayHomeAsUpEnabled(false)
        }
    }

    private fun handleBackClick() {

    }

}