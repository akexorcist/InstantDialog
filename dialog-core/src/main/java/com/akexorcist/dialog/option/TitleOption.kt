package com.akexorcist.dialog.option

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.akexorcist.dialog.core.DialogComponent
import com.akexorcist.dialog.core.R

interface TitleOption : DialogComponent {
    var titleTextView: TextView
    var title: String?
    var titleResourceId: Int

    fun getTitleTextViewResourceId(): Int = R.id.titleTextView

    override fun bindView(parent: View) {
        titleTextView = parent.findViewById(getTitleTextViewResourceId())
        when {
            titleResourceId != 0 -> {
                titleTextView.text = titleTextView.context.getString(titleResourceId)
                titleTextView.visibility = View.VISIBLE
            }
            title != null -> {
                titleTextView.text = title
                titleTextView.visibility = View.VISIBLE
            }
            else -> {
                titleTextView.visibility = View.GONE
            }
        }
    }

    override fun saveState(outState: Bundle) {
        outState.putString(EXTRA_TITLE, title)
        outState.putInt(EXTRA_TITLE_RESOURCE_ID, titleResourceId)
    }

    override fun restoreState(savedInstanceState: Bundle) {
        title = savedInstanceState.getString(EXTRA_TITLE)
        titleResourceId = savedInstanceState.getInt(EXTRA_TITLE_RESOURCE_ID)
    }

    companion object {
        const val EXTRA_TITLE = "com.akexorcist.dialog.core.title.key_title"
        const val EXTRA_TITLE_RESOURCE_ID = "com.akexorcist.dialog.core.title.key_title_resource_id"
    }
}