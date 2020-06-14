package com.akexorcist.library.instantdialog.confirm

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import com.akexorcist.instantdialog.core.DialogEvent
import com.akexorcist.instantdialog.core.SingleLiveEvent

class ConfirmDialogViewModel : ViewModel() {
    val positiveButtonClicked = SingleLiveEvent<DialogEvent>()
    val negativeButtonClicked = SingleLiveEvent<DialogEvent>()
    val dialogDismiss = SingleLiveEvent<DialogEvent>()

    fun onPositiveButtonClick(tag: String?, bundle: Bundle?, dialog: DialogFragment) {
        positiveButtonClicked(DialogEvent(tag, bundle, dialog))
    }

    fun onNegativeButtonClick(tag: String?, bundle: Bundle?, dialog: DialogFragment) {
        negativeButtonClicked(DialogEvent(tag, bundle, dialog))
    }

    fun onDialogDismiss(tag: String?, bundle: Bundle?, dialog: DialogFragment) {
        dialogDismiss(DialogEvent(tag, bundle, dialog))
    }
}