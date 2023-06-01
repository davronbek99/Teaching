package com.example.newentranttest.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.os.Build
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
import com.google.android.material.transition.platform.MaterialSharedAxis
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


//bottom sheet animation
fun Fragment.setBottomAnimations() {
    val exitTransation = MaterialSharedAxis(MaterialSharedAxis.Y, /* forward= */ true)
    exitTransation.duration = 500

    exitTransition = exitTransation
    enterTransition = exitTransition


    val reenterTransation = MaterialSharedAxis(MaterialSharedAxis.Y, false)
    reenterTransation.duration = 500

    reenterTransition = reenterTransation
    returnTransition = reenterTransition
}

//animation add photo fragment
fun Fragment.setAnimationsForAddPhotoFragment() {
    val exitTransation = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ true)
    exitTransation.duration = 500
    enterTransition = exitTransation

    val exitTransinsartion = MaterialSharedAxis(MaterialSharedAxis.Y, true)
    exitTransinsartion.duration = 500
    exitTransition = exitTransinsartion

    val reenterTransation = MaterialSharedAxis(MaterialSharedAxis.Y, false)
    reenterTransation.duration = 500
    reenterTransition = reenterTransation

    val returnTransation = MaterialSharedAxis(MaterialSharedAxis.X, false)
    returnTransation.duration = 500
    returnTransition = returnTransation

}

//rotate image to true angle
fun getRotationRightBitmap(file: File): Bitmap {
    var bitmap: Bitmap = BitmapFactory.decodeFile(file.path)

    val ei = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        ExifInterface(file.absolutePath)
    } else {
        ExifInterface(file.absolutePath)
    }
    val orientation = ei.getAttributeInt(
        ExifInterface.TAG_ORIENTATION,
        ExifInterface.ORIENTATION_UNDEFINED
    )

    var rotatedBitmap: Bitmap? = null
    rotatedBitmap = when (orientation) {
        ExifInterface.ORIENTATION_ROTATE_90 -> rotatedImage(bitmap, 90f)
        ExifInterface.ORIENTATION_ROTATE_180 -> rotatedImage(bitmap, 180f)
        ExifInterface.ORIENTATION_ROTATE_270 -> rotatedImage(bitmap, 270f)
        ExifInterface.ORIENTATION_NORMAL -> bitmap
        else -> bitmap
    }

    return rotatedBitmap
}

//return rotated image bitmap
fun rotatedImage(source: Bitmap, angle: Float): Bitmap {
    val matrix = Matrix()
    matrix.postRotate(angle)
    return Bitmap.createBitmap(
        source, 0, 0, source.width, source.height,
        matrix, true
    )
}


//retrieve data to previous fragment
fun <T> Fragment.setBackStackData(key: String, data: T, doBack: Boolean = false) {
    findNavController().previousBackStackEntry?.savedStateHandle?.set(key, data)
    if (doBack)
        findNavController().popBackStack()
}
