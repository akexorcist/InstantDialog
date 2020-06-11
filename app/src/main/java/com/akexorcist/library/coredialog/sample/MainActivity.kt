package com.akexorcist.library.coredialog.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.akexorcist.dialog.component.alert.AlertDialog
import com.akexorcist.dialog.component.confirm.ConfirmDialog
import com.akexorcist.dialog.component.confirm.ConfirmViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val dialogViewModel: ConfirmViewModel by lazy { ViewModelProvider(this).get(ConfirmViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dialogViewModel.positiveButtonClicked.observe(this, Observer {
            Log.e("Check", "Confirm")
        })

        dialogViewModel.negativeButtonClicked.observe(this, Observer {
            Log.e("Check", "Cancel")
        })

        showAlertDialogButton.setOnClickListener { showAlertDialog() }
        showConfirmDialogButton.setOnClickListener { showConfirmDialog() }
    }

    private val showAlertDialog = {
        AlertDialog.Builder().apply {
            title = "Akexorcist"
            message = "Sleeping For Less"
            iconResourceId = R.drawable.ic_check
        }.build().show(supportFragmentManager, "confirm")
    }

    private val showConfirmDialog = {
        ConfirmDialog.Builder().apply {
            title = "Akexorcist"
            message = "Sleeping For Less"
        }.build().show(supportFragmentManager, "confirm")
    }
}
