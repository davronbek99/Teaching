package com.example.teaching.fragments.bottomSheet.language

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.teaching.R
import com.example.teaching.databinding.BottomSheetLanguageBinding
import com.example.teaching.utils.MySharedPreferences
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yariksoffice.lingver.Lingver

class LanguageBottomSheetDialogFragment(var onClick: SetOnClickListener) :
    BottomSheetDialogFragment() {
    private lateinit var binding: BottomSheetLanguageBinding
    private lateinit var sharedPreferences: MySharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetLanguageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = MySharedPreferences
        binding.apply {

            when {
                sharedPreferences.getLang(requireContext()) == "uz" -> {
                    ivUz.setImageResource(R.drawable.ic_icon_check)
                    ivRu.setImageResource(R.drawable.ic_box)
                    ivEng.setImageResource(R.drawable.ic_box)
                }
                sharedPreferences.getLang(requireContext()) == "ru" -> {
                    ivUz.setImageResource(R.drawable.ic_box)
                    ivRu.setImageResource(R.drawable.ic_icon_check)
                    ivEng.setImageResource(R.drawable.ic_box)
                }
                else -> {
                    ivUz.setImageResource(R.drawable.ic_box)
                    ivRu.setImageResource(R.drawable.ic_box)
                    ivEng.setImageResource(R.drawable.ic_icon_check)
                }
            }

            linearUz.setOnClickListener {
                sharedPreferences.setLang(requireContext(), "uz")
                Lingver.getInstance().setLocale(requireContext(), "uz")
                onClick.onClick("uz")
                dismiss()
            }

            linearRu.setOnClickListener {
                sharedPreferences.setLang(requireContext(), "ru")
                Lingver.getInstance().setLocale(requireContext(), "ru")
                onClick.onClick("ru")
                dismiss()
            }

            linearEng.setOnClickListener {
                sharedPreferences.setLang(requireContext(), "en")
                Lingver.getInstance().setLocale(requireContext(), "en")
                onClick.onClick("en")
                dismiss()
            }
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)

    }

    override fun getTheme() = R.style.CustomBottomSheetDialogTheme

    interface SetOnClickListener {
        fun onClick(lang: String)
    }
}