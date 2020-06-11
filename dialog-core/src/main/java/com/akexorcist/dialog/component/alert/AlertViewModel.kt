package com.akexorcist.dialog.component.alert

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.akexorcist.dialog.core.DialogEvent
import com.akexorcist.dialog.core.SingleLiveEvent

class AlertViewModel : ViewModel() {
    val neutralButtonClicked = SingleLiveEvent<DialogEvent>()
    val dialogDismiss = SingleLiveEvent<DialogEvent>()

    fun onNeutralButtonClick(tag: String?, bundle: Bundle?) {
        neutralButtonClicked(DialogEvent(tag, bundle))
    }

    fun onDialogDismiss(tag: String?, bundle: Bundle?) {
        dialogDismiss(DialogEvent(tag, bundle))
    }
}