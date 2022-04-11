package com.example.kmf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.kmf.ui.BottomSheetFragment
import com.example.kmf.ui.UserInfoFragment
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        BottomSheetFragment().show(supportFragmentManager, "tag")

        findViewById<Button>(R.id.showProfile).setOnClickListener {
            UserInfoFragment().show(supportFragmentManager, "dialog")
        }
    }
}