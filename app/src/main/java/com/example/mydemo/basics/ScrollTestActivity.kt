package com.example.mydemo.basics

import android.app.IntentService
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mydemo.R
import com.example.mydemo.basics.component.MyService


class ScrollTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll_test)
//
//        val containerViewGroup = findViewById<ScrollCaseViewGroup>(R.id.container_viewgroup)
//        val leftButton =  findViewById<Button>(R.id.button_left);
//        val rightButton =  findViewById<Button>(R.id.button_right);
//
//        val onButtonClickListener =
//            View.OnClickListener { v ->
//                val currentIndex: Int = containerViewGroup.getCurrentIndex()
//                var targetIndex = currentIndex
//                when (v.id) {
//                    R.id.button_left -> targetIndex = currentIndex - 1
//                    R.id.button_right -> targetIndex = currentIndex + 1
//                }
//                containerViewGroup.moveToIndex(targetIndex)
//                Toast.makeText(this, "buttonclick", Toast.LENGTH_SHORT).show()
//            }
//
//        leftButton.setOnClickListener(onButtonClickListener)
//        rightButton.setOnClickListener(onButtonClickListener)

    }

}