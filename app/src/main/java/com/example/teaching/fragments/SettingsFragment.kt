package com.example.teaching.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.newentranttest.extensions.setBackPressed
import com.example.newentranttest.ui.bottomSheetFragments.theme.ThemeBottomSheet
import com.example.teaching.R
import com.example.teaching.databinding.FragmentSettingsBinding
import com.example.teaching.fragments.bottomSheet.language.LanguageBottomSheetDialogFragment
import com.example.teaching.utils.MySharedPreferences
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yariksoffice.lingver.Lingver
import java.util.*

class SettingsFragment : Fragment(), LanguageBottomSheetDialogFragment.SetOnClickListener {

    private lateinit var binding: FragmentSettingsBinding
    private lateinit var languageBottomSheetDialogFragment: LanguageBottomSheetDialogFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setBackPressed()
        setClickListeners()
    }

    private fun init() {

        val nightMode = MySharedPreferences.getNightMode(requireContext())

        binding.tvTheme.text = when (nightMode) {
            "day" -> getString(R.string.day)
            "auto" -> getString(R.string.auto)
            else -> getString(R.string.night)
        }
    }

    private fun setClickListeners() {

        binding.apply {
            linearComplaints.setOnClickListener {

            }

            binding.myProfileBox.setOnClickListener {
                findNavController().navigate(R.id.to_my_profile_fragment)
            }

            linearLanguage.setOnClickListener {
                languageBottomSheetDialogFragment =
                    LanguageBottomSheetDialogFragment(this@SettingsFragment)
                languageBottomSheetDialogFragment.show(childFragmentManager, "")
            }

            linearTheme.setOnClickListener {
                val nightMode = MySharedPreferences.getNightMode(requireContext())
                val dialog = ThemeBottomSheet(
                    requireContext(),
                    nightMode,
                    object : ThemeBottomSheet.OnItemClickListener {
                        override fun onThemeSelected(nightMode: String) {
                            MySharedPreferences.setNightMode(requireContext(), nightMode)

                            binding.tvTheme.text = when (nightMode) {
                                "day" -> getString(R.string.day)
                                "auto" -> getString(R.string.auto)
                                else -> getString(R.string.night)
                            }
                            requireActivity().recreate()
                        }
                    })
                dialog.show()
            }
        }
    }

    override fun onClick(lang: String) {
        Lingver.getInstance().setLocale(requireContext(), lang)
        MySharedPreferences.setLang(requireContext(), lang)

        changeTvs()
    }

    private fun changeTvs() {
        binding.tvSetting.text = getString(R.string.tv_setting)
        binding.tvLanguageTitle.text = getString(R.string.tv_language)
        binding.tvComplaints.text = getString(R.string.tv_send_complaints)
        binding.tvLanguage.text = getString(R.string.tv_language)

        // update bottomNav view language
        val navigationView =
            requireActivity().findViewById<View>(R.id.bottomNavigationView) as BottomNavigationView

        navigationView.menu.findItem(R.id.homeFragment).title =
            resources.getString(R.string.tv_sciences)
        navigationView.menu.findItem(R.id.examFragment).title =
            resources.getString(R.string.str_exam)
        navigationView.menu.findItem(R.id.settingsFragment).title =
            resources.getString(R.string.tv_setting)

    }

}