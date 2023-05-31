package com.example.teaching

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.newentranttest.extensions.getAutoNightMode
import com.example.newentranttest.extensions.statusBarColor
import com.example.teaching.databinding.ActivityMainBinding
import com.example.teaching.utils.MySharedPreferences
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var sharedPreferences: MySharedPreferences
    private lateinit var navController: NavController
    private var isTheme = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        init()
        setBottomNav()
        setNavController()
        setUpUI()
    }

    private fun init() {
        sharedPreferences = MySharedPreferences
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun setBottomNav() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        binding.bottomNavigationView.setOnItemReselectedListener {

        }

        setupWithNavController(binding.bottomNavigationView, navController)
    }

    private fun setUpUI() {
        //change status bar color
        statusBarColor(
            ResourcesCompat.getColor(resources, R.color.home_color, theme),
            ResourcesCompat.getColor(resources, R.color.home_color, theme), true
        )

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> {
                    //change status bar color
                    statusBarColor(
                        ResourcesCompat.getColor(resources, R.color.home_color, theme),
                        ResourcesCompat.getColor(resources, R.color.black, theme), true
                    )
                }

                R.id.resultFragment -> {
                    statusBarColor(
                        ResourcesCompat.getColor(resources, R.color.green, theme),
                        ResourcesCompat.getColor(resources, R.color.white, theme), true
                    )
                }

                else -> {
                    //change status bar color
                    statusBarColor(
                        ResourcesCompat.getColor(resources, R.color.white, theme),
                        ResourcesCompat.getColor(resources, R.color.white, theme), true
                    )
                }
            }
        }
    }

    private fun setNavController() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment, R.id.examFragment,
                R.id.settingsFragment -> {
                    binding.coordinator.visibility = View.VISIBLE
                }
                else -> {
                    binding.coordinator.visibility = View.GONE
                }
            }
        }
    }

    //change statusBar color different fragments...
    private fun statusBarChangeGreenDifferent() {
        if (!isTheme) {
            statusBarColor(
                ResourcesCompat.getColor(resources, R.color.green, theme),
                ResourcesCompat.getColor(resources, R.color.green, theme),
                false
            )
        } else {
            statusBarColor(
                ResourcesCompat.getColor(resources, R.color.statusBarNight, theme),
                ResourcesCompat.getColor(resources, R.color.statusBarNight, theme),
                false
            )

        }


    }

    //change StatusBar color different fragments...
    private fun statusBarChangeWhiteDifferent() {
        if (!isTheme) {
            statusBarColor(
                ResourcesCompat.getColor(resources, R.color.white, theme),
                ResourcesCompat.getColor(resources, R.color.white, theme),
                true
            )
        } else {
            statusBarColor(
                ResourcesCompat.getColor(resources, R.color.statusBarNight, theme),
                ResourcesCompat.getColor(resources, R.color.statusBarNight, theme),
                false
            )
        }
    }

    //changeStatusBar color....
    private fun statusBarColorChange() {
        if (!isTheme) {
            statusBarColor(
                ResourcesCompat.getColor(resources, R.color.white, theme),
                ResourcesCompat.getColor(resources, R.color.white, theme),
                true
            )
        } else {
            statusBarColor(
                ResourcesCompat.getColor(resources, R.color.statusBarNight, theme),
                ResourcesCompat.getColor(resources, R.color.statusBarNight, theme),
                false
            )
        }

    }

    private fun adjustFontScale(configuration: Configuration) {
        if (configuration.fontScale > 1f || configuration.fontScale < 1f) {
            configuration.fontScale = 1f
            val metrics = resources.displayMetrics
            val wm = getSystemService(WINDOW_SERVICE) as WindowManager
            wm.defaultDisplay.getMetrics(metrics)
            metrics.scaledDensity = configuration.fontScale * metrics.density
            baseContext.resources.updateConfiguration(configuration, metrics)
        }
    }

    private fun setTheme() {
        when (MySharedPreferences.getNightMode(this)) {
            "day" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            "night" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "auto" -> {
                setListeners()

                when (getAutoNightMode()) {
                    "day" -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    }
                    "night" -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    }
                }
            }
        }
        changeStatusBarColor()
    }

    private fun setListeners() {
        val intentFilter = IntentFilter(Intent.ACTION_TIME_TICK)
        registerReceiver(timerReceiver, intentFilter)
    }

    private val timerReceiver = object : BroadcastReceiver() {
        @SuppressLint("SimpleDateFormat")
        override fun onReceive(p0: Context?, p1: Intent?) {
            if (p1 != null) {
                if (p1.action == Intent.ACTION_TIME_TICK) {
                    val time = Date()
                    val dH = SimpleDateFormat("HH")
                    val dM = SimpleDateFormat("mm")
                    val hour = dH.format(time).toInt()
                    val minute = dM.format(time).toInt()
                    val nightMode = MySharedPreferences.getNightMode(this@MainActivity)

                    if (nightMode == "auto") {
                        if (hour == 18 || hour == 6) {
                            if (minute == 0) {
                                finishAffinity()
                                val intent =
                                    Intent(this@MainActivity, SplashActivity::class.java)
                                startActivity(intent)
                            }
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("NewApi")
    private fun changeStatusBarColor() {

        var nightMode = MySharedPreferences.getNightMode(this)

        if (nightMode == "auto") nightMode = getAutoNightMode()

        statusBarColor(
            ResourcesCompat.getColor(resources, R.color.status_bar_color_light, theme),
            ResourcesCompat.getColor(resources, R.color.status_bar_color_light, theme),
            nightMode != "night"
        )
    }

}