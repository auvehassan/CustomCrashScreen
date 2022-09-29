package com.auvehassan.customerrorscreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.auvehassan.customerrorscreen.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.btnThrowError.setOnClickListener { throw Error("Hello, I'm the crash!") }
        setContentView(binding.root)
    }
}