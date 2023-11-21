package com.example.mydemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.mydemo.R
import com.example.mydemo.basics.ScrollTestActivity
import com.example.mydemo.render.SimpleRenderActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val button = findViewById<Button>(R.id.simpleRender)
        button.setOnClickListener {
            val intent = Intent(this, SimpleRenderActivity::class.java)
            startActivity(intent)
        }

        val scrollButton = findViewById<Button>(R.id.scroll)
        scrollButton.setOnClickListener {
            val intent = Intent(this, ScrollTestActivity::class.java)
            startActivity(intent)
        }

    }
}