package com.app.abnezarutask.utils

import android.content.Context
import android.view.View
import android.widget.Toast

fun View.showOrHide(isShow: Boolean){
    visibility = if(isShow) View.VISIBLE else View.GONE
}

fun Context.showLongToast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}