package com.narvi.delivery.movie.presentation.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.narvi.delivery.movie.R
import com.narvi.delivery.movie.data.model.FeaturedMovie
import com.narvi.delivery.movie.data.model.Movie
import com.narvi.delivery.movie.databinding.FragmentHomeBinding
import com.narvi.delivery.movie.presentation.base.BaseFragment
import com.narvi.delivery.movie.presentation.extension.dip
import com.narvi.delivery.movie.presentation.extension.toGone
import com.narvi.delivery.movie.presentation.extension.toVisible
import com.narvi.delivery.movie.presentation.uitll.Constant.ITEM_VIEW_TYPE_FEATURED
import com.narvi.delivery.movie.presentation.uitll.Constant.ITEM_VIEW_TYPE_SECTION_HEADER
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    R.layout.fragment_home
) {

    override val viewModel: HomeViewModel by viewModel()

    private lateinit var homeAdapter: HomeAdapter

    override fun onCreate() {
        homeAdapter = HomeAdapter()
        initViews()
        viewModel.fetchMovie()
        bindView()
    }

    override fun observeData() = with(viewModel) {
        homeState.observe(viewLifecycleOwner, Observer {
            when(it) {
                is HomeState.Loading -> {
                    handleLoading()
                }
                is HomeState.Success -> {
                    handleSuccess(it.featuredMovie, it.movieList)
                }
                is HomeState.Error -> {
                    handleError(it.message)
                }
            }
        })
    }

    private fun initViews() {
        binding.recyclerView.apply {
            adapter = homeAdapter
            val gridLayoutManager = createGridLayoutManager()
            layoutManager = gridLayoutManager
            addItemDecoration(GridSpacingItemDecoration(gridLayoutManager.spanCount, dip(6f)))
        }
    }

    private fun bindView() {
        homeAdapter.apply {
            onMovieClickListener = { movie ->
                val action = HomeFragmentDirections.actionHomeFragmentToMovieReviewsFragment(movie)
                findNavController().navigate(action)
            }
        }
    }

    private fun handleLoading() {
        binding.progressBar.toVisible()
    }

    private fun handleSuccess(featuredMovie: FeaturedMovie?, movie: List<Movie>) {
        binding.progressBar.toGone()
        binding.recyclerView.toVisible()
        binding.errorDescriptionTextView.toGone()
        homeAdapter.apply {
            addData(featuredMovie, movie)
            notifyDataSetChanged()
        }
    }

    private fun handleError(message: String?) {
        binding.recyclerView.toGone()
        binding.errorDescriptionTextView.toVisible()
        binding.errorDescriptionTextView.text = message
    }

    private fun RecyclerView.createGridLayoutManager(): GridLayoutManager =
        GridLayoutManager(context, 3, RecyclerView.VERTICAL, false).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int =
                    when (adapter?.getItemViewType(position)) {
                        ITEM_VIEW_TYPE_SECTION_HEADER,
                        ITEM_VIEW_TYPE_FEATURED -> {
                            spanCount
                        }
                        else -> {
                            1
                        }
                    }
            }
        }
}