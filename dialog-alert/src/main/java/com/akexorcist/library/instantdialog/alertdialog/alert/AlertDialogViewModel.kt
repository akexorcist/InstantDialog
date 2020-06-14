package com.akexorcist.library.instantdialog.alertdialog.alert

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import com.akexorcist.instantdialog.core.DialogEvent
import com.akexorcist.instantdialog.core.SingleLiveEvent

class AlertDialogViewModel : ViewModel() {
    val neutralButtonClicked = SingleLiveEvent<DialogEvent>()
    val dialogDismiss = SingleLiveEvent<DialogEvent>()

    fun onNeutralButtonClick(tag: String?, bundle: Bundle?, dialog: DialogFragment) {
        neutralButtonClicked(DialogEvent(tag, bundle, dialog))
    }

    fun onDialogDismiss(tag: String?, bundle: Bundle?, dialog: DialogFragment) {
        dialogDismiss(DialogEvent(tag, bundle, dialog))
    }
}