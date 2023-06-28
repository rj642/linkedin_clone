package com.example.linkedinclone.main.ui.mynetwork

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.linkedinclone.R
import com.example.linkedinclone.databinding.FragmentMyNetworkBinding
import com.example.linkedinclone.main.interfaces.LoaderInterface
import com.example.linkedinclone.main.model.ProfileData
import com.example.linkedinclone.main.model.RESPONSE
import com.example.linkedinclone.main.ui.MainActivity
import com.example.linkedinclone.main.ui.mynetwork.adapter.PeopleListAdapter
import com.example.linkedinclone.main.ui.post.PostFragment
import com.example.linkedinclone.main.ui.post.PostViewModel
import com.example.linkedinclone.main.viewmodel.MainViewModel
import com.example.linkedinclone.utils.Extensions.logs
import com.example.linkedinclone.utils.Extensions.viewEnabled
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyNetworkFragment : Fragment() {

    private lateinit var binding: FragmentMyNetworkBinding

    private lateinit var adapter: PeopleListAdapter

    private val viewModel: MainViewModel by activityViewModels()

    private var mListener: LoaderInterface? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = context as? MainActivity
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMyNetworkBinding.inflate(inflater, container, false)

        viewModel.usersLoading.observe(viewLifecycleOwner) {
            it?.let {
                when (it) {
                    RESPONSE.IDLE -> {
                        // Show idle behavior here

                    }
                    RESPONSE.LOADING -> {
                        // Show loader here
                        mListener?.onLoad()
                        binding.peopleRecyclerView.viewEnabled(false)
                    }
                    RESPONSE.SUCCESS -> {
                        // Hide loader here
                        mListener?.onSuccess()
                        binding.peopleRecyclerView.viewEnabled(true)
                    }
                    RESPONSE.FAILED -> {
                        // Hide loader here, if data exists then at the bottom display "Please try again"
                        mListener?.onFailed()
                        binding.peopleRecyclerView.viewEnabled(true)
                    }
                }
            }
        }

        viewModel.allUser.observe(viewLifecycleOwner) {
            requireContext().logs("I was called and updated with $it")
            it?.let {
                binding.apply {
                    adapter = PeopleListAdapter(it.users)

                    peopleRecyclerView.adapter = adapter
                }
            }
        }

        return binding.root
    }

}