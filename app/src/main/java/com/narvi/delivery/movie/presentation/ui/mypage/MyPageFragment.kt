package com.narvi.delivery.movie.presentation.ui.mypage

import com.narvi.delivery.movie.R
import com.narvi.delivery.movie.databinding.FragmentMyPageBinding
import com.narvi.delivery.movie.presentation.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class MyPageFragment : BaseFragment<FragmentMyPageBinding, MyPageViewModel>(
    R.layout.fragment_my_page
){
    override val viewModel: MyPageViewModel by viewModel()

    override fun onCreate() {
    }

    override fun observeData() {
    }
}