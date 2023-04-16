package com.example.teaching

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import com.example.newentranttest.extensions.statusBarColor
import com.example.teaching.databinding.ActivitySplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUp()
    }

    private fun setUp() {

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        statusBarColor(
            ResourcesCompat.getColor(resources, R.color.splash_color, theme),
            ResourcesCompat.getColor(resources, R.color.splash_color, theme), false
        )

        lifecycleScope.launch {
            delay(3000)
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun FragmentActivity.statusBarColor(
        @ColorInt statusBarColor: Int,
        @ColorInt navigationBarColor: Int,
        darkStatusBarTint: Boolean
    ) {
        val win: Window = (window).also {
            it.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            it.statusBarColor = statusBarColor
            it.navigationBarColor = navigationBarColor
        }

        val dec = win.decorView
        if (darkStatusBarTint) {
            dec.systemUiVisibility = dec.systemUiVisibility or
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            dec.systemUiVisibility = 0
        }
    }
}