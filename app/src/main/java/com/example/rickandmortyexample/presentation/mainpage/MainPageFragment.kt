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
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.domain.entity.CharacterModel
import com.example.rickandmortyexample.Const.CHARACTER_EPISODE
import com.example.rickandmortyexample.Const.CHARACTER_GENDER
import com.example.rickandmortyexample.Const.CHARACTER_IMAGEURL
import com.example.rickandmortyexample.Const.CHARACTER_LOCATION
import com.example.rickandmortyexample.Const.CHARACTER_NAME
import com.example.rickandmortyexample.Const.CHARACTER_ORIGIN
import com.example.rickandmortyexample.Const.CHARACTER_SPECIES
import com.example.rickandmortyexample.Const.CHARACTER_STATUS
import com.example.rickandmortyexample.Const.CHARACTER_TYPE
import com.example.rickandmortyexample.R
import com.example.rickandmortyexample.adapter.RecyclerAdapter
import com.example.rickandmortyexample.databinding.FragmentMainPageBinding
import com.example.secondgallery.adapter.PaginationScrollListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainPageFragment : Fragment() {

    private var _binding: FragmentMainPageBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainPageViewModel by viewModels()

    private lateinit var adapter: RecyclerAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var linearLayoutManager: GridLayoutManager
    private lateinit var progressBar: ProgressBar
    private var page: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainPageBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initRecyclerAdapter()
        initViewModel()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = RecyclerAdapter(object : RecyclerAdapter.Callback {
            override fun onItemClicked(item: CharacterModel) {
                navigateToCharactersDetailFragment(item)
            }
        })

    }

    @SuppressLint("NotifyDataSetChanged")
    fun initViewModel() {
        viewModel.getLiveDataObserver().observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.setCharacterData(it)
                adapter.notifyDataSetChanged()
            }
        }
        viewModel.loadNextPageOfData(page)
    }

//    @SuppressLint("NotifyDataSetChanged")
//    fun initViewModelWithRoom() {
//        viewModel.getAllCharacterData().observe(viewLifecycleOwner, Observer {
//            if (it != null) {
//                adapter.setCharacterData(it)
//                adapter.notifyDataSetChanged()
//            }
//        })
//        viewModel.loadNextPageOfData(page)
//    }

    fun setUpLayoutManager(spanCount: Int, spanCountLand: Int) {
        linearLayoutManager = GridLayoutManager(this.context, spanCount)
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            linearLayoutManager = GridLayoutManager(this.context, spanCountLand)
        }
    }

    private fun initRecyclerAdapter() {
        setUpLayoutManager(1, 2)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object : PaginationScrollListener(linearLayoutManager) {
            override fun loadMoreItems() {
                progressBar.isVisible = true
                page++
                viewModel.loadNextPageOfData(page)
                progressBar.isVisible = false
            }
        })
    }

    fun initViews() {
        recyclerView = binding.recyclerview
        swipeRefreshLayout = binding.swipeRefresh
        progressBar = binding.progressbar

        swipeRefreshLayout.setOnRefreshListener {
//            adapter.clearCharacterData()
//            page = 1
//            viewModel.loadListOfData()
            swipeRefreshLayout.isRefreshing = false
            //TODO: рефреш
        }

        progressBar.isVisible = false
    }

    fun navigateToCharactersDetailFragment(characterModel: CharacterModel) {
        val args = Bundle()
        args.putString(CHARACTER_NAME, characterModel.name)
        args.putString(CHARACTER_STATUS, characterModel.status)
        args.putString(CHARACTER_SPECIES, characterModel.species)
        args.putString(CHARACTER_TYPE, characterModel.type)
        args.putString(CHARACTER_GENDER, characterModel.gender)
        args.putString(CHARACTER_ORIGIN, characterModel.origin.name)
        args.putString(CHARACTER_LOCATION, characterModel.location.name)
        args.putString(CHARACTER_IMAGEURL, characterModel.image)
        args.putString(CHARACTER_EPISODE, characterModel.episode.toString())
        findNavController().navigate(R.id.itemInfoFragment, args)

            //todo
    }

}