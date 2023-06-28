package com.example.linkedinclone.main.ui.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.linkedinclone.R
import com.example.linkedinclone.databinding.FragmentHomeBinding
import com.example.linkedinclone.main.interfaces.LoaderInterface
import com.example.linkedinclone.main.model.RESPONSE
import com.example.linkedinclone.main.ui.MainActivity
import com.example.linkedinclone.main.viewmodel.MainViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewModel.homeLoading.observe(viewLifecycleOwner) {
            it?.let {
                when (it) {
                    RESPONSE.IDLE -> {
                        // Handle the idle state
                        mListener?.onIdle()
                    }
                    RESPONSE.LOADING -> {
                        // Handle the loading state
                        mListener?.onLoad()
                    }
                    RESPONSE.SUCCESS -> {
                        // Handle the success state
                        mListener?.onSuccess()
                    }
                    RESPONSE.FAILED -> {
                        // Handle the failed state
                        mListener?.onFailed()
                    }
                }
            }
        }

        binding.apply {

        }

        return binding.root
    }

    /*companion object {
        *//**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         *//*
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }*/
}