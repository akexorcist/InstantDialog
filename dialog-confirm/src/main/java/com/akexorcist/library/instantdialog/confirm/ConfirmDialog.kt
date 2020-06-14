package com.akexorcist.library.instantdialog.confirm

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.akexorcist.instantdialog.core.CoreDialogFragment
import com.akexorcist.instantdialog.core.option.*

class ConfirmDialog : CoreDialogFragment<ConfirmDialogViewModel>(),
    LayoutOption,
    TitleOption,
    MessageOption,
    PositiveNegativeButtonOption,
    IconOption,
    BundleOption {
    override lateinit var titleTextView: TextView
    override lateinit var messageTextView: TextView
    override lateinit var positiveButton: Button
    override lateinit var negativeButton: Button
    override lateinit var iconImageView: ImageView

    override var layoutResourceId: Int = 0
    override var title: String? = null
    override var titleResourceId: Int = 0
    override var message: String? = null
    override var messageResourceId: Int = 0
    override var positiveMessage: String? = null
    override var positiveMessageResourceId: Int = 0
    override var negativeMessage: String? = null
    override var negativeMessageResourceId: Int = 0
    override var iconResourceId: Int = 0
    override var bundle: Bundle? = null

    override fun bindViewModel() = ConfirmDialogViewModel::class.java

    override fun bindView(parent: View) {
        super<LayoutOption>.bindView(parent)
        super<TitleOption>.bindView(parent)
        super<MessageOption>.bindView(parent)
        super<PositiveNegativeButtonOption>.bindView(parent)
        super<IconOption>.bindView(parent)
        super<BundleOption>.bindView(parent)
        positiveButton.setOnClickListener {
            viewModel.onPositiveButtonClick(this, tag, bundle)
        }
        negativeButton.setOnClickListener {
            viewModel.onNegativeButtonClick(this, tag, bundle)
        }
    }

    override fun saveState(outState: Bundle) {
        super<LayoutOption>.saveState(outState)
        super<TitleOption>.saveState(outState)
        super<MessageOption>.saveState(outState)
        super<PositiveNegativeButtonOption>.saveState(outState)
        super<IconOption>.saveState(outState)
        super<BundleOption>.saveState(outState)
    }

    override fun restoreState(savedInstanceState: Bundle) {
        super<LayoutOption>.restoreState(savedInstanceState)
        super<TitleOption>.restoreState(savedInstanceState)
        super<MessageOption>.restoreState(savedInstanceState)
        super<PositiveNegativeButtonOption>.restoreState(savedInstanceState)
        super<IconOption>.restoreState(savedInstanceState)
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
        var positiveMessage: String? = null
        var positiveMessageResourceId: Int = android.R.string.ok
        var negativeMessage: String? = null
        var negativeMessageResourceId: Int = android.R.string.cancel
        var iconResourceId: Int = 0
        var layoutResourceId: Int = R.layout.library_layout_dialog_confirm
        var bundle: Bundle? = null
        fun build() = newInstance(
            title,
            titleResourceId,
            message,
            messageResourceId,
            positiveMessage,
            positiveMessageResourceId,
            negativeMessage,
            negativeMessageResourceId,
            iconResourceId,
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
            positiveMessage: String? = null,
            positiveMessageResourceId: Int = android.R.string.ok,
            negativeMessage: String? = null,
            negativeMessageResourceId: Int = android.R.string.cancel,
            iconResourceId: Int = 0,
            layoutResourceId: Int = R.layout.library_layout_dialog_confirm,
            bundle: Bundle? = null
        ) = ConfirmDialog().apply {
            arguments = Bundle().apply {
                putString(TitleOption.EXTRA_TITLE, title)
                putInt(TitleOption.EXTRA_TITLE_RESOURCE_ID, titleResourceId)
                putString(MessageOption.EXTRA_MESSAGE, message)
                putInt(MessageOption.EXTRA_MESSAGE_RESOURCE_ID, messageResourceId)
                putString(PositiveNegativeButtonOption.EXTRA_POSITIVE_MESSAGE, positiveMessage)
                putInt(PositiveNegativeButtonOption.EXTRA_POSITIVE_MESSAGE_RESOURCE_ID, positiveMessageResourceId)
                putString(PositiveNegativeButtonOption.EXTRA_NEGATIVE_MESSAGE, negativeMessage)
                putInt(PositiveNegativeButtonOption.EXTRA_NEGATIVE_MESSAGE_RESOURCE_ID, negativeMessageResourceId)
                putInt(IconOption.EXTRA_ICON_RESOURCE_ID, iconResourceId)
                putInt(LayoutOption.EXTRA_LAYOUT_RESOURCE_ID, layoutResourceId)
                putBundle(BundleOption.EXTRA_BUNDLE, bundle)
            }
        }
    }
}