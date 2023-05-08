package com.example.linkedinclone.main.ui.post.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.linkedinclone.R
import com.example.linkedinclone.databinding.CommentRecyclerViewBinding
import com.example.linkedinclone.databinding.PostFullComponentViewBinding
import com.example.linkedinclone.main.model.CommentData
import com.example.linkedinclone.utils.Extensions.loadImage

class CommentAdapter(var list: List<CommentData>): RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    inner class CommentViewHolder(private val binding: CommentRecyclerViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CommentData) {
            binding.apply {
                senderProfileImage.loadImage(R.drawable.abstract_image)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(CommentRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val item = list[position]

        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}