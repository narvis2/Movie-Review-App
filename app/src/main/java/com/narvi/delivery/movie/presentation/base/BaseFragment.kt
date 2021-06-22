package com.narvi.delivery.movie.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.narvi.delivery.movie.BR

abstract class BaseFragment<B: ViewDataBinding, VM: BaseViewModel>(
    @LayoutRes private val layoutResId: Int
) : Fragment() {

    protected lateinit var binding: B
    abstract val viewModel : VM

    abstract fun onCreate()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setVariable(BR.viewModel, viewModel)
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        onCreate()
        observeData()
    }

    abstract fun observeData()

}