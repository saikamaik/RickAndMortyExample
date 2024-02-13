package com.example.rickandmortyexample.presentation.mainpage

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.domain.entity.CharacterModel
import com.example.rickandmortyexample.R
import com.example.rickandmortyexample.adapter.RecyclerAdapter
import com.example.rickandmortyexample.databinding.FragmentMainPageBinding
import com.example.secondgallery.adapter.PaginationScrollListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainPageFragment: Fragment() {

    private var _binding: FragmentMainPageBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: RecyclerAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private var isLoading = false
    private var page: Int = 1
    private var isLastPage = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initViews()
        initRecyclerAdapter()

    }

    @SuppressLint("NotifyDataSetChanged")
    fun initViewModel() {
        val viewModel = ViewModelProvider(this)[MainPageViewModel::class.java]
        viewModel.getLiveDataObserver().observe(viewLifecycleOwner, Observer{
            if (it != null){
                adapter.setCharactersData(it)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(context, "error in getting data", Toast.LENGTH_SHORT).show()
            }
        } )
        viewModel.loadListOfData()
    }

    fun initRecyclerAdapter() {
        adapter = RecyclerAdapter(object : RecyclerAdapter.Callback {
            override fun onItemClicked(item: CharacterModel) {

            }
        })

        val linearLayoutManager = LinearLayoutManager(this.context)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object : PaginationScrollListener(linearLayoutManager) {
            override fun loadMoreItems() {

            }
        })
    }

    fun initViews() {
        recyclerView = requireView().findViewById(R.id.recyclerview)
        swipeRefreshLayout = requireView().findViewById(R.id.swipe_refresh)

        swipeRefreshLayout.setOnRefreshListener {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}