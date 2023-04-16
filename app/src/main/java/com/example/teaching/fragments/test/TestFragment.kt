package com.example.teaching.fragments.test

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.teaching.R
import com.example.teaching.adapters.MathAdapter
import com.example.teaching.databinding.FragmentTestBinding
import com.example.teaching.models.MathTest
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class TestFragment : Fragment() {
    private lateinit var binding: FragmentTestBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    private lateinit var countDownTimer: CountDownTimer
    private var isHave = false
    private lateinit var mathAdapter: MathAdapter
    private lateinit var testList: ArrayList<MathTest>
    private var testPosition: Int = 0
    private lateinit var answerList: ArrayList<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTestBinding.inflate(inflater, container, false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}