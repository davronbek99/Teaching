package com.example.teaching.fragments.test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.newentranttest.extensions.setBackPressed
import com.example.teaching.R
import com.example.teaching.databinding.FragmentExamBinding

class ExamFragment : Fragment() {
    private lateinit var binding: FragmentExamBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExamBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListener()
        setBackPressed()
    }

    private fun setClickListener() {
        binding.exam5Math.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("class", "class 5")
            findNavController().navigate(R.id.to_test, bundle)
        }

        binding.exam6Math.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("class", "class 6")
            findNavController().navigate(R.id.to_test, bundle)
        }

        binding.exam7Math.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("class", "class 7 math")
            findNavController().navigate(R.id.to_test, bundle)
        }

        binding.exam7Geometer.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("class", "class 7 geo")
            findNavController().navigate(R.id.to_test, bundle)
        }

        binding.exam8Math.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("class", "class 8 math")
            findNavController().navigate(R.id.to_test, bundle)
        }

        binding.exam8Geometer.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("class", "class 8 geo")
            findNavController().navigate(R.id.to_test, bundle)
        }

        binding.exam9Math.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("class", "class 9 math")
            findNavController().navigate(R.id.to_test, bundle)
        }

        binding.exam9Geometer.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("class", "class 9 geo")
            findNavController().navigate(R.id.to_test, bundle)
        }
    }

}