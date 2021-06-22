package com.narvi.delivery.movie.presentation.ui.mypage


import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.narvi.delivery.movie.R
import com.narvi.delivery.movie.databinding.FragmentMyPageBinding
import com.narvi.delivery.movie.presentation.base.BaseFragment
import com.narvi.delivery.movie.presentation.extension.dip
import com.narvi.delivery.movie.presentation.extension.toGone
import com.narvi.delivery.movie.presentation.extension.toVisible
import com.narvi.delivery.movie.presentation.ui.home.GridSpacingItemDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel


class MyPageFragment : BaseFragment<FragmentMyPageBinding, MyPageViewModel>(
    R.layout.fragment_my_page
){
    override val viewModel: MyPageViewModel by viewModel()
    
    private lateinit var myPageAdapter: MyPageAdapter

    override fun onCreate() {
        initViews()
        bindView()
        viewModel.fetchReviewedMovies()
    }

    private fun initViews() {
        myPageAdapter = MyPageAdapter()
        binding.recyclerView.apply {
            adapter = myPageAdapter
            layoutManager = GridLayoutManager(context, 3, RecyclerView.VERTICAL, false)
            addItemDecoration(GridSpacingItemDecoration(3, dip(6f)))
        }
    }

    private fun bindView() {
        myPageAdapter.apply {
            onMovieClickListener = { movie->
                val action = MyPageFragmentDirections.actionMyPageFragmentToMovieReviewsFragment(movie)
                findNavController().navigate(action)
            }
        }
    }

    override fun observeData() = with(viewModel) {
        myPageState.observe(viewLifecycleOwner, Observer {
            when(it) {
                is MyPageState.Loading -> {
                    handleLoading()
                }
                is MyPageState.Success -> {
                    handleSuccess(it)
                }
                is MyPageState.Error -> {
                    handleError(it)
                }
            }
        })
    }

    private fun handleSuccess(state: MyPageState.Success) {
        binding.progressBar.toGone()
        when(state) {
            is MyPageState.Success.NoDataDescription -> {
                showNoDataDescription(state)
            }
            is MyPageState.Success.ShowReviewedMovies -> {
                showReviewRecyclerView(state)
            }
        }
    }

    private fun showReviewRecyclerView(state: MyPageState.Success.ShowReviewedMovies) {
        binding.recyclerView.toVisible()
        binding.descriptionTextView.toGone()
        myPageAdapter.apply {
            this.reviewedMovies = state.reviewedMovies
            notifyDataSetChanged()
        }
    }

    private fun showNoDataDescription(state: MyPageState.Success.NoDataDescription) {
        binding.recyclerView.toGone()
        binding.descriptionTextView.toVisible()
        binding.descriptionTextView.text = state.message
    }

    private fun handleLoading() {
        binding.progressBar.toGone()
    }

    private fun handleError(state: MyPageState.Error) {
        binding.recyclerView.toGone()
        binding.descriptionTextView.toVisible()
        binding.descriptionTextView.text = state.message
    }
}