package com.project.memelist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.project.memelist.databinding.FragmentStartingPageBinding

class StartingPageFragment : ViewModelFragment() {

    private lateinit var binding: FragmentStartingPageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentStartingPageBinding.inflate(layoutInflater)

        binding.btnMemeList .setOnClickListener {
            viewModel.setLoading()
            findNavController().navigate(
            StartingPageFragmentDirections.actionStartingPageToMemeList())
        }

        binding.btnMemeId.setOnClickListener {
            findNavController().navigate(StartingPageFragmentDirections.actionStartingPageToMemeDetail(1))
        }

        return binding.root
    }
}