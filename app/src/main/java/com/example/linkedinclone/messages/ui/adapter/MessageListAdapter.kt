package com.example.linkedinclone.messages.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.linkedinclone.R
import com.example.linkedinclone.databinding.MessageRecyclerViewBinding
import com.example.linkedinclone.messages.model.MessageModel
import com.example.linkedinclone.utils.Extensions.loadImage

class MessageListAdapter(var list: List<MessageModel>): RecyclerView.Adapter<MessageListAdapter.MessageViewHolder>() {

    inner class MessageViewHolder(private val binding: MessageRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MessageModel) {
            binding.apply {
                senderProfileImage.loadImage(R.drawable.abstract_image)
                senderName.text = item.profileData.fullName
                message.text = item.message.message[item.message.message.size - 1].message
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(MessageRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val item = list[position]

        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}