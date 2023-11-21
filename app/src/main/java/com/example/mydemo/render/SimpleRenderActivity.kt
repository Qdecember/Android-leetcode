package com.example.mydemo.render

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.opengl.GLSurfaceView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore.Video
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.Surface
import androidx.core.content.ContextCompat
import com.example.mydemo.R
import java.util.concurrent.Executors

class SimpleRenderActivity : AppCompatActivity() {

    lateinit var drawer: IDrawer

    val path = Environment.getExternalStorageDirectory().absolutePath + "/chuyouya.mp4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_render)

        val bitmap = BitmapFactory.decodeResource(this.resources, R.mipmap.live_cover)

//        drawer = TriangleDrawer()
//        drawer = BitmapDrawer(-1, bitmap)
        drawer = VideoDrawer()
        drawer.getSurfaceTexture {

        }
        val glSurfaceView = findViewById<GLSurfaceView>(R.id.glSurfaceView)
        glSurfaceView.setEGLContextClientVersion(2)
        glSurfaceView.setRenderer(SimpleRender(drawer = drawer))

    }

    private fun initPlayer(surface: Surface) {
        val threadPool = Executors.newFixedThreadPool(10)
//        val videoDecoder = Video
    }

    override fun onDestroy() {
        super.onDestroy()
        drawer.release()
    }
}