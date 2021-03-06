package com.tripaza.tripaza.ui.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.tripaza.tripaza.databinding.ActivityOnBoardingBinding
import com.tripaza.tripaza.helper.HelperTools
import com.tripaza.tripaza.ui.navigation.MainNavigationActivity
import com.tripaza.tripaza.helper.PreferencesHelper
import com.tripaza.tripaza.ui.registration.MainActivity

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding
    private lateinit var preferences: PreferencesHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        
        preferences = PreferencesHelper(this)
        checkIsUserAlreadyLoggedIn()
        HelperTools.setUpDarkMode(this)
        fullscreen()
        setContentView(binding.root)
        
        binding.button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun checkIsUserAlreadyLoggedIn() {
        if (preferences.getUser().id.isNotEmpty()){
            val intent = Intent(this, MainNavigationActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun fullscreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()
    }
}
