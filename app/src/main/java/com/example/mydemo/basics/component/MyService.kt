package com.example.mydemo.basics.component

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

/**
 * service
 */
class MyService: Service() {

    private val mBinder by lazy {
        MyBinder()
    }
    override fun onBind(intent: Intent?): IBinder? {
        return MyBinder()
    }



    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
    }

    class MyBinder: Binder() {
        fun doSomething() {
            Log.d("MyService", "my binder do something")
        }
    }
}