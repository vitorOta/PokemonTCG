package pokemontcg.libraries.ui_components.extensions

import android.app.Activity
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog

fun Activity.showMessage(message: String) {
    createDialog(message).show()
}

fun Activity.createDialog(message: String): AlertDialog {
    return AlertDialog.Builder(this)
        .setMessage(message)
        .show()
}

fun Activity.createLoadingDialog(isCancelable: Boolean = false): AlertDialog {
    val progressBar = ProgressBar(this).apply {
        isIndeterminate = true
    }

    val dialog = AlertDialog.Builder(this)
        .setView(progressBar)
        .setCancelable(isCancelable)
        .create()

    dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
    dialog.window!!.setLayout(200, 10)

    return dialog
}