package com.example.linkedinclone.main.ui.mynetwork.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.linkedinclone.R
import com.example.linkedinclone.databinding.AddUserRecyclerViewBinding
import com.example.linkedinclone.main.model.ProfileData
import com.example.linkedinclone.utils.Extensions.createCenterDialog
import com.example.linkedinclone.utils.Extensions.loadImage
import com.example.linkedinclone.utils.Extensions.showCenterDialog
import com.example.linkedinclone.utils.Extensions.toast

class PeopleListAdapter(var list: List<ProfileData>) : RecyclerView.Adapter<PeopleListAdapter.PeopleViewHolder>() {
    inner class PeopleViewHolder(private val binding: AddUserRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProfileData) {
            binding.apply {
                userImage.loadImage(R.drawable.test_image)
                userFullName.text = item.fullName
                connectButton
                connectButton.setOnClickListener {
                    it.context.toast("Connection request sent to ${item.fullName}")
                    it.background = ContextCompat.getDrawable(it.context, R.drawable.custom_success_btn)
                    (it as TextView).text = "Sent"
                    (it as TextView).setTextColor(Color.parseColor("#4CAF50"))
                }
                root.setOnClickListener {
                    val dialogBinding = AddUserRecyclerViewBinding.inflate(LayoutInflater.from(it.context), it as ViewGroup, false)
                    val dialog = it.context.createCenterDialog(dialogBinding.root)
                    dialogBinding.apply {
                        userImage.loadImage(R.drawable.abstract_image)
                    }
                    it.context.showCenterDialog(dialog)
                }
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
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