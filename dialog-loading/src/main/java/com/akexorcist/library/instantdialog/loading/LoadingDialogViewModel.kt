package com.akexorcist.library.instantdialog.loading

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import com.akexorcist.instantdialog.core.DialogEvent
import com.akexorcist.instantdialog.core.SingleLiveEvent

class LoadingDialogViewModel : ViewModel() {
    val dialogDismiss = SingleLiveEvent<DialogEvent>()

    fun onDialogDismiss(dialog: DialogFragment, tag: String?, bundle: Bundle?) {
        dialogDismiss(DialogEvent(dialog, tag, bundle))
    }
}