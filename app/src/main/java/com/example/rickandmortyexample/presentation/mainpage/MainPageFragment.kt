package com.example.rickandmortyexample.presentation.mainpage

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyexample.adapter.ClickListener
import com.example.rickandmortyexample.adapter.RecyclerAdapter
import com.example.rickandmortyexample.databinding.FragmentMainPageBinding
import com.example.secondgallery.adapter.PaginationScrollListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainPageFragment : Fragment() {

    private lateinit var binding: FragmentMainPageBinding
    private val viewModel: MainPageViewModel by viewModels()
    private var adapter: RecyclerAdapter = RecyclerAdapter(ClickListener {
        viewModel.onCardClicked(it)
    })
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainPageBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerAdapter()
        initViewModel()

        viewModel.navigateToItemInfo.observe(viewLifecycleOwner, ) {
            id ->
            id?.let {
                navigateToCharactersDetailFragment(id)
            }
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun initViewModel() {
        viewModel.getAllCharacterData().observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.setCharacterData(it)
                adapter.notifyDataSetChanged()
            }
        }
        viewModel.loadNextPageOfData()
    }

    private fun initRecyclerAdapter() {

        val recyclerView: RecyclerView = binding.recyclerview
        val progressBar: ProgressBar = binding.progressbar
        progressBar.isVisible = false

        var linearLayoutManager = GridLayoutManager(this.context, 1)
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            linearLayoutManager = GridLayoutManager(this.context, 2)
        }

        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object : PaginationScrollListener(linearLayoutManager) {
            override fun loadMoreItems() {
                progressBar.isVisible = true
                viewModel.loadNextPageOfData()
                progressBar.isVisible = false
            }
        })

    }

    private fun navigateToCharactersDetailFragment(id: Int) {

        val action = MainPageFragmentDirections.actionMainPageFragmentToItemInfoFragment(
            id
        )
        findNavController().navigate(action)
        viewModel.doneNavigating()

    }

}