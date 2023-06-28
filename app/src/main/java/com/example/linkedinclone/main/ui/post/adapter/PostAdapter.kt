package com.example.linkedinclone.main.ui.post.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.linkedinclone.R
import com.example.linkedinclone.databinding.PostFullComponentViewBinding
import com.example.linkedinclone.databinding.PostRecyclerViewBinding
import com.example.linkedinclone.main.model.CommentData
import com.example.linkedinclone.main.model.PostModel
import com.example.linkedinclone.main.model.UpdatedPostModel
import com.example.linkedinclone.main.ui.post.PostFragment
import com.example.linkedinclone.utils.Extensions.createBottomSheet
import com.example.linkedinclone.utils.Extensions.loadImage
import com.example.linkedinclone.utils.Extensions.showBottomSheet

class PostAdapter(
    private var list: List<UpdatedPostModel>,
    var userNameList: List<Map<Int, String>>? = emptyList()
) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

//    var commentData = mutableListOf<CommentData>()

//    private lateinit var adapter: CommentAdapter

    /*init {
        val profileData = PostFragment.profileData
        for (i in 0..100) {
            commentData.add(CommentData(userData = profileData, commentData="Hey there it's me $i",))
        }
        adapter = CommentAdapter(commentData)
    }*/

    private fun returnUserName(userId: Int) = if (userNameList?.isNotEmpty() == true) {
        var name = ""
        userNameList?.let {
            it.forEach { item ->
                if (item.containsKey(userId)) {
                    name = item[userId] ?: ""
                } else {
                    // not found
                }
            }
        }
        name
    } else {
        ""
    }

    inner class PostViewHolder(private val binding: PostRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UpdatedPostModel, pos: Int, userData: List<Map<Int, String>>?) {
            binding.apply {

                userImage.loadImage(R.drawable.abstract_image)

                singleImage.root.visibility = if (pos % 2 == 0) View.VISIBLE else View.GONE

                doubleImage.root.visibility = if (pos % 2 != 0) View.VISIBLE else View.GONE

                singleImage.apply {
                    singleImage.loadImage(R.drawable.test_image)
                }



                userLayout.userFullName.text = returnUserName(item.userId)

                postContent.text = item.body

                doubleImage.apply {
                    for (i in 0..1) {
                        // This will use a condition to validate and load image
                        Glide.with(if (i == 0) imageOne else imageTwo)
                            .load(if (i == 0) R.drawable.test_image else R.drawable.abstract_image)
                            .into(if (i == 0) imageOne else imageTwo)
                    }
                }

                root.setOnClickListener {
                    val dialogBinding = PostFullComponentViewBinding.inflate(
                        LayoutInflater.from(it.context),
                        it as ViewGroup,
                        false
                    )
                    val bottomSheet =
                        it.context.createBottomSheet(dialogBinding.root, fullHeight = true)
                    dialogBinding.apply {
                        userImage.loadImage(R.drawable.abstract_image)
                        singleImage.root.visibility = View.VISIBLE
                        singleImage.apply {
                            singleImage.loadImage(R.drawable.test_image)
                        }
//                        commentRecyclerView.adapter = adapter
                    }
                    it.context.showBottomSheet(bottomSheet)
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            PostRecyclerViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = list[position]

        holder.bind(item, position, userNameList)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}