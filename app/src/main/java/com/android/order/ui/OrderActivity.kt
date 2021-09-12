package com.android.order.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.order.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
    }
}
