package com.akexorcist.instantdialog.core

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.DialogFragment
import kotlinx.android.parcel.Parcelize

class DialogEvent(
    var tag: String?,
    var bundle: Bundle?,
    var dialog: DialogFragment
) {
    fun dismiss() {
        dialog.dismiss()
    }
}