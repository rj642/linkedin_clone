package com.example.linkedinclone.main.ui.mynetwork.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.linkedinclone.R
import com.example.linkedinclone.databinding.AddUserRecyclerViewBinding
import com.example.linkedinclone.main.model.Users
import com.example.linkedinclone.utils.Extensions.createCenterDialog
import com.example.linkedinclone.utils.Extensions.loadImageUrl
import com.example.linkedinclone.utils.Extensions.showCenterDialog
import com.example.linkedinclone.utils.Extensions.toast

class PeopleListAdapter(
    var list: List<Users>
) : RecyclerView.Adapter<PeopleListAdapter.PeopleViewHolder>() {

    fun updateList(newList: List<Users>) {
        this.list = newList
        notifyItemRangeChanged(0, newList.size)
    }

    inner class PeopleViewHolder(private val binding: AddUserRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Users) {
            binding.apply {
                userImage.loadImageUrl(item.image)
                userFullName.text = "${item.firstName} ${item.lastName}"
                userBio.text = item.company.title

                connectButton.setOnClickListener {
                    it.context.toast("Connection request sent to ${item.firstName}")
                    it.background =
                        ContextCompat.getDrawable(it.context, R.drawable.custom_success_btn)
                    (it as TextView).text = "Sent"
                    it.setTextColor(Color.parseColor("#4CAF50"))
                }

                root.setOnClickListener {
                    val dialogBinding = AddUserRecyclerViewBinding.inflate(
                        LayoutInflater.from(it.context),
                        it as ViewGroup,
                        false
                    )
                    val dialog = it.context.createCenterDialog(dialogBinding.root)
                    dialogBinding.apply {
                        userFullName.text = "${item.firstName} ${item.lastName}"
                        userBio.text = item.company.title
                        userImage.loadImageUrl(item.image)
                    }
                    it.context.showCenterDialog(dialog)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        return PeopleViewHolder(
            AddUserRecyclerViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        val item = list[position]

        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}