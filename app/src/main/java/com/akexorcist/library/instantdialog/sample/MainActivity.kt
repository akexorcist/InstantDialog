package com.akexorcist.library.instantdialog.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.akexorcist.library.instantdialog.alertdialog.alert.AlertDialog
import com.akexorcist.library.instantdialog.alertdialog.alert.AlertDialogViewModel
import com.akexorcist.library.instantdialog.confirm.ConfirmDialog
import com.akexorcist.library.instantdialog.confirm.ConfirmDialogViewModel
import com.akexorcist.library.instantdialog.loading.LoadingDialog
import com.akexorcist.library.instantdialog.loading.LoadingDialogViewModel
import com.akexorcist.library.instantdialog.sample.custom.ThreeChoiceDialog
import com.akexorcist.library.instantdialog.sample.custom.ThreeChoiceDialogViewModel
import com.akexorcist.library.instantdialog.sample.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val confirmDialogViewModel: ConfirmDialogViewModel by lazy {
        ViewModelProvider(this).get(ConfirmDialogViewModel::class.java)
    }
    private val alertDialogViewModel: AlertDialogViewModel by lazy {
        ViewModelProvider(this).get(AlertDialogViewModel::class.java)
    }
    private val loadingDialogViewModel: LoadingDialogViewModel by lazy {
        ViewModelProvider(this).get(LoadingDialogViewModel::class.java)
    }
    private val threeChoiceDialogViewModel: ThreeChoiceDialogViewModel by lazy {
        ViewModelProvider(this).get(ThreeChoiceDialogViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        bindDialogEvent()
        bindView()
    }

    private fun bindView() {
        binding.showDefaultAlertDialogButton.setOnClickListener { showDefaultAlertDialog() }
        binding.showDefaultConfirmDialogButton.setOnClickListener { showDefaultConfirmDialog() }
        binding.showDefaultLoadingDialogButton.setOnClickListener { showDefaultLoadingDialog() }
        binding.showCustomAlertDialogButton.setOnClickListener { showCustomAlertDialog() }
        binding.showCustomConfirmDialogButton.setOnClickListener { showCustomConfirmDialog() }
        binding.showCustomLoadingDialogButton.setOnClickListener { showCustomLoadingDialog() }
        binding.showThreeChoiceDialogButton.setOnClickListener { showThreeChoiceDialog() }
    }

    private fun bindDialogEvent() {
        alertDialogViewModel.neutralButtonClicked.observe(this, {
            it.dismiss()
            if (it.tag == TAG_DEFAULT_ALERT_DIALOG) {
                showMessage("Greeting message accepted")
            } else if (it.tag == TAG_CUSTOM_ALERT_DIALOG) {
                showMessage("Please don't do that again, OK?")
            }
        })

        alertDialogViewModel.dialogDismiss.observe(this, {
            showMessage("Dialog dismissed")
        })

        confirmDialogViewModel.positiveButtonClicked.observe(this, {
            it.dismiss()
            if (it.tag == TAG_DEFAULT_CONFIRM_DIALOG) {
                showMessage("Delete the data confirmed")
            } else if (it.tag == TAG_CUSTOM_CONFIRM_DIALOG) {
                showMessage("Update new version confirmed")
            }
        })

        confirmDialogViewModel.negativeButtonClicked.observe(this, {
            it.dismiss()
            if (it.tag == TAG_DEFAULT_CONFIRM_DIALOG) {
                showMessage("Delete the data cancelled")
            } else if (it.tag == TAG_CUSTOM_CONFIRM_DIALOG) {
                showMessage("Update new version cancelled")
            }
        })

        loadingDialogViewModel.dialogDismiss.observe(this, {
            if (it.tag == TAG_DEFAULT_LOADING_DIALOG) {
                showMessage("Default loading dismissed")
            } else if (it.tag == TAG_CUSTOM_LOADING_DIALOG) {
                showMessage("Custom loading dismissed")
            }
        })

        threeChoiceDialogViewModel.positiveButtonClicked.observe(this, {
            it.dismiss()
            showMessage("Thank you! we will bring you to Google Play")

        })

        threeChoiceDialogViewModel.neutralButtonClicked.observe(this, {
            it.dismiss()
            showMessage("Well, let us ask you in the next time")
        })

        threeChoiceDialogViewModel.negativeButtonClicked.observe(this, {
            it.dismiss()
            showMessage("OK, we won't ask you again")
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

    private val showThreeChoiceDialog = {
        ThreeChoiceDialog.Builder().apply {
            title = "Do you like our app?"
            message = "Please tell us by give a review in Google Play"
        }.build().show(supportFragmentManager, TAG_THREE_CHOICE_DIALOG)
    }

    private fun showMessage(message: String) {
        Snackbar.make(window.decorView.rootView, message, Snackbar.LENGTH_SHORT).show()
    }

    companion object {
        private const val TAG_DEFAULT_LOADING_DIALOG = "tag_default_loading_dialog"
        private const val TAG_DEFAULT_ALERT_DIALOG = "tag_default_alert_dialog"
        private const val TAG_DEFAULT_CONFIRM_DIALOG = "tag_default_confirm_dialog"
        private const val TAG_CUSTOM_ALERT_DIALOG = "tag_custom_alert_dialog"
        private const val TAG_CUSTOM_CONFIRM_DIALOG = "tag_custom_confirm_dialog"
        private const val TAG_CUSTOM_LOADING_DIALOG = "tag_custom_loading_dialog"
        private const val TAG_THREE_CHOICE_DIALOG = "tag_three_choice_dialog"
    }
}
