package com.akexorcist.dialog.option

import android.os.Bundle
import android.view.View
import com.akexorcist.dialog.core.DialogComponent

interface BundleOption : DialogComponent {
    var bundle: Bundle?

    override fun bindView(parent: View) {}

    override fun saveState(outState: Bundle) {
        outState.putBundle(EXTRA_BUNDLE, bundle)
    }

    override fun restoreState(savedInstanceState: Bundle) {
        bundle = savedInstanceState.getBundle(EXTRA_BUNDLE)
    }

    companion object {
        const val EXTRA_BUNDLE = "com.akexorcist.dialog.core.bundle.extra_bundle"
    }
}
