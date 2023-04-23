package com.example.newentranttest.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.activity.addCallback
import androidx.annotation.ColorInt
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import java.io.File
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.*

typealias SingleBlock <T> = (T) -> Unit

fun FragmentActivity.statusBarColor(
    @ColorInt statusBarColor: Int,
    @ColorInt navigationBarColor: Int,
    darkStatusBarTint: Boolean
) {
    val win: Window = (window).also {
        it.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        it.statusBarColor = statusBarColor
    }

    val dec = win.decorView
    if (darkStatusBarTint) {
        dec.systemUiVisibility = dec.systemUiVisibility or
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    } else {
        dec.systemUiVisibility = 0
    }
}

@SuppressLint("SimpleDateFormat")
fun FragmentActivity.getAutoNightMode(): String {
    val hour = Date()
    val df = SimpleDateFormat("HH")
    val string = df.format(hour).toInt()

    return if (string < 6 || string > 18) "night" else "day"
}


fun Fragment.setBackPressed() {
    activity?.onBackPressedDispatcher?.addCallback {
        findNavController().popBackStack()
    }
}


fun File.getExtension(): String {
    val encoded: String = try {
        URLEncoder.encode(name, "UTF-8").replace("+", "%20")
    } catch (e: Exception) {
        name
    }

    return MimeTypeMap.getFileExtensionFromUrl(encoded).toLowerCase(Locale.getDefault())
}

fun toast(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}
