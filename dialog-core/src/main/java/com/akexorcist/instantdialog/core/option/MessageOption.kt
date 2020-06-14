package com.akexorcist.instantdialog.core.option

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.akexorcist.instantdialog.core.DialogComponent
import com.akexorcist.instantdialog.core.R

interface MessageOption : DialogComponent {
    var messageTextView: TextView
    var message: String?
    var messageResourceId: Int

    fun getMessageTextViewResourceId(): Int = R.id.messageTextView

    override fun bindView(parent: View) {
        messageTextView = parent.findViewById(getMessageTextViewResourceId())
        when {
            messageResourceId != 0 -> {
                messageTextView.text = messageTextView.context.getString(messageResourceId)
                messageTextView.visibility = View.VISIBLE
            }
            message != null -> {
                messageTextView.text = message
                messageTextView.visibility = View.VISIBLE
            }
            else -> {
                messageTextView.visibility = View.GONE
            }
        }
    }

    override fun saveState(outState: Bundle) {
        outState.putString(EXTRA_MESSAGE, message)
        outState.putInt(EXTRA_MESSAGE_RESOURCE_ID, messageResourceId)
    }

    override fun restoreState(savedInstanceState: Bundle) {
        message = savedInstanceState.getString(EXTRA_MESSAGE)
        messageResourceId = savedInstanceState.getInt(EXTRA_MESSAGE_RESOURCE_ID)
    }

    companion object {
        const val EXTRA_MESSAGE = "com.akexorcist.dialog.core.message.extra_message"
        const val EXTRA_MESSAGE_RESOURCE_ID = "com.akexorcist.dialog.core.message.extra_message_resource_id"
    }
}
