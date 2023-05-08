package com.example.linkedinclone.main.ui.mynetwork.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.linkedinclone.R
import com.example.linkedinclone.databinding.AddUserRecyclerViewBinding
import com.example.linkedinclone.main.model.ProfileData
import com.example.linkedinclone.utils.Extensions.loadImage

class PeopleListAdapter(var list: List<ProfileData>) : RecyclerView.Adapter<PeopleListAdapter.PeopleViewHolder>() {

    inner class PeopleViewHolder(private val binding: AddUserRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProfileData) {
            binding.apply {
                userImage.loadImage(R.drawable.test_image)
                userFullName.text = item.fullName
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        return PeopleViewHolder(AddUserRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        val item = list[position]

        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}