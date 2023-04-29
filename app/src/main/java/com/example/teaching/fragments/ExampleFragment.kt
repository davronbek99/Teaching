package com.example.teaching.fragments

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.example.teaching.MainActivity
import com.example.teaching.R
import com.example.teaching.databinding.FragmentExamBinding
import com.example.teaching.databinding.FragmentExampleBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import java.io.File
import java.io.IOException
import java.util.Objects

class ExampleFragment : Fragment() {

    private lateinit var binding: FragmentExampleBinding
    private val CAMERA_REQ_CODE = 100

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExampleBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        openSomeActivityForResult()
        binding.image.setOnClickListener {
//            ImagePicker.with(requireActivity()).crop().compress(1024).maxResultSize(1080, 1080)
//                .start()

//            getContent.launch("image/*")

        }

    }

//    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
//        // Handle the returned Uri
//        binding.image.setImageURI(uri)
//        try {
//            val myObj = File(uri)
//            if (myObj.createNewFile()) {
//                println("File created: " + myObj.name)
//            } else {
//                println("File already exists.")
//            }
//        } catch (e: IOException) {
//            println("An error occurred.")
//            e.printStackTrace()
//        }
//    }

//    private fun openSomeActivityForResult() {
//        val intent = Intent(requireContext(), MainActivity::class.java)
//        resultLauncher.launch(intent)
//    }
//
//    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//        if (result.resultCode == Activity.RESULT_OK) {
//            // There are no request codes
//            val data: Intent? = result.data
//            val uri = data?.data
//            binding.image.setImageURI(uri)
//
//        }
//    }
}