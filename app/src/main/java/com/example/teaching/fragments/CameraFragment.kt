package com.example.teaching.fragments

import android.animation.Animator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.activity.addCallback
import androidx.appcompat.widget.AppCompatImageView
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newentranttest.extensions.setBackStackData
import com.example.newentranttest.extensions.setBottomAnimations
import com.example.teaching.R
import com.example.teaching.databinding.FragmentCameraBinding
import com.example.teaching.utils.Common
import java.io.File

class CameraFragment : Fragment() {


    private lateinit var binding: FragmentCameraBinding
    private val FRONT_CAMERA = 0
    private val BACK_CAMERA = 1
    private var currentCameraType = BACK_CAMERA
    private var flashlight = false
    private var file: File? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            file = it.getSerializable("file") as File
            val from = it.getBoolean("from", false)

            if (from) {
                setBottomAnimations()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCameraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnBackPressed()
        setUpCamera()
        setClickListener()
    }


    private fun setClickListener() {
        binding.cancelButton.setOnClickListener {
            binding.cameraView.visibility = View.VISIBLE
            binding.bottomLayout.animate().setDuration(500).translationY(0f).start()
            binding.imageView.visibility = View.GONE
            binding.flashlightButton.isVisible = currentCameraType == BACK_CAMERA
        }

        binding.doneButton.setOnClickListener {
            setBackStackData("success", true, true)
        }

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.takePhotoButton.setOnClickListener {
            takePhoto()
        }

        binding.changeCameraButton.setOnClickListener {
            if (currentCameraType == BACK_CAMERA) {
                currentCameraType = FRONT_CAMERA
                setUpCamera(FRONT_CAMERA, flashlight)
            } else {
                currentCameraType = BACK_CAMERA
                setUpCamera(BACK_CAMERA, flashlight)
            }

            binding.flashlightButton.isVisible = currentCameraType == BACK_CAMERA

            val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.change_camera_anim)

            it.startAnimation(anim)
        }

        binding.flashlightButton.setOnClickListener {
            flashlight = !flashlight
            setUpCamera(currentCameraType, flashlight)
            setAnimFlashLight(flashlight, binding.flashlightButton)
        }
    }

    private fun setAnimFlashLight(flashlight: Boolean, view: AppCompatImageView) {
        view.animate().scaleX(0f).setDuration(300)
            .setListener(object : Animator.AnimatorListener {

                override fun onAnimationStart(p0: Animator) {

                }

                override fun onAnimationEnd(p0: Animator) {
                    if (flashlight) binding.flashlightButton.setImageResource(R.drawable.ic_flash_on) else binding.flashlightButton.setImageResource(
                        R.drawable.ic_flash_off
                    )

                    view.animate().setDuration(300).scaleX(1f).start()
                }

                override fun onAnimationCancel(p0: Animator) {
                    TODO("Not yet implemented")
                }

                override fun onAnimationRepeat(p0: Animator) {
                    TODO("Not yet implemented")
                }
            }).start()

    }

    private fun setUpCamera(cameraType: Int = BACK_CAMERA, flashLight: Boolean = false) {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            val builder = ImageCapture.Builder()
                .setTargetRotation(
                    requireActivity().getWindowManager().getDefaultDisplay().getRotation()
                )
            if (flashLight) {
                builder.setFlashMode(ImageCapture.FLASH_MODE_ON)
            } else {
                builder.setFlashMode(ImageCapture.FLASH_MODE_OFF)
            }


            imageCapture = builder.build()

            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.cameraView.surfaceProvider)
                }


            // Select back camera as a default
            val cameraSelector = if (cameraType == BACK_CAMERA) {
                CameraSelector.DEFAULT_BACK_CAMERA
            } else {
                CameraSelector.DEFAULT_FRONT_CAMERA
            }


            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture
                )

            } catch (exc: Exception) {

            }

        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun setOnBackPressed() {
        activity?.onBackPressedDispatcher?.addCallback {
            findNavController().popBackStack()
        }
    }

    private var imageCapture: ImageCapture? = null

    private fun takePhoto() {
        // Get a stable reference of the modifiable image capture use case
        val imageCapture = imageCapture ?: return

        // Create output options object which contains file + metadata
        val outputOptions = ImageCapture.OutputFileOptions
            .Builder(
                file!!
            )
            .setMetadata(ImageCapture.Metadata().also {
                it.isReversedHorizontal = currentCameraType != BACK_CAMERA
            })
            .build()

        // Set up image capture listener, which is triggered after photo has
        // been taken
        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(requireContext()),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    if (Common.isPhotoChangingForFragments) {
                        Common.successLiveData.postValue(currentCameraType)
                        findNavController().popBackStack()
                    } else {
                        setBackStackData("success", currentCameraType, true)
                    }
                }
            })
    }


}