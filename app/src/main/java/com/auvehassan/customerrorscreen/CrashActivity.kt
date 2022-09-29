package com.auvehassan.customerrorscreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.auvehassan.customerrorscreen.databinding.ActivityCrashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CrashActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCrashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrashBinding.inflate(layoutInflater)
        ApplicationExceptionHandler.getThrowableFromIntent(intent).let {
            Log.e(TAG, "Error: $it")
        }
        setOnClickListeners()
        setContentView(binding.root)
    }

    private fun setOnClickListeners() {
        binding.btnReport.setOnClickListener {
            lifecycleScope.launch {
                binding.btnReport.isEnabled = false
                binding.btnReport.text = "Reporting..."
                delay(2000)
                binding.btnReport.text = "Reported."
                delay(1000)
                finishAffinity()
            }
        }

        binding.bRestartApp.setOnClickListener {
            finishAffinity()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    companion object {
        private const val TAG = "CrashActivity"
    }

}