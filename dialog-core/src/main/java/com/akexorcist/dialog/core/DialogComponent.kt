package com.akexorcist.dialog.core

import android.os.Bundle
import android.view.View

interface DialogComponent {
    fun bindView(parent: View)
    fun saveState(outState: Bundle)
    fun restoreState(savedInstanceState: Bundle)
}
