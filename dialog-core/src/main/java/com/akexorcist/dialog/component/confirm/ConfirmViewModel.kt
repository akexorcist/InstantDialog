package com.akexorcist.dialog.component.confirm

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.akexorcist.dialog.core.DialogEvent
import com.akexorcist.dialog.core.SingleLiveEvent

class ConfirmViewModel : ViewModel() {
    val positiveButtonClicked = SingleLiveEvent<DialogEvent>()
    val negativeButtonClicked = SingleLiveEvent<DialogEvent>()
    val dialogDismiss = SingleLiveEvent<DialogEvent>()

    fun onPositiveButtonClick(tag: String?, bundle: Bundle?) {
        positiveButtonClicked(DialogEvent(tag, bundle))
    }

    fun onNegativeButtonClick(tag: String?, bundle: Bundle?) {
        negativeButtonClicked(DialogEvent(tag, bundle))
    }

    fun onDialogDismiss(tag: String?, bundle: Bundle?) {
        dialogDismiss(DialogEvent(tag, bundle))
    }
}