package com.example.linkedinclone.main.ui.post.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.linkedinclone.R
import com.example.linkedinclone.databinding.PostRecyclerViewBinding
import com.example.linkedinclone.main.model.PostModel
import com.example.linkedinclone.utils.Extensions.loadImage

class PostAdapter(private var list: List<PostModel>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    inner class PostViewHolder(private val binding: PostRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PostModel, pos: Int) {
            binding.apply {

                userImage.loadImage(R.drawable.abstract_image)

                singleImage.root.visibility = if (pos % 2 == 0) View.VISIBLE else View.GONE

                doubleImage.root.visibility = if (pos % 2 != 0) View.VISIBLE else View.GONE

                singleImage.apply {
                    singleImage.loadImage(R.drawable.test_image)
                }

                doubleImage.apply {
                    for (i in 0..1) {
                        // This will use a condition to validate and load image
                        Glide.with(if (i == 0) imageOne else imageTwo)
                            .load(if (i == 0) R.drawable.test_image else R.drawable.abstract_image)
                            .into(if (i == 0) imageOne else imageTwo)
                    }
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(PostRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = list[position]

        holder.bind(item, position)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}