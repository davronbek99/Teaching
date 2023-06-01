package com.example.teaching.fragments.bottomSheet

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.teaching.databinding.AddPhotoChooseTypeDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddPhotoTypeBottomSheetFragment(val onItemClickListener: OnItemClickListener):BottomSheetDialogFragment() {

    lateinit var binding: AddPhotoChooseTypeDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddPhotoChooseTypeDialogBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //set up
        uiClickListener()
    }

    private fun uiClickListener() {
        binding.cameraButton.setOnClickListener {
            onItemClickListener.onCameraSelected()
            dismiss()
        }
         binding.galleryButton.setOnClickListener {
            onItemClickListener.onGallerySelected()
            dismiss()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener { setupBottomSheet(it) }
        return dialog
    }

    private fun setupBottomSheet(dialogInterface: DialogInterface) {
        val bottomSheetDialog = dialogInterface as BottomSheetDialog
        val bottomSheet = bottomSheetDialog.findViewById<View>(
            com.google.android.material.R.id.design_bottom_sheet
        ) ?: return
        bottomSheet.setBackgroundColor(Color.TRANSPARENT)
    }

    interface OnItemClickListener {
        fun onGallerySelected()
        fun onCameraSelected()
    }
}