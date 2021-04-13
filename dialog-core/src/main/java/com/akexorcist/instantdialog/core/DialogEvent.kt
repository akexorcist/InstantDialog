package com.akexorcist.instantdialog.core

import android.os.Bundle
import androidx.fragment.app.DialogFragment

class DialogEvent(
    var dialog: DialogFragment,
    var tag: String?,
    var bundle: Bundle?
) {
    fun dismiss() {
        dialog.dismiss()
    }
}