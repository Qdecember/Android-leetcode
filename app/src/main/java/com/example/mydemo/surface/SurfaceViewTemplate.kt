package com.example.mydemo.surface

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

class SurfaceViewTemplate @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : SurfaceView(context, attrs, defStyleAttr), SurfaceHolder.Callback, Runnable {

    private var mSurfaceHolder: SurfaceHolder? = null
    private var mCanvas: Canvas? = null
    private var mIsDrawing: Boolean = false

    init {
        initView()
    }

    private fun initView() {
        mSurfaceHolder = holder
        mSurfaceHolder?.addCallback(this)
        isFocusable = true
        keepScreenOn = true
        isFocusableInTouchMode = true
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        mIsDrawing = true
        Thread(this).start()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        mIsDrawing = false
    }

    override fun run() {
        while (mIsDrawing) {
            doSomething()
        }
    }

    private fun doSomething() {
        try {
            mCanvas = mSurfaceHolder?.lockCanvas()
            mCanvas?.drawColor(Color.Red.toArgb())
        } catch (e: Exception) {
            e.stackTrace
        } finally {
            if (mCanvas != null) {
                mSurfaceHolder?.unlockCanvasAndPost(mCanvas)
            }
        }
    }


}