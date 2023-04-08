package com.auvehassan.customerrorscreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

import com.auvehassan.customerrorscreen.databinding.ActivityCrashBinding

class CrashActivity : AppCompatActivity() {

    private var errorStackTrace: String?=null
    private lateinit var binding: ActivityCrashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate")
        binding = ActivityCrashBinding.inflate(layoutInflater)

        errorStackTrace = intent.getStringExtra(ApplicationExceptionHandler.INTENT_DATA_NAME)
        setOnClickListeners()
        setContentView(binding.root)
    }

    private fun setOnClickListeners() {
        binding.btnReport.setOnClickListener { openContactDialog() }
        binding.ibClose.setOnClickListener { finishAffinity() }
        binding.bRestartApp.setOnClickListener {
            finishAffinity()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    /* Contact user community dialog*/
    private fun openContactDialog() {
        Log.d(TAG, "errorStackTrace: $errorStackTrace")

    }

    companion object {
        private const val TAG = "CrashActivity"
    }

}
