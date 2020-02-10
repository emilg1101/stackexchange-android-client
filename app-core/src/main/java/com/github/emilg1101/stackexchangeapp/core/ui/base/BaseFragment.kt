package com.github.emilg1101.stackexchangeapp.core.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.view.doOnLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<Binding : ViewDataBinding>(private val containerId: Int) : Fragment(containerId) {

    protected abstract val viewModel: BaseViewModel

    protected lateinit var binding: Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, containerId, container, false)
        context ?: return binding.root

        binding.lifecycleOwner = this

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.doOnLayout { onLayout(it, savedInstanceState) }
        viewModel.snackbar.observe(viewLifecycleOwner) { text ->
            text?.let {
                Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    fun setToolbar(toolbar: Toolbar) {
        (activity as? AppActivity)?.setToolbar(toolbar)
    }

    open fun onLayout(view: View, savedInstanceState: Bundle?) {}
}
