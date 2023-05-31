package com.example.teaching.fragments.test

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.newentranttest.extensions.setBackPressed
import com.example.teaching.R
import com.example.teaching.adapters.Math5Adapter
import com.example.teaching.adapters.MathAdapter
import com.example.teaching.databinding.FragmentTestBinding
import com.example.teaching.models.Math5Test
import com.example.teaching.models.MathTest
import com.example.teaching.utils.BaseDialog
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class TestFragment : Fragment(), MathAdapter.OnClickTestListener, Math5Adapter.OnClickTestListener {
    private lateinit var binding: FragmentTestBinding

    private lateinit var valueStr: String

    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    private lateinit var countDownTimer: CountDownTimer
    private var isHave = false
    private lateinit var mathAdapter: MathAdapter
    private lateinit var math5Adapter: Math5Adapter
    private lateinit var testList: ArrayList<Math5Test>
    private lateinit var testImgList: ArrayList<MathTest>

    //    private var testPosition: Int = 0
    private lateinit var answerList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        valueStr = arguments?.getString("class", "").toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        testFirebase()
        init()
        setBackPressed()
    }

    private fun init() {
    }

    private fun testFirebase() {
        testList = ArrayList()
        testImgList = ArrayList()
        mathAdapter = MathAdapter(testImgList, this)
        math5Adapter = Math5Adapter(testList, this)
        answerList = ArrayList()
        firebaseDatabase = FirebaseDatabase.getInstance()
        when (valueStr) {
            "class 5" -> {
                databaseReference = firebaseDatabase.getReference("all/math5")
                databaseReference.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (child in snapshot.children) {
                            val value = child.getValue(Math5Test::class.java)
                            Log.d("FIREBASE", "${snapshot.childrenCount}")
                            testList.add(value!!)
                            answerList.add(value.ans)
                        }

                        binding.loadingLayout.loadingLayout.visibility = View.GONE
                        binding.container.visibility = View.VISIBLE
                        math5Adapter = Math5Adapter(testList, this@TestFragment)
                        binding.rvTest.adapter = math5Adapter

                        startTimer()

                        if (isHave) {
                            testFinishDialog()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                    }

                })
            }
            "class 6" -> {
                databaseReference = firebaseDatabase.getReference("all/math6")
                databaseReference.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (child in snapshot.children) {
                            val value = child.getValue(Math5Test::class.java)
                            Log.d("FIREBASE", "${snapshot.childrenCount}")
                            testList.add(value!!)
                            answerList.add(value.ans)
                        }
                        binding.loadingLayout.loadingLayout.visibility = View.GONE
                        binding.container.visibility = View.VISIBLE
                        math5Adapter = Math5Adapter(testList, this@TestFragment)
                        binding.rvTest.adapter = math5Adapter

                        startTimer()


                        if (isHave) {
                            testFinishDialog()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                    }

                })
            }
            "class 7 math" -> {
                databaseReference = firebaseDatabase.getReference("all/math7")
                databaseReference.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (child in snapshot.children) {
                            val value = child.getValue(MathTest::class.java)
                            Log.d("FIREBASE", "${snapshot.childrenCount}")
                            testImgList.add(value!!)
                            answerList.add(value.ans)
                        }
                        binding.loadingLayout.loadingLayout.visibility = View.GONE
                        binding.container.visibility = View.VISIBLE
                        mathAdapter = MathAdapter(
                            testImgList, this@TestFragment
                        )
                        binding.rvTest.adapter = mathAdapter

                        startTimer()


                        if (isHave) {
                            testFinishDialog()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                    }

                })
            }
            "class 7 geo" -> {
                databaseReference = firebaseDatabase.getReference("all/geo7")
                databaseReference.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (child in snapshot.children) {
                            val value = child.getValue(MathTest::class.java)
                            Log.d("FIREBASE", "${snapshot.childrenCount}")
                            testImgList.add(value!!)
                            answerList.add(value.ans)
                        }
                        binding.loadingLayout.loadingLayout.visibility = View.GONE
                        binding.container.visibility = View.VISIBLE
                        mathAdapter = MathAdapter(
                            testImgList, this@TestFragment
                        )
                        binding.rvTest.adapter = mathAdapter

                        startTimer()


                        if (isHave) {
                            testFinishDialog()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                    }

                })
            }
            "class 8 math" -> {
                databaseReference = firebaseDatabase.getReference("all/math8")
                databaseReference.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (child in snapshot.children) {
                            val value = child.getValue(MathTest::class.java)
                            Log.d("FIREBASE", "${snapshot.childrenCount}")
                            testImgList.add(value!!)
                            answerList.add(value.ans)
                        }
                        binding.loadingLayout.loadingLayout.visibility = View.GONE
                        binding.container.visibility = View.VISIBLE
                        mathAdapter = MathAdapter(
                            testImgList, this@TestFragment
                        )
                        binding.rvTest.adapter = mathAdapter

                        startTimer()


                        if (isHave) {
                            testFinishDialog()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                    }

                })
            }
            "class 8 geo" -> {
                databaseReference = firebaseDatabase.getReference("all/geo8")
                databaseReference.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (child in snapshot.children) {
                            val value = child.getValue(MathTest::class.java)
                            Log.d("FIREBASE", "${snapshot.childrenCount}")
                            testImgList.add(value!!)
                            answerList.add(value.ans)
                        }
                        binding.loadingLayout.loadingLayout.visibility = View.GONE
                        binding.container.visibility = View.VISIBLE
                        mathAdapter = MathAdapter(
                            testImgList, this@TestFragment
                        )
                        binding.rvTest.adapter = mathAdapter

                        startTimer()


                        if (isHave) {
                            testFinishDialog()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                    }

                })
            }
            "class 9 math" -> {
                databaseReference = firebaseDatabase.getReference("all/math9")
                databaseReference.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (child in snapshot.children) {
                            val value = child.getValue(MathTest::class.java)
                            Log.d("FIREBASE", "${snapshot.childrenCount}")
                            testImgList.add(value!!)
                            answerList.add(value.ans)
                        }
                        binding.loadingLayout.loadingLayout.visibility = View.GONE
                        binding.container.visibility = View.VISIBLE
                        mathAdapter = MathAdapter(
                            testImgList, this@TestFragment
                        )
                        binding.rvTest.adapter = mathAdapter

                        startTimer()


                        if (isHave) {
                            testFinishDialog()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                    }

                })
            }
            "class 9 geo" -> {
                databaseReference = firebaseDatabase.getReference("all/geo9")
                databaseReference.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (child in snapshot.children) {
                            val value = child.getValue(MathTest::class.java)
                            Log.d("FIREBASE", "${snapshot.childrenCount}")
                            testImgList.add(value!!)
                            answerList.add(value.ans)
                        }
                        binding.loadingLayout.loadingLayout.visibility = View.GONE
                        binding.container.visibility = View.VISIBLE
                        mathAdapter = MathAdapter(
                            testImgList, this@TestFragment
                        )
                        binding.rvTest.adapter = mathAdapter

                        startTimer()


                        if (isHave) {
                            testFinishDialog()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                    }

                })
            }

        }

        binding.btnFinish.setOnClickListener {
            testFinishDialog()

        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun startTimer() {
        val df = SimpleDateFormat("mm:ss")
        countDownTimer = object : CountDownTimer(120000, 10) {
            override fun onTick(millisUntilFinished: Long) {
                binding.tvCountDown.text = df.format(Date(millisUntilFinished))
            }

            override fun onFinish() {

            }

        }

        countDownTimer.start()
    }

    private fun testFinishDialog() {

        val dialog = BaseDialog("Rostdan ham testni\n yakunlamoqchimisiz?", "Yo'q", "Ha")
        dialog.setOnDoneListener {
            dialog.dismiss()
            val score = calculateTests()
            countDownTimer.cancel()
            val bundle = Bundle()
            bundle.putInt("score", score)
            findNavController().navigate(R.id.to_result, bundle)
        }
        dialog.setOnCancelListener {
            dialog.dismiss()
        }
        dialog.show(childFragmentManager, "sdfg")
    }

    private fun calculateTests(): Int {
        var score = 0
        when (valueStr) {
            "class 5" -> {
                math5Adapter.answersList.keys.forEach {
                    if (testList[it].ans == math5Adapter.answersList[it]) {
                        score++
                    }
                }
            }
            "class 6" -> {
                math5Adapter.answersList.keys.forEach {
                    if (testList[it].ans == math5Adapter.answersList[it]) {
                        score++
                    }
                }
            }
            "class 7 math" -> {
                mathAdapter.answersList.keys.forEach {
                    if (testImgList[it].ans == mathAdapter.answersList[it]) {
                        score++
                    }
                }
            }
            "class 7 geo" -> {
                mathAdapter.answersList.keys.forEach {
                    if (testImgList[it].ans == mathAdapter.answersList[it]) {
                        score++
                    }
                }
            }
            "class 8 math" -> {
                mathAdapter.answersList.keys.forEach {
                    if (testImgList[it].ans == mathAdapter.answersList[it]) {
                        score++
                    }
                }
            }
            "class 8 geo" -> {
                mathAdapter.answersList.keys.forEach {
                    if (testImgList[it].ans == mathAdapter.answersList[it]) {
                        score++
                    }
                }
            }
            "class 9 math" -> {
                mathAdapter.answersList.keys.forEach {
                    if (testImgList[it].ans == mathAdapter.answersList[it]) {
                        score++
                    }
                }
            }
            "class 9 geo" -> {
                mathAdapter.answersList.keys.forEach {
                    if (testImgList[it].ans == mathAdapter.answersList[it]) {
                        score++
                    }
                }
            }

        }

        return score
    }

    override fun onClickTest(position: Int) {

    }

}