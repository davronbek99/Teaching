package com.example.teaching.fragments

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newentranttest.extensions.getRotationRightBitmap
import com.example.newentranttest.extensions.rotatedImage
import com.example.newentranttest.extensions.setBackStackData
import com.example.newentranttest.extensions.setBottomAnimations
import com.example.newentranttest.extensions.toast
import com.example.teaching.databinding.FragmentConfirmPhotoBinding
import com.example.teaching.utils.Common
import com.fenchtose.nocropper.Cropper
import com.fenchtose.nocropper.CropperCallback
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream


class ConfirmPhotoFragment : Fragment() {

    private var photoFile: File? = null
    private var fromWhere: String = "gallery"
    private var frontBackType = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBottomAnimations()

        arguments?.let {
            photoFile = it.getSerializable("file") as File?
            fromWhere = it.getString("from", "gallery")
            frontBackType = it.getInt("cameraType")
        }
    }

    private lateinit var binding: FragmentConfirmPhotoBinding

    private lateinit var cropper: Cropper
    private lateinit var bitmap: Bitmap
    private lateinit var rotatedImageBitmap: Bitmap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentConfirmPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnBackPressed()
        setUpClickListeners()
        setUpPhotoCrop()

        Common.fromConfirmPhotoFragment = true
    }

    private fun setUpPhotoCrop() {
        bitmap = getRotationRightBitmap(photoFile!!)

        if (fromWhere != "gallery") {
            if (frontBackType == 0) {
                rotatedImageBitmap = rotatedImage(bitmap, -90f)
            } else {
                rotatedImageBitmap = rotatedImage(bitmap, 0f)
            }
        } else {
            rotatedImageBitmap = rotatedImage(bitmap, 0f)
        }


        val outputStream = ByteArrayOutputStream()
        rotatedImageBitmap.compress(Bitmap.CompressFormat.JPEG, 50, outputStream)
        val byteArray: ByteArray = outputStream.toByteArray()
        val encodedString: String = Base64.encodeToString(byteArray, Base64.DEFAULT)

        binding.cropperView.setImageBitmap(rotatedImageBitmap)

    }

    private fun setUpClickListeners() {
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.saveButton.setOnClickListener {
            cropper = Cropper(
                binding.cropperView.cropInfo.cropInfo,
                rotatedImageBitmap
            )

            cropper.crop(object : CropperCallback() {
                override fun onCropped(bitmap: Bitmap?) {
                    //todo save new bitmap and close this fragment
                    if (photoFile != null && bitmap != null) {
                        if (Common.isPhotoChangingForFragments) {
                            Common.bitmapMutableLiveData.postValue(bitmap)
                            findNavController().popBackStack()
                        } else {
                            setBackStackData("bitmap", bitmap, true)
                        }
                    } else {
                        toast(requireContext(), "Something went wrong!")
                    }
                }
            })
            cropper.cropBitmap()
        }

        binding.retakeButton.setOnClickListener {
            //todo retake option
            if (Common.isPhotoChangingForFragments) {
                Common.fromMutableLiveData.postValue(fromWhere)
                findNavController().popBackStack()
            } else {
                setBackStackData("from", fromWhere, true)
            }
        }
    }

    private fun saveImage(photoFile: File, bitmap: Bitmap) {
        try {
            val fileOutputStream: FileOutputStream =
                requireActivity().openFileOutput(photoFile.name, Context.MODE_PRIVATE)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
            fileOutputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setOnBackPressed() {
        activity?.onBackPressedDispatcher?.addCallback {
            findNavController().popBackStack()
        }
    }
}