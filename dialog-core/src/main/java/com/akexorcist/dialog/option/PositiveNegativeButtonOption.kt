package com.akexorcist.dialog.option

import android.os.Bundle
import android.view.View
import android.widget.Button
import com.akexorcist.dialog.core.DialogComponent
import com.akexorcist.dialog.core.R

interface PositiveNegativeButtonOption : DialogComponent {
    var positiveButton: Button
    var negativeButton: Button

    var positiveMessage: String?
    var positiveMessageResourceId: Int
    var negativeMessage: String?
    var negativeMessageResourceId: Int

    fun getPositiveButtonResourceId(): Int = R.id.positiveButton
    fun getNegativeButtonResourceId(): Int = R.id.negativeButton

    override fun bindView(parent: View) {
        positiveButton = parent.findViewById(getPositiveButtonResourceId())
        negativeButton = parent.findViewById(getNegativeButtonResourceId())
        if (positiveMessageResourceId != 0) {
            positiveButton.text = positiveButton.context.getString(positiveMessageResourceId)
        } else if (positiveMessage != null) {
            positiveButton.text = positiveMessage
        }
        if (negativeMessageResourceId != 0) {
            negativeButton.text = negativeButton.context.getString(negativeMessageResourceId)
        } else if (negativeMessage != null) {
            negativeButton.text = negativeMessage
        }
    }

    override fun saveState(outState: Bundle) {
        outState.putString(EXTRA_POSITIVE_MESSAGE, positiveMessage)
        outState.putString(EXTRA_NEGATIVE_MESSAGE, negativeMessage)
        outState.putInt(EXTRA_POSITIVE_MESSAGE_RESOURCE_ID, positiveMessageResourceId)
        outState.putInt(EXTRA_NEGATIVE_MESSAGE_RESOURCE_ID, negativeMessageResourceId)
    }

    override fun restoreState(savedInstanceState: Bundle) {
        positiveMessage = savedInstanceState.getString(EXTRA_POSITIVE_MESSAGE)
        negativeMessage = savedInstanceState.getString(EXTRA_NEGATIVE_MESSAGE)
        positiveMessageResourceId = savedInstanceState.getInt(EXTRA_POSITIVE_MESSAGE_RESOURCE_ID)
        negativeMessageResourceId = savedInstanceState.getInt(EXTRA_NEGATIVE_MESSAGE_RESOURCE_ID)
    }

    companion object {
        const val EXTRA_POSITIVE_MESSAGE = "com.akexorcist.dialog.core.positivenegativebutton.key_positive_message"
        const val EXTRA_NEGATIVE_MESSAGE = "com.akexorcist.dialog.core.positivenegativebutton.key_negative_message"
        const val EXTRA_POSITIVE_MESSAGE_RESOURCE_ID = "com.akexorcist.dialog.core.positivenegativebutton.key_positive_message_resource_id"
        const val EXTRA_NEGATIVE_MESSAGE_RESOURCE_ID = "com.akexorcist.dialog.core.positivenegativebutton.key_negative_message_resource_id"
    }
}