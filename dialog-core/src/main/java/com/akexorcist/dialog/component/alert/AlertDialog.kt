package com.akexorcist.dialog.component.alert

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.akexorcist.dialog.core.CoreDialogFragment
import com.akexorcist.dialog.core.R
import com.akexorcist.dialog.option.*

class AlertDialog : CoreDialogFragment<AlertViewModel>(),
    LayoutOption,
    TitleOption,
    MessageOption,
    NeutralButtonOption,
    IconOption,
    BundleOption {
    override lateinit var titleTextView: TextView
    override lateinit var messageTextView: TextView
    override lateinit var neutralButton: Button
    override lateinit var iconImageView: ImageView

    override var layoutResourceId: Int = 0
    override var title: String? = null
    override var titleResourceId: Int = 0
    override var message: String? = null
    override var messageResourceId: Int = 0
    override var neutralMessage: String? = null
    override var neutralMessageResourceId: Int = 0
    override var iconResourceId: Int = 0
    override var bundle: Bundle? = null

    override fun bindViewModel() = AlertViewModel::class.java

    override fun bindView(parent: View) {
        super<LayoutOption>.bindView(parent)
        super<TitleOption>.bindView(parent)
        super<MessageOption>.bindView(parent)
        super<NeutralButtonOption>.bindView(parent)
        super<IconOption>.bindView(parent)
        super<BundleOption>.bindView(parent)
        neutralButton.setOnClickListener {
            viewModel.onNeutralButtonClick(tag, bundle)
            dismiss()
        }
    }

    override fun saveState(outState: Bundle) {
        super<LayoutOption>.saveState(outState)
        super<TitleOption>.saveState(outState)
        super<MessageOption>.saveState(outState)
        super<NeutralButtonOption>.saveState(outState)
        super<IconOption>.saveState(outState)
        super<BundleOption>.saveState(outState)
    }

    override fun restoreState(savedInstanceState: Bundle) {
        super<LayoutOption>.restoreState(savedInstanceState)
        super<TitleOption>.restoreState(savedInstanceState)
        super<MessageOption>.restoreState(savedInstanceState)
        super<NeutralButtonOption>.restoreState(savedInstanceState)
        super<IconOption>.restoreState(savedInstanceState)
        super<BundleOption>.restoreState(savedInstanceState)
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        viewModel.onDialogDismiss(tag, bundle)
    }

    companion object {
        private fun newInstance(
            title: String? = null,
            titleResourceId: Int = 0,
            message: String? = null,
            messageResourceId: Int = 0,
            iconResourceId: Int = 0,
            neutralMessage: String? = null,
            neutralMessageResourceId: Int = android.R.string.ok,
            layoutResourceId: Int = R.layout.library_layout_dialog_alert,
            bundle: Bundle? = null
        ) = AlertDialog().apply {
            arguments = Bundle().apply {
                putString(TitleOption.EXTRA_TITLE, title)
                putInt(TitleOption.EXTRA_TITLE_RESOURCE_ID, titleResourceId)
                putString(MessageOption.EXTRA_MESSAGE, message)
                putInt(MessageOption.EXTRA_MESSAGE_RESOURCE_ID, messageResourceId)
                putInt(IconOption.EXTRA_ICON_RESOURCE_ID, iconResourceId)
                putString(NeutralButtonOption.EXTRA_NEUTRAL_MESSAGE, neutralMessage)
                putInt(NeutralButtonOption.EXTRA_NEUTRAL_MESSAGE_RESOURCE_ID, neutralMessageResourceId)
                putInt(LayoutOption.EXTRA_LAYOUT_RESOURCE_ID, layoutResourceId)
                putBundle(BundleOption.EXTRA_BUNDLE, bundle)
            }
        }
    }

    @Suppress("MemberVisibilityCanBePrivate")
    class Builder {
        var title: String? = null
        var titleResourceId: Int = 0
        var message: String? = null
        var messageResourceId: Int = 0
        var iconResourceId: Int = 0
        var neutralMessage: String? = null
        var neutralMessageResourceId: Int = android.R.string.ok
        var layoutResourceId: Int = R.layout.library_layout_dialog_alert
        var bundle: Bundle? = null
        fun build() = newInstance(
            title,
            titleResourceId,
            message,
            messageResourceId,
            iconResourceId,
            neutralMessage,
            neutralMessageResourceId,
            layoutResourceId,
            bundle
        )
    }
}