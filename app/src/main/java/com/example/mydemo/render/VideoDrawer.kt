package com.example.mydemo.render

import android.graphics.SurfaceTexture
import android.opengl.GLES11Ext
import android.opengl.GLES20
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer


class VideoDrawer: IDrawer {

    // 顶点坐标
    private val mVertexCoors = floatArrayOf(
        -1f, -1f,
        1f, -1f,
        -1f, 1f,
        1f, 1f
    )

    // 纹理坐标
    private val mTextureCoors = floatArrayOf(
        0f, 1f,
        1f, 1f,
        0f, 0f,
        1f, 0f
    )

    private var mTextureId: Int = -1

    private var mProgramId: Int = -1

    private var mVertexPosHandler: Int = -1

    private var mTexturePosHandler: Int = -1

    private var mTextureHandler: Int = -1

    private lateinit var mVertexBuffer: FloatBuffer
    private lateinit var mTextureBuffer: FloatBuffer

    private var mSurfaceTexture: SurfaceTexture? = null

    init {
        initPos()
    }

    private fun initPos() {
        val bb = ByteBuffer.allocateDirect(mVertexCoors.size * 4)
            .order(ByteOrder.nativeOrder())
            .asFloatBuffer()
            .put(mVertexCoors)
            .position(0)

        val cc = ByteBuffer.allocateDirect(mTextureCoors.size * 4)
            .order(ByteOrder.nativeOrder())
            .asFloatBuffer()
            .put(mTextureCoors)
            .position(0)
    }


    override fun draw() {
        createGLPro() // 创建opengl 程序
        activeTexture() // 激活纹理
        doDraw() // 开始绘制

    }

    override fun setTextureID(id: Int) {
        mTextureId = id
        mSurfaceTexture = SurfaceTexture(id)
        mSurfaceTexture?.let {
            getSurfaceTexture?.invoke(it)
        }
    }

    override fun release() {
        GLES20.glDisableVertexAttribArray(mVertexPosHandler)
        GLES20.glDisableVertexAttribArray(mTexturePosHandler)
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0)
        GLES20.glDeleteTextures(1, intArrayOf(mTextureId), 0)
        GLES20.glDeleteProgram(mProgramId)
    }

    private fun createGLPro() {
        if (mProgramId == -1) {
            val vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, getVertexShader())
            val fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, getFragmentShader())

            mProgramId = GLES20.glCreateProgram()
            GLES20.glAttachShader(mProgramId, vertexShader)
            GLES20.glAttachShader(mProgramId, fragmentShader)
            GLES20.glLinkProgram(mProgramId)

            mVertexPosHandler = GLES20.glGetAttribLocation(mProgramId, "aPosition")
            mTexturePosHandler = GLES20.glGetAttribLocation(mProgramId, "aCoordinate")
            mTextureHandler = GLES20.glGetUniformLocation(mProgramId, "uTexture")
        }

        GLES20.glUseProgram(mProgramId)
    }

    private fun activeTexture() {
        // 激活指定纹理单元
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0)
        // 绑定纹理id 到纹理单元
        GLES20.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, mTextureId)
        // 将纹理单元传递到着色器里
        GLES20.glUniform1i(mTextureHandler, 0)
        //配置边缘过渡参数
        GLES20.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR.toFloat())
        GLES20.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR.toFloat())
        GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE)
        GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE)
    }

    private fun doDraw() {
        GLES20.glEnableVertexAttribArray(mVertexPosHandler)
        GLES20.glEnableVertexAttribArray(mTexturePosHandler)

        GLES20.glVertexAttribPointer(mVertexPosHandler, 2, GLES20.GL_FLOAT,false, 0, mVertexBuffer)
        GLES20.glVertexAttribPointer(mTexturePosHandler, 2, GLES20.GL_FLOAT, false, 0, mTextureBuffer)

        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0,4)
    }

    private fun updateTexture() {
        mSurfaceTexture?.updateTexImage()
    }

    override fun getSurfaceTexture(callback: (st: SurfaceTexture) -> Unit) {
        getSurfaceTexture = callback
    }

    private var getSurfaceTexture: ((SurfaceTexture) -> Unit)? = null

    private fun getVertexShader(): String {
        return "attribute vec4 aPosition" +
                "attribute vec2 aCoordinate" +
                "varying vec2 vCoordinate"+
                "void main() {" +
                "    gl_Position = aPosition;" +
                "    vCoordinate = aCoordinate;" +
                "}"
    }

    private fun getFragmentShader(): String {
        // 使用android 的扩展纹理
        return "#extension GL_OES_EGL_image_external : require\n" +
                "precision mediump float;" +
                "varying vec2 vCoordinate;" +
                "uniform samplerExternalOES uTexture;" +
                "void main() {" +
                "  gl_FragColor=texture2D(uTexture, vCoordinate);" +
                "}"
    }

    private fun loadShader(type: Int, shaderCoder: String): Int {
        val shader = GLES20.glCreateShader(type)
        GLES20.glShaderSource(type, shaderCoder)
        GLES20.glCompileShader(shader)
        return shader
    }
}