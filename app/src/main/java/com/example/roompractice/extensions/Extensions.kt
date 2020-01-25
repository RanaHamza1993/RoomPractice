package com.example.roompractice.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import es.dmoral.toasty.Toasty

fun Context.showSuccessMessage(message: String) {
    try {
        Toasty.success(this, message, Toast.LENGTH_SHORT, true).show()

    }catch (e: WindowManager.BadTokenException){}
}

fun Context.showInfoMessage(message: String) {
    try{
        Toasty.info(this, message, Toast.LENGTH_SHORT, true).show()
    }catch (e: WindowManager.BadTokenException){}
}

fun Context.showErrorMessage(message: String) {
    try{
        Toasty.error(this, message, Toast.LENGTH_SHORT, true).show()
    }catch (e: WindowManager.BadTokenException){}
}


fun  Context.setStatusBarColor(activity: Activity, color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        val window = activity?.getWindow()
        window!!.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window!!.setStatusBarColor(color)
    }
}

fun View.show() {
    visibility = View.VISIBLE
}
fun View.hide() {
    visibility = View.GONE
}
fun View.snackBar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackbar ->
        snackbar.setAction("Ok") {
            snackbar.dismiss()
        }.show()
    }
}