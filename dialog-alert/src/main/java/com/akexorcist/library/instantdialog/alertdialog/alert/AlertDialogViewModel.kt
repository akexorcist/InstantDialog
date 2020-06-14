package com.akexorcist.library.instantdialog.alertdialog.alert

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import com.akexorcist.instantdialog.core.DialogEvent
import com.akexorcist.instantdialog.core.SingleLiveEvent

class AlertDialogViewModel : ViewModel() {
    val neutralButtonClicked = SingleLiveEvent<DialogEvent>()
    val dialogDismiss = SingleLiveEvent<DialogEvent>()

    fun onNeutralButtonClick(dialog: DialogFragment, tag: String?, bundle: Bundle?) {
        neutralButtonClicked(DialogEvent(dialog, tag, bundle))
    }

    fun onDialogDismiss(dialog: DialogFragment, tag: String?, bundle: Bundle?) {
        dialogDismiss(DialogEvent(dialog, tag, bundle))
    }
}