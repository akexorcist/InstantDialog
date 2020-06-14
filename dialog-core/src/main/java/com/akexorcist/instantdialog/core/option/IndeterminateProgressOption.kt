package com.akexorcist.instantdialog.core.option

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import com.akexorcist.instantdialog.core.DialogComponent
import com.akexorcist.instantdialog.core.R

interface IndeterminateProgressOption : DialogComponent {
    var indeterminateProgressBar: ProgressBar

    fun getIndeterminateProgressBarResourceId(): Int = R.id.indeterminateProgressBar

    override fun bindView(parent: View) {
        indeterminateProgressBar = parent.findViewById(getIndeterminateProgressBarResourceId())
    }

    override fun saveState(outState: Bundle) {
    }

    override fun restoreState(savedInstanceState: Bundle) {
    }
}