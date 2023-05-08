package com.example.linkedinclone.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.res.Configuration
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.linkedinclone.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

object Extensions {

    // Call this ext. function directly to load images using glide
    @JvmStatic
    fun View.loadImage(drawable: Int, compress: Boolean = false) {
        Glide.with(this)
            .load(drawable)
            .into(this as ImageView)
    }

    // Call this ext. function to create an instance of BottomSheetDialog with the parameters as per user's choice
    @JvmStatic
    fun Context.createBottomSheet(view: View, fullHeight: Boolean = false, cancellable: Boolean = true): BottomSheetDialog {
        val bottomSheetDialog = BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
        bottomSheetDialog.let {
            it.setContentView(view)
            it.setCancelable(cancellable)
            if (fullHeight) {
                it.behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return bottomSheetDialog
    }

    // Use this ext. function to show bottomSheetDialog on the user's screen
    @JvmStatic
    fun Context.showBottomSheet(bottomSheet: BottomSheetDialog) {
        bottomSheet.create()
        bottomSheet.show()
    }

    // This ext. function will create an instance of the center dialog which can be used as a variable
    @JvmStatic
    fun Context.createCenterDialog(view: View, fullHeight: Boolean = false): Dialog {
        val dialog = Dialog(this)
        dialog.let {
            it.requestWindowFeature(Window.FEATURE_NO_TITLE)
            it.setContentView(view)
            it.window?.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.main_modal_background
                )
            )
        }

        // Here we're setting the size (Width and Height) depending on the user's requirement
        val metrics = this.resources.displayMetrics
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            dialog.window?.setLayout(
                (metrics.widthPixels * 0.6F).toInt(),
                if (fullHeight) (metrics.heightPixels * 0.9F).toInt() else ViewGroup.LayoutParams.WRAP_CONTENT
            )
        } else {
            dialog.window?.setLayout(
                (metrics.widthPixels * 0.9F).toInt(),
                if (fullHeight) (metrics.heightPixels * 0.9F).toInt() else ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
        return dialog
    }

    // Use this ext. function to show dialog on the screen
    @JvmStatic
    fun Context.showCenterDialog(dialog: Dialog) {
        dialog.create()
        dialog.show()
    }

    // Use this ext. function under activity class
    @JvmStatic
    fun Activity.logs(message: String) {
        Log.d("ACTIVITY:: ", message)
    }

    // Use this ext. function under fragment class
    @JvmStatic
    fun Fragment.logs(message: String) {
        Log.d("FRAGMENT:: ", message)
    }

    // Use this ext. function with any context
    @JvmStatic
    fun Context.logs(message: String) {
        Log.d("CONTEXT:: ", message)
    }

}