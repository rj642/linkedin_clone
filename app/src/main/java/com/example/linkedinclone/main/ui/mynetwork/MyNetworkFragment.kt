package com.example.linkedinclone.main.ui.mynetwork

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.linkedinclone.R
import com.example.linkedinclone.databinding.FragmentMyNetworkBinding
import com.example.linkedinclone.main.model.ProfileData
import com.example.linkedinclone.main.ui.mynetwork.adapter.PeopleListAdapter
import com.example.linkedinclone.main.ui.post.PostFragment

class MyNetworkFragment : Fragment() {

    private lateinit var binding: FragmentMyNetworkBinding

    private val listOfUser = mutableListOf<ProfileData>()

    private lateinit var adapter: PeopleListAdapter

    init {
        for(i in 0..100) {
            listOfUser.add(PostFragment.profileData)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMyNetworkBinding.inflate(inflater, container, false)

        binding.apply {

            adapter = PeopleListAdapter(listOfUser)

            peopleRecyclerView.adapter = adapter

        }

        return binding.root
    }
}