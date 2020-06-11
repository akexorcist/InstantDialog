package com.akexorcist.dialog.option

import android.os.Bundle
import android.view.View
import android.widget.Button
import com.akexorcist.dialog.core.DialogComponent
import com.akexorcist.dialog.core.R

interface NeutralButtonOption : DialogComponent {
    var neutralButton: Button

    var neutralMessage: String?
    var neutralMessageResourceId: Int

    fun getNeutralButtonResourceId(): Int = R.id.neutralButton

    override fun bindView(parent: View) {
        neutralButton = parent.findViewById(getNeutralButtonResourceId())
        if (neutralMessageResourceId != 0) {
            neutralButton.text = neutralButton.context.getString(neutralMessageResourceId)
        } else if (neutralMessage != null) {
            neutralButton.text = neutralMessage
        }
    }

    override fun saveState(outState: Bundle) {
        outState.putString(EXTRA_NEUTRAL_MESSAGE, neutralMessage)
        outState.putInt(EXTRA_NEUTRAL_MESSAGE_RESOURCE_ID, neutralMessageResourceId)
    }

    override fun restoreState(savedInstanceState: Bundle) {
        neutralMessage = savedInstanceState.getString(EXTRA_NEUTRAL_MESSAGE)
        neutralMessageResourceId = savedInstanceState.getInt(EXTRA_NEUTRAL_MESSAGE_RESOURCE_ID)
    }

    companion object {
        const val EXTRA_NEUTRAL_MESSAGE = "com.akexorcist.dialog.core.neutralbutton.extra_neutral_message"
        const val EXTRA_NEUTRAL_MESSAGE_RESOURCE_ID = "com.akexorcist.dialog.core.neutralbutton.extra_neutral_message_resource_id"
    }
}