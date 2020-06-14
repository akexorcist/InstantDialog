package com.akexorcist.library.instantdialog.loading

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.akexorcist.instantdialog.core.CoreDialogFragment
import com.akexorcist.instantdialog.core.option.*

class LoadingDialog : CoreDialogFragment<LoadingDialogViewModel>(),
    LayoutOption,
    TitleOption,
    MessageOption,
    IndeterminateProgressOption,
    BundleOption {
    override lateinit var titleTextView: TextView
    override lateinit var messageTextView: TextView
    override lateinit var indeterminateProgressBar: ProgressBar

    override var layoutResourceId: Int = 0
    override var title: String? = null
    override var titleResourceId: Int = 0
    override var message: String? = null
    override var messageResourceId: Int = 0
    override var bundle: Bundle? = null

    override fun bindViewModel() = LoadingDialogViewModel::class.java

    override fun bindView(parent: View) {
        super<LayoutOption>.bindView(parent)
        super<TitleOption>.bindView(parent)
        super<MessageOption>.bindView(parent)
        super<IndeterminateProgressOption>.bindView(parent)
        super<BundleOption>.bindView(parent)
    }

    override fun saveState(outState: Bundle) {
        super<LayoutOption>.saveState(outState)
        super<TitleOption>.saveState(outState)
        super<MessageOption>.saveState(outState)
        super<IndeterminateProgressOption>.saveState(outState)
        super<BundleOption>.saveState(outState)
    }

    override fun restoreState(savedInstanceState: Bundle) {
        super<LayoutOption>.restoreState(savedInstanceState)
        super<TitleOption>.restoreState(savedInstanceState)
        super<MessageOption>.restoreState(savedInstanceState)
        super<IndeterminateProgressOption>.restoreState(savedInstanceState)
        super<BundleOption>.restoreState(savedInstanceState)
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        viewModel.onDialogDismiss(this, tag, bundle)
    }

    @Suppress("MemberVisibilityCanBePrivate")
    class Builder {
        var title: String? = null
        var titleResourceId: Int = 0
        var message: String? = null
        var messageResourceId: Int = 0
        var layoutResourceId: Int = R.layout.library_layout_dialog_loading
        var bundle: Bundle? = null
        fun build() = newInstance(
            title,
            titleResourceId,
            message,
            messageResourceId,
            layoutResourceId,
            bundle
        )
    }

    companion object {
        private fun newInstance(
            title: String? = null,
            titleResourceId: Int = 0,
            message: String? = null,
            messageResourceId: Int = 0,
            layoutResourceId: Int = R.layout.library_layout_dialog_loading,
            bundle: Bundle? = null
        ) = LoadingDialog().apply {
            arguments = Bundle().apply {
                putString(TitleOption.EXTRA_TITLE, title)
                putInt(TitleOption.EXTRA_TITLE_RESOURCE_ID, titleResourceId)
                putString(MessageOption.EXTRA_MESSAGE, message)
                putInt(MessageOption.EXTRA_MESSAGE_RESOURCE_ID, messageResourceId)
                putInt(LayoutOption.EXTRA_LAYOUT_RESOURCE_ID, layoutResourceId)
                putBundle(BundleOption.EXTRA_BUNDLE, bundle)
            }
        }
    }
}