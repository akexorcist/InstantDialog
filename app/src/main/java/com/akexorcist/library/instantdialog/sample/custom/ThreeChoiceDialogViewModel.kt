package com.akexorcist.library.instantdialog.sample.custom

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import com.akexorcist.instantdialog.core.DialogEvent
import com.akexorcist.instantdialog.core.SingleLiveEvent

class ThreeChoiceDialogViewModel : ViewModel() {
    val positiveButtonClicked = SingleLiveEvent<DialogEvent>()
    val negativeButtonClicked = SingleLiveEvent<DialogEvent>()
    val neutralButtonClicked = SingleLiveEvent<DialogEvent>()
    val dialogDismiss = SingleLiveEvent<DialogEvent>()

    fun onPositiveButtonClicked(dialog: DialogFragment, tag: String?, bundle: Bundle?) {
        positiveButtonClicked(DialogEvent(dialog, tag, bundle))
    }

    fun onNegativeButtonClicked(dialog: DialogFragment, tag: String?, bundle: Bundle?) {
        negativeButtonClicked(DialogEvent(dialog, tag, bundle))
    }

    fun onNeutralButtonClicked(dialog: DialogFragment, tag: String?, bundle: Bundle?) {
        neutralButtonClicked(DialogEvent(dialog, tag, bundle))
    }

    fun onDialogDismiss(dialog: DialogFragment, tag: String?, bundle: Bundle?) {
        dialogDismiss(DialogEvent(dialog, tag, bundle))
    }
}
