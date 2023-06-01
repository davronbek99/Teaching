package com.example.teaching.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.newentranttest.extensions.setBackPressed
import com.example.teaching.R
import com.example.teaching.databinding.FragmentClass6Binding

class Class6Fragment : Fragment() {
    private lateinit var binding: FragmentClass6Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentClass6Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackPressed()
        setClickListener()
    }

    private fun setClickListener() {
        binding.btnMath.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("class", "math 6")
            findNavController().navigate(R.id.class_to_pdf, bundle)
        }
    }
}