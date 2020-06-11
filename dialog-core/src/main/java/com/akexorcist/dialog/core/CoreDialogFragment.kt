package com.akexorcist.dialog.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class CoreDialogFragment<T : ViewModel> : DialogFragment(), DialogComponent {
    protected val viewModel: T by lazy { ViewModelProvider(requireActivity()).get(bindViewModel()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            restoreState(arguments as Bundle)
        } else if (savedInstanceState != null) {
            restoreState(savedInstanceState)
        }
    }

    abstract fun bindViewModel(): Class<T>

    abstract var layoutResourceId: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutResourceId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindView(view)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        saveState(outState)
    }
}