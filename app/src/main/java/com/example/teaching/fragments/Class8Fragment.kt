package com.example.teaching.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.newentranttest.extensions.setBackPressed
import com.example.teaching.R
import com.example.teaching.databinding.FragmentClass8Binding

class Class8Fragment : Fragment() {
    private lateinit var binding: FragmentClass8Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentClass8Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
setBackPressed()
        setClickListener()
    }

    private fun setClickListener() {
        val bundle = Bundle()
        binding.btnMath.setOnClickListener {
            bundle.putString("class", "math 8")
            findNavController().navigate(R.id.class_to_pdf, bundle)
        }

        binding.btnGeometer.setOnClickListener {
            bundle.putString("class", "geo 8")
            findNavController().navigate(R.id.class_to_pdf, bundle)
        }
    }
}