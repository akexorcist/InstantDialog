package com.akexorcist.library.instantdialog.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.akexorcist.library.instantdialog.alertdialog.alert.AlertDialog
import com.akexorcist.library.instantdialog.alertdialog.alert.AlertDialogViewModel
import com.akexorcist.library.instantdialog.confirm.ConfirmDialog
import com.akexorcist.library.instantdialog.confirm.ConfirmDialogViewModel
import com.akexorcist.library.instantdialog.loading.LoadingDialog
import com.akexorcist.library.instantdialog.loading.LoadingDialogViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val confirmDialogViewModel: ConfirmDialogViewModel by lazy {
        ViewModelProvider(this).get(ConfirmDialogViewModel::class.java)
    }
    private val alertDialogViewModel: AlertDialogViewModel by lazy {
        ViewModelProvider(this).get(AlertDialogViewModel::class.java)
    }
    private val loadingDialogViewModel: LoadingDialogViewModel by lazy {
        ViewModelProvider(this).get(LoadingDialogViewModel::class.java)
    }

    companion object {
        private const val TAG_DEFAULT_LOADING_DIALOG = "tag_default_loading_dialog"
        private const val TAG_DEFAULT_ALERT_DIALOG = "tag_default_alert_dialog"
        private const val TAG_DEFAULT_CONFIRM_DIALOG = "tag_default_confirm_dialog"
        private const val TAG_CUSTOM_ALERT_DIALOG = "tag_custom_alert_dialog"
        private const val TAG_CUSTOM_CONFIRM_DIALOG = "tag_custom_confirm_dialog"
        private const val TAG_CUSTOM_LOADING_DIALOG = "tag_custom_loading_dialog"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindDialogEvent()
        bindView()
    }

    private fun bindView() {
        showDefaultAlertDialogButton.setOnClickListener { showDefaultAlertDialog() }
        showDefaultConfirmDialogButton.setOnClickListener { showDefaultConfirmDialog() }
        showDefaultLoadingDialogButton.setOnClickListener { showDefaultLoadingDialog() }
        showCustomAlertDialogButton.setOnClickListener { showCustomAlertDialog() }
        showCustomConfirmDialogButton.setOnClickListener { showCustomConfirmDialog() }
        showCustomLoadingDialogButton.setOnClickListener { showCustomLoadingDialog() }
    }

    private fun bindDialogEvent() {
        alertDialogViewModel.neutralButtonClicked.observe(this, Observer {
            it.dismiss()
            if (it.tag == TAG_DEFAULT_ALERT_DIALOG) {
                showMessage("Greeting message accepted")
            } else if (it.tag == TAG_CUSTOM_ALERT_DIALOG) {
                showMessage("Please don't do that again, OK?")
            }
        })

        alertDialogViewModel.dialogDismiss.observe(this, Observer {
            showMessage("Dialog dismissed")
        })

        confirmDialogViewModel.positiveButtonClicked.observe(this, Observer {
            it.dismiss()
            if (it.tag == TAG_DEFAULT_CONFIRM_DIALOG) {
                showMessage("Delete the data confirmed")
            } else if (it.tag == TAG_CUSTOM_CONFIRM_DIALOG) {
                showMessage("Update new version confirmed")
            }
        })

        confirmDialogViewModel.negativeButtonClicked.observe(this, Observer {
            it.dismiss()
            if (it.tag == TAG_DEFAULT_CONFIRM_DIALOG) {
                showMessage("Delete the data cancelled")
            } else if (it.tag == TAG_CUSTOM_CONFIRM_DIALOG) {
                showMessage("Update new version cancelled")
            }
        })

        loadingDialogViewModel.dialogDismiss.observe(this, Observer {
            if (it.tag == TAG_DEFAULT_LOADING_DIALOG) {
                showMessage("Default loading dismissed")
            } else if (it.tag == TAG_CUSTOM_LOADING_DIALOG) {
                showMessage("Custom loading dismissed")
            }
        })
    }

    private val showDefaultAlertDialog = {
        AlertDialog.Builder().apply {
            title = "Welcome, User"
            message = "We will bring you to the new journey"
            iconResourceId = R.drawable.ic_check
        }.build().show(supportFragmentManager, TAG_DEFAULT_ALERT_DIALOG)
    }

    private val showDefaultConfirmDialog = {
        ConfirmDialog.Builder().apply {
            title = "Are you sure?"
            message = "This data will be delete permanently "
        }.build().apply {
            isCancelable = false
        }.show(supportFragmentManager, TAG_DEFAULT_CONFIRM_DIALOG)
    }

    private val showDefaultLoadingDialog = {
        LoadingDialog.Builder().apply {
            title = "Loading..."
            message = "Touch outside dialog to dismiss"
        }.build().show(supportFragmentManager, TAG_DEFAULT_LOADING_DIALOG)
    }

    private val showCustomAlertDialog = {
        AlertDialog.Builder().apply {
            title = "Hey! We don't like it"
            message = "Don't do like that"
            iconResourceId = R.drawable.ic_warning
            layoutResourceId = R.layout.layout_custom_alert_dialog
        }.build().show(supportFragmentManager, TAG_CUSTOM_ALERT_DIALOG)
    }

    private val showCustomConfirmDialog = {
        ConfirmDialog.Builder().apply {
            title = "New version available "
            message = "Do you want to update your app now?"
            positiveMessageResourceId = R.string.confirm_update
            negativeMessageResourceId = R.string.cancel_update
            iconResourceId = R.drawable.ic_update
            layoutResourceId = R.layout.layout_custom_confirm_dialog
        }.build().apply {
            isCancelable = false
        }.show(supportFragmentManager, TAG_CUSTOM_CONFIRM_DIALOG)
    }

    private val showCustomLoadingDialog = {
        LoadingDialog.Builder().apply {
            title = "Loading..."
            message = "Touch outside dialog to dismiss"
            layoutResourceId = R.layout.layout_custom_loading_dialog
        }.build().show(supportFragmentManager, TAG_CUSTOM_LOADING_DIALOG)
    }

    private fun showMessage(message: String) {
        Snackbar.make(window.decorView.rootView, message, Snackbar.LENGTH_SHORT).show()
    }
}
