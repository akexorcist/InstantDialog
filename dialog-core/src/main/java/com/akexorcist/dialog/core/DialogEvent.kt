package com.akexorcist.dialog.core

import android.os.Bundle
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class DialogEvent(
    var tag: String?,
    var bundle: Bundle?
) : Parcelable