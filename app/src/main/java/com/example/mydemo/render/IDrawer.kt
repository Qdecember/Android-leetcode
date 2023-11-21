package com.example.mydemo.render

import android.graphics.SurfaceTexture
import android.view.Surface

interface IDrawer {
    fun draw()

    fun setTextureID(id: Int)

    fun release()

    fun getSurfaceTexture(callback: (st: SurfaceTexture) -> Unit) {}
}