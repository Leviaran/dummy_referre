package com.leviaran.dummyreferre

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class ReferreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val action : String? = intent?.action
        val data: Uri? = intent?.data

        Log.e("percobaan referre", data.toString())
    }
}