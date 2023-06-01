package com.example.teaching.fragments.bottomSheet.editTv

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newentranttest.extensions.toast
import com.example.teaching.R
import com.example.teaching.databinding.BottomSheetEditTvBinding
import com.example.teaching.utils.MySharedPreferences
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class EditTvBottomSheet(
    val string: String,
    var onClick: SetOnClickListener
) :
    BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetEditTvBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetEditTvBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        setClickListener()
    }

    @SuppressLint("SetTextI18n")
    private fun init() {

//        binding.tvSubject.text =
//            "${model.subject_name} (${model.main_teacher?.last_name ?: ""} ${model.main_teacher?.first_name ?: ""})"
//
//        binding.etAreasOfExcellence.setText(model.academicachievement?.areas_of_excellence ?: "")
//        binding.etAreasForImprovement.setText(
//            model.academicachievement?.areas_for_improvement ?: ""
//        )
//        binding.etPredictedGrade.setText(
//            (model.academicachievement?.predictedgrade ?: "").toString()
//        )

        when (string) {
            "lastname" -> {
                binding.etSurname.setText(MySharedPreferences.getSurname(requireContext()))
                binding.etSurname.hint = getString(R.string.tv_surname)
                binding.tvSubject.setText(R.string.last_family_name)
            }

            "firstname" -> {
                binding.etSurname.setText(MySharedPreferences.getFirstname(requireContext()))
                binding.etSurname.hint = getString(R.string.tv_first_name)
                binding.tvSubject.setText(R.string.tv_first_name)
            }

            "middlename" -> {
                binding.etSurname.setText(MySharedPreferences.getMiddlename(requireContext()))
                binding.etSurname.hint = getString(R.string.tv_et_middle_name)
                binding.tvSubject.setText(R.string.tv_middle_name)
            }

            "date_of_birth" -> {
                binding.etSurname.setText(MySharedPreferences.getDateOfBirth(requireContext()))
                binding.etSurname.hint = getString(R.string.tv_date_of_birth)
                binding.tvSubject.setText(R.string.tv_date_of_birth)
            }

            "phone_number" -> {
                binding.etSurname.setText(MySharedPreferences.getPhone(requireContext()))
                binding.etSurname.hint = getString(R.string.tv_phone_number)
                binding.tvSubject.setText(R.string.tv_phone_number)
            }

            "email" -> {
                binding.etSurname.setText(MySharedPreferences.getEmail(requireContext()))
                binding.etSurname.hint = getString(R.string.tv_email)
                binding.tvSubject.setText(R.string.tv_email)
            }

            "address" -> {
                binding.etSurname.setText(MySharedPreferences.getAddress(requireContext()))
                binding.etSurname.hint = getString(R.string.tv_address)
                binding.tvSubject.setText(R.string.tv_address)
            }
        }
    }

    private fun setClickListener() {

        binding.cancelButton.setOnClickListener {
            dismiss()
        }

        binding.saveButton.setOnClickListener {

            when (string) {
                "lastname" -> {
                    val editText = try {
                        binding.etSurname.text.toString()
                    } catch (e: Exception) {
                        ""
                    }
                    onClick.onSaveClicked(editText)
                }

                "firstname" -> {
                    val editText = try {
                        binding.etSurname.text.toString()
                    } catch (e: Exception) {
                        ""
                    }
                    onClick.onSaveClicked(editText)
                }

                "middlename" -> {
                    val editText = try {
                        binding.etSurname.text.toString()
                    } catch (e: Exception) {
                        ""
                    }
                    onClick.onSaveClicked(editText)
                }

                "date_of_birth" -> {
                    val editText = try {
                        binding.etSurname.text.toString()
                    } catch (e: Exception) {
                        ""
                    }
                    onClick.onSaveClicked(editText)
                }

                "phone_number" -> {
                    binding.etSurname.inputType = InputType.TYPE_CLASS_PHONE
                    val editText = try {
                        binding.etSurname.text.toString()
                    } catch (e: Exception) {
                        ""
                    }
                    onClick.onSaveClicked(editText)
                }

                "email" -> {
                    val editText = try {
                        binding.etSurname.text.toString()
                    } catch (e: Exception) {
                        ""
                    }
                    onClick.onSaveClicked(editText)
                }

                "address" -> {
                    val editText = try {
                        binding.etSurname.text.toString()
                    } catch (e: Exception) {
                        ""
                    }
                    onClick.onSaveClicked(editText)
                }
            }


//            if (grade < 0 || grade > 100) {
//                toast(requireContext(), "Please, enter valid grade!")
//                return@setOnClickListener
//            }


            dismiss()
        }

    }

    interface SetOnClickListener {
        fun onSaveClicked(editText: String)
    }
}