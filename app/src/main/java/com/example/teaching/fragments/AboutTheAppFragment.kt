package com.example.teaching.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newentranttest.extensions.setBackPressed
import com.example.teaching.R
import com.example.teaching.databinding.FragmentAboutTheAppBinding

class AboutTheAppFragment : Fragment() {
    private lateinit var binding: FragmentAboutTheAppBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutTheAppBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setBackPressed()
    }
}