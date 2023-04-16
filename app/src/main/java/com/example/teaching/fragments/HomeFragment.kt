package com.example.teaching.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.example.teaching.R
import com.example.teaching.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListener()
        setOnBackPressed()
    }

    private fun setClickListener() {
        binding.cardClass5.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("class", "class 5")
            findNavController().navigate(R.id.home_to_class_5, bundle)
        }

        binding.cardClass6.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("class", "class 6")
            findNavController().navigate(R.id.home_to_class_6, bundle)
        }

        binding.cardClass7.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("class", "class 7")
            findNavController().navigate(R.id.home_to_class_7, bundle)
        }

        binding.cardClass8.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("class", "class 8")
            findNavController().navigate(R.id.home_to_class_8, bundle)
        }

        binding.cardClass9.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("class", "class 9")
            findNavController().navigate(R.id.home_to_class_9, bundle)
        }
    }

    private fun setOnBackPressed() {
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner) {
            requireActivity().finishAffinity()
        }
    }
}