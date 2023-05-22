package com.thaer.core

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

open class CoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_core)
    }
}