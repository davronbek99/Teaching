package com.example.teaching.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.teaching.R
import com.example.teaching.databinding.FragmentClass5Binding

class Class5Fragment : Fragment() {
    private lateinit var binding: FragmentClass5Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentClass5Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClickListener()
    }

    private fun setClickListener() {
        binding.btnMath.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("class", "math 5")
            findNavController().navigate(R.id.class_to_pdf, bundle)
        }
    }
}