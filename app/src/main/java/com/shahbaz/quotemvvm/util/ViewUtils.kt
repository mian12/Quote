package com.shahbaz.quotemvvm.util

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun Context.toast(message: String) {

    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

}

fun ProgressBar.show() {
    visibility = View.VISIBLE
}

fun ProgressBar.hide() {
    visibility = View.INVISIBLE
}

fun View.snakebar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).also { snakebar ->
        snakebar.setAction("OK") {
            snakebar.dismiss()
        }
    }.show()

}