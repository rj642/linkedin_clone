package com.example.linkedinclone.main.ui.post

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.linkedinclone.R
import com.example.linkedinclone.common.MainApplication
import com.example.linkedinclone.databinding.FragmentPostBinding
import com.example.linkedinclone.main.interfaces.LoaderInterface
import com.example.linkedinclone.main.model.PostModel
import com.example.linkedinclone.main.model.ProfileData
import com.example.linkedinclone.main.model.RESPONSE
import com.example.linkedinclone.main.ui.MainActivity
import com.example.linkedinclone.main.ui.post.adapter.PostAdapter
import com.example.linkedinclone.main.viewmodel.MainViewModel
import com.example.linkedinclone.utils.Extensions.logs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostFragment : Fragment() {

    private lateinit var binding: FragmentPostBinding

    private val dummyList = mutableListOf<PostModel>()

    private lateinit var adapter: PostAdapter

    private var mListener: LoaderInterface? = null

    private val viewModel: MainViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = context as? MainActivity
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPostBinding.inflate(inflater, container, false)

        viewModel.postsLoading.observe(viewLifecycleOwner) {
            it?.let {
                when (it) {
                    RESPONSE.IDLE -> {
                        // Handle the idle state here

                    }
                    RESPONSE.LOADING -> {
                        // Call interface method
                        mListener?.onLoad()
                        // Hide recyclerview from here
                        binding.postRecyclerView.visibility = View.GONE
                    }
                    RESPONSE.SUCCESS -> {
                        // Call interface method
                        mListener?.onSuccess()
                        // Make recyclerview visible from here
                        binding.postRecyclerView.visibility = View.VISIBLE
                    }
                    RESPONSE.FAILED -> {
                        // Call interface method
                        mListener?.onFailed()
                        // Make recyclerview visible from here
                        binding.postRecyclerView.visibility = View.VISIBLE
                    }
                }
            }
        }

        viewModel.allPost.observe(viewLifecycleOwner) {
            it?.let {
                // capture response here and update UI
                binding.apply {
                    adapter = PostAdapter(it.posts)

                    postRecyclerView.adapter = adapter
                }
                requireContext().logs("Response received from backend is $it")
            }
        }

        return binding.root
    }
}