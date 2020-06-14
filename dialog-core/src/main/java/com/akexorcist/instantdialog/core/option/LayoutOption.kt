package com.akexorcist.instantdialog.core.option

import android.os.Bundle
import android.view.View
import com.akexorcist.instantdialog.core.DialogComponent

interface LayoutOption : DialogComponent {
    var layoutResourceId: Int

    override fun bindView(parent: View) {}

    override fun saveState(outState: Bundle) {
        outState.putInt(EXTRA_LAYOUT_RESOURCE_ID, layoutResourceId)
    }

    override fun restoreState(savedInstanceState: Bundle) {
        layoutResourceId = savedInstanceState.getInt(EXTRA_LAYOUT_RESOURCE_ID)
    }

    companion object {
        const val EXTRA_LAYOUT_RESOURCE_ID = "com.akexorcist.dialog.core.layout.extra_layout_resource_id"
    }
}
