package com.example.teaching.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.newentranttest.extensions.getExtension
import com.example.newentranttest.extensions.setBackPressed
import com.example.teaching.R
import com.example.teaching.databinding.FragmentMyProfileBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.single.PermissionListener
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileOutputStream

class MyProfileFragment : Fragment() {
    private lateinit var binding: FragmentMyProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setBackPressed()
        setClickListener()
    }

    private fun init() {

    }

    private fun setClickListener() {

        binding.image.setOnClickListener {
            if (requireContext().checkCallingOrSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                getImage.launch("image/*")
            } else {
                requestPermission()
            }
        }

        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private val getImage = registerForActivityResult(ActivityResultContracts.GetContent()) {
        if (it != null) {

            val cR = requireContext().contentResolver
            val mime = MimeTypeMap.getSingleton()
            val type = mime.getExtensionFromMimeType(cR.getType(it))
            val cursor = cR.query(it, null, null, null, null)

            if (cursor != null && cursor.moveToFirst()) {
                val index = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                val displayName = cursor.getString(index)
                val file = saveImage(it, "$displayName")

                val requestFile =
                    RequestBody.create(
                        MimeTypeMap.getSingleton().getMimeTypeFromExtension(file.getExtension())
                            ?.toMediaType(), file
                    )

                // MultipartBody.Part is used to send also the actual file name
                val photoBody = MultipartBody.Part.createFormData("photo", file.name, requestFile)

//                viewModel.updatePhoto(photoBody)
//                binding.loadingLayout.visibility = View.VISIBLE

                Glide.with(binding.image).load(it)
                    .placeholder(R.drawable.ic_carbon_user_avatar_filled)
                    .into(binding.image)
            }
        }
    }

    private fun requestPermission() {
        Dexter.withContext(requireContext())
            .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse?) { /* ... */
                    getImage.launch("image/*")
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse?) { /* ... */
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri: Uri =
                        Uri.fromParts("package", requireActivity().packageName, null)
                    intent.data = uri
                    startActivity(intent)
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: com.karumi.dexter.listener.PermissionRequest?,
                    p1: PermissionToken?
                ) {
                    p1?.continuePermissionRequest()
                }


            }).check()
    }

    @SuppressLint("Recycle")
    private fun saveImage(imageUri: Uri?, filename: String): File {

        val dir = requireContext().getDir(
            "TaxiDriver",
            Context.MODE_PRIVATE
        ) //Creates Dir inside internal memory

        var file: File? = null

        if (imageUri != null) {
            file = File(dir, filename) //It has directory details and file name

            if (file.exists()) file.delete()

            file.createNewFile()


            val fos = FileOutputStream(file)

            try {
                val inputStream = activity?.contentResolver?.openInputStream(imageUri)!!

                inputStream.copyTo(fos, DEFAULT_BUFFER_SIZE)

                fos.flush()
                fos.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            file = File(dir, "$filename.jpg") //It has directory details and file name

            if (file.exists()) file.delete()

            file.createNewFile()
        }

        return file
    }
}