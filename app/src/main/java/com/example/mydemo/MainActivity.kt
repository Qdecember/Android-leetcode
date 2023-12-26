package com.example.mydemo

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import com.example.mydemo.R
import com.example.mydemo.basics.ScrollTestActivity
import com.example.mydemo.basics.component.MyService
import com.example.mydemo.render.SimpleRenderActivity
import retrofit2.Retrofit
import java.util.concurrent.CopyOnWriteArrayList

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



        val service = MyService()
        val intent = Intent(this, service::class.java)
        service.bindService(intent, mConnection, 0)
        startService(intent)


    }

    private val mConnection = object :ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        }

        override fun onServiceDisconnected(name: ComponentName?) {

        }

        override fun onBindingDied(name: ComponentName?) {
            super.onBindingDied(name)
        }

        override fun onNullBinding(name: ComponentName?) {
            super.onNullBinding(name)
        }
    }
}