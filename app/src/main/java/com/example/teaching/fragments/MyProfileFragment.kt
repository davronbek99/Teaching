package com.example.teaching.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.newentranttest.extensions.getExtension
import com.example.newentranttest.extensions.setBackPressed
import com.example.teaching.R
import com.example.teaching.databinding.FragmentMyProfileBinding
import com.example.teaching.fragments.bottomSheet.editTv.EditTvBottomSheet
import com.example.teaching.utils.MySharedPreferences
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
    private var SELECT_PICTURE = 200
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

        binding.lastNameTv.text =
            MySharedPreferences.getSurname(requireContext())
        binding.firstNameTv.text =
            MySharedPreferences.getFirstname(requireContext())
        binding.middleNameTv.text =
            MySharedPreferences.getMiddlename(requireContext())
        binding.birthdayTv.text =
            MySharedPreferences.getDateOfBirth(requireContext())
        binding.phoneNumberTv.text =
            MySharedPreferences.getPhone(requireContext())
        binding.emailTv.text = MySharedPreferences.getEmail(requireContext())
        binding.addressTv.text =
            MySharedPreferences.getAddress(requireContext())
    }


    private fun setClickListener() {

        binding.image.setOnClickListener {
            if (requireContext().checkCallingOrSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                getImage.launch("image/*")
              imageChooser()
            } else {
                requestPermission()
            }
        }

        binding.linearLastname.setOnClickListener {
            val lastnameBottomSheet =
                EditTvBottomSheet("lastname",
                    object : EditTvBottomSheet.SetOnClickListener {
                        override fun onSaveClicked(editText: String) {
                            MySharedPreferences.setSurname(requireContext(), editText)
                            binding.lastNameTv.text =
                                MySharedPreferences.getSurname(requireContext())
                        }
                    })

            lastnameBottomSheet.show(childFragmentManager, "")
        }

        binding.linearFirstname.setOnClickListener {
            val firstnameBottomSheet =
                EditTvBottomSheet("firstname",
                    object : EditTvBottomSheet.SetOnClickListener {
                        override fun onSaveClicked(editText: String) {
                            MySharedPreferences.setFirstname(requireContext(), editText)
                            binding.firstNameTv.text =
                                MySharedPreferences.getFirstname(requireContext())
                        }
                    })

            firstnameBottomSheet.show(childFragmentManager, "")
        }

        binding.linearMiddleName.setOnClickListener {
            val middlename =
                EditTvBottomSheet("middlename",
                    object : EditTvBottomSheet.SetOnClickListener {
                        override fun onSaveClicked(editText: String) {
                            MySharedPreferences.setMiddlename(requireContext(), editText)
                            binding.middleNameTv.text =
                                MySharedPreferences.getMiddlename(requireContext())
                        }
                    })

            middlename.show(childFragmentManager, "")
        }

        binding.linearDateOfBirth.setOnClickListener {
            val dateOfBirth =
                EditTvBottomSheet("date_of_birth",
                    object : EditTvBottomSheet.SetOnClickListener {
                        override fun onSaveClicked(editText: String) {
                            MySharedPreferences.setDateOfBirth(requireContext(), editText)
                            binding.birthdayTv.text =
                                MySharedPreferences.getDateOfBirth(requireContext())
                        }
                    })

            dateOfBirth.show(childFragmentManager, "")
        }

        binding.linearPhoneNumber.setOnClickListener {
            val phoneNumber =
                EditTvBottomSheet("phone_number",
                    object : EditTvBottomSheet.SetOnClickListener {
                        override fun onSaveClicked(editText: String) {
                            MySharedPreferences.setPhone(requireContext(), editText)
                            binding.phoneNumberTv.text =
                                MySharedPreferences.getPhone(requireContext())
                        }
                    })

            phoneNumber.show(childFragmentManager, "")
        }

        binding.linearEmail.setOnClickListener {
            val email =
                EditTvBottomSheet("email",
                    object : EditTvBottomSheet.SetOnClickListener {
                        override fun onSaveClicked(editText: String) {
                            MySharedPreferences.setEmail(requireContext(), editText)
                            binding.emailTv.text = MySharedPreferences.getEmail(requireContext())
                        }
                    })

            email.show(childFragmentManager, "")
        }

        binding.linearAddress.setOnClickListener {
            val address =
                EditTvBottomSheet("address",
                    object : EditTvBottomSheet.SetOnClickListener {
                        override fun onSaveClicked(editText: String) {
                            MySharedPreferences.setAddress(requireContext(), editText)
                            binding.addressTv.text =
                                MySharedPreferences.getAddress(requireContext())
                        }
                    })

            address.show(childFragmentManager, "")
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
                val file = saveImage(it, displayName)

                val requestFile =
                    RequestBody.create(
                        MimeTypeMap.getSingleton().getMimeTypeFromExtension(file.getExtension())
                            ?.toMediaType(), file
                    )

                // MultipartBody.Part is used to send also the actual file name
                val photoBody = MultipartBody.Part.createFormData("photo", file.name, requestFile)

//                viewModel.updatePhoto(photoBody)
//                binding.loadingLayout.visibility = View.VISIBLE
//
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

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                val selectedImageUri = data.data
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    binding.image.setImageURI(selectedImageUri)
                }
            }
        }
    }

    fun imageChooser() {

        // create an instance of the
        // intent of the type image
        val i = Intent()
        i.type = "image/*"
        i.action = Intent.ACTION_GET_CONTENT

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE)
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