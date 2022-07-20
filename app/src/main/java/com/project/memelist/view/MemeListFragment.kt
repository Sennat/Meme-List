package com.project.memelist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.memelist.databinding.FragmentMemeListBinding
import com.project.memelist.model.UIState


class MemeListFragment : ViewModelFragment() {

    private lateinit var binding: FragmentMemeListBinding

    private val memeAdapter by lazy {
        MemeAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMemeListBinding.inflate(layoutInflater)
        configureObserver()
        return binding.root
    }

    private fun configureObserver() {
        viewModel.memeListData.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is UIState.Loading -> {
                    viewModel.getDememes(1)
                }
                is UIState.Error -> {
                    binding.pbLoading.visibility = View.GONE
                    binding.tvLoadingText.text = uiState.error.message
                }
                is UIState.Success -> {
                    binding.apply {
                        pbLoading.visibility = View.GONE
                        tvLoadingText.visibility = View.GONE
                        memeAdapter.setMemeList(uiState.response.data)
                        rvMemes.adapter = memeAdapter
                    }
                }
            }
        }
    }
}