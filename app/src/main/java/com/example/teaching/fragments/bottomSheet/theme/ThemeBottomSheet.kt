package com.example.newentranttest.ui.bottomSheetFragments.theme

import android.content.Context
import android.view.LayoutInflater
import com.example.teaching.R
import com.example.teaching.databinding.ChooseThemeDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class ThemeBottomSheet(
    context: Context,
    val nightMode: String,
    private val onItemClickListener: OnItemClickListener
) :
    BottomSheetDialog(context, R.style.SheetDialog) {

    private val binding = ChooseThemeDialogBinding.inflate(LayoutInflater.from(context))

    interface OnItemClickListener {
        fun onThemeSelected(nightMode: String)
    }

    init {
        setContentView(binding.root)

        when (nightMode) {
            "day" -> binding.dayCheck.setImageResource(R.drawable.ic_icon_check)
            "night" -> binding.nightCheck.setImageResource(R.drawable.ic_icon_check)
            "auto" -> binding.autoCheck.setImageResource(R.drawable.ic_icon_check)
        }

        binding.autoBox.setOnClickListener {
            onItemClickListener.onThemeSelected("auto")
            dismiss()
        }

        binding.dayBox.setOnClickListener {
            onItemClickListener.onThemeSelected("day")
            dismiss()
        }

        binding.nightBox.setOnClickListener {
            onItemClickListener.onThemeSelected("night")
            dismiss()
        }
    }
}