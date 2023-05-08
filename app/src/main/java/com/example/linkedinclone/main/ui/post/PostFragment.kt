package com.example.linkedinclone.main.ui.post

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.linkedinclone.R
import com.example.linkedinclone.databinding.FragmentPostBinding
import com.example.linkedinclone.main.model.PostModel
import com.example.linkedinclone.main.model.ProfileData
import com.example.linkedinclone.main.ui.post.adapter.PostAdapter

class PostFragment : Fragment() {

    private lateinit var binding: FragmentPostBinding

    private val dummyList = mutableListOf<PostModel>()

    private lateinit var adapter: PostAdapter

    companion object {
        val profileData = ProfileData(uid="123", username="rj_a", fullName = "Rahul Jha", followerCount = 161, bioData="Principal Software Engineer working day and night", isMutualConnection = true)
    }
    init {
        dummyList.add(PostModel(profileData = profileData, description = "This is a test post 1"))
        dummyList.add(PostModel(profileData = profileData, description = "This is a test post 2"))
        dummyList.add(PostModel(profileData = profileData, description = "This is a test post 3"))
        dummyList.add(PostModel(profileData = profileData, description = "This is a test post 4"))
        dummyList.add(PostModel(profileData = profileData, description = "This is a test post 5"))
        dummyList.add(PostModel(profileData = profileData, description = "This is a test post 6"))
        dummyList.add(PostModel(profileData = profileData, description = "This is a test post 7"))
        dummyList.add(PostModel(profileData = profileData, description = "This is a test post 8"))
        dummyList.add(PostModel(profileData = profileData, description = "This is a test post 9"))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPostBinding.inflate(inflater, container, false)

        binding.apply {

            adapter = PostAdapter(dummyList)

            postRecyclerView.adapter = adapter

        }

        return binding.root
    }
}