package com.akexorcist.dialog.option

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.akexorcist.dialog.core.DialogComponent
import com.akexorcist.dialog.core.R

interface IconOption : DialogComponent {
    var iconImageView: ImageView
    var iconResourceId: Int

    fun getIconImageViewResourceId(): Int = R.id.iconImageView

    override fun bindView(parent: View) {
        iconImageView = parent.findViewById(getIconImageViewResourceId())
        when {
            iconResourceId != 0 -> {
                iconImageView.setImageDrawable(iconImageView.context.getDrawable(iconResourceId))
                iconImageView.visibility = View.VISIBLE
            }
            else -> {
                iconImageView.visibility = View.GONE
            }
        }
    }

    override fun saveState(outState: Bundle) {
        outState.putInt(EXTRA_ICON_RESOURCE_ID, iconResourceId)
    }

    override fun restoreState(savedInstanceState: Bundle) {
        iconResourceId = savedInstanceState.getInt(EXTRA_ICON_RESOURCE_ID)
    }

    companion object {
        const val EXTRA_ICON_RESOURCE_ID = "com.akexorcist.dialog.core.message.extra_icon_resource_id"
    }
}
