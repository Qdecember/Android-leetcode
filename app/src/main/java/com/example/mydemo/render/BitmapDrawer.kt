package com.example.mydemo.render

import android.graphics.Bitmap
import android.opengl.GLES20
import android.opengl.GLUtils
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

/**
 * 绘制流程
 * 通过GLSurfaceView配置OpenGL ES版本，指定Render
 * 实现GLSurfaceView.Renderer，复写暴露的方法，并配置OpenGL显示窗口，清屏
 * 创建纹理ID
 * 配置好顶点坐标和纹理坐标
 * 初始化坐标变换矩阵
 * 初始化OpenGL程序，并编译、链接顶点着色和片段着色器，获取GLSL中的变量属性
 * 激活纹理单元，绑定纹理ID，配置纹理过滤模式和环绕方式
 * 绑定纹理（如将bitmap绑定给纹理）
 * 启动绘制
 *
 * 作者：开发的猫
 * 链接：https://www.jianshu.com/p/2158d4aec142
 * 来源：简书
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 */

class BitmapDrawer(private var mTextureId: Int, private val mBitmap: Bitmap): IDrawer {

    private val mVertexCoors = floatArrayOf(
        -1f, -1f,
        1f, -1f,
        -1f, 1f,
        1f, 1f
    )

    private val mTextureCoors = floatArrayOf(
        0f, 1f,
        1f, 1f,
        0f, 0f,
        1f, 0f
    )

    private var mProgramId: Int = -1

    // 顶点坐标接收者
    private var mVertexPosHandler: Int = -1

    // 纹理坐标接收者
    private var mTexturePosHandler: Int = -1

    // 纹理接收者
    private var mTextureHandler: Int = -1


    private lateinit var mVertexBuffer: FloatBuffer
    private lateinit var mTextureBuffer: FloatBuffer

    init {
        initPos()
    }

    private fun initPos() {
        val bb = ByteBuffer.allocateDirect(mVertexCoors.size * 4)
        bb.order(ByteOrder.nativeOrder())
        mVertexBuffer = bb.asFloatBuffer()
        mVertexBuffer.put(mVertexCoors)
        mVertexBuffer.position(0)

        val cc = ByteBuffer.allocateDirect(mTextureCoors.size * 4)
        cc.order(ByteOrder.nativeOrder())
        mTextureBuffer = cc.asFloatBuffer()
        mTextureBuffer.put(mTextureCoors)
        mTextureBuffer.position(0)
    }


    override fun draw() {
        if (mTextureId != -1) {
            // 创建、编译并启动OpenGL着色器
            createGlPrg()
            // 激活并绑定纹理单元
            activateTexture()
            // 绑定图片到纹理单元
            bindBitmapToTexture()
            // 开始渲染绘制
            doDraw()
        }

    }

    override fun setTextureID(id: Int) {
        mTextureId = id
    }

    override fun release() {
        GLES20.glDisableVertexAttribArray(mVertexPosHandler)
        GLES20.glDisableVertexAttribArray(mTexturePosHandler)
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0)
        GLES20.glDeleteTextures(1, intArrayOf(mTextureId), 0)
        GLES20.glDeleteProgram(mProgramId)
    }

    fun createGlPrg() {
        if (mProgramId == -1) {
            val vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, getVertexShader())
            val fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, getFragmentShader())

            // 创建opengl es 程序
            mProgramId = GLES20.glCreateProgram()

            //
            GLES20.glAttachShader(mProgramId, vertexShader)
            GLES20.glAttachShader(mProgramId, fragmentShader)

            GLES20.glLinkProgram(mProgramId)

            mVertexPosHandler = GLES20.glGetAttribLocation(mProgramId, "aPosition")
            mTexturePosHandler = GLES20.glGetAttribLocation(mProgramId, "aCoordinate")
            mTextureHandler = GLES20.glGetUniformLocation(mProgramId, "uTexture")
        }
        GLES20.glUseProgram(mProgramId)
    }

    private fun doDraw() {
        // 启用顶点句柄
        GLES20.glEnableVertexAttribArray(mVertexPosHandler)
        GLES20.glEnableVertexAttribArray(mTexturePosHandler)

        GLES20.glVertexAttribPointer(mVertexPosHandler, 2, GLES20.GL_FLOAT, false, 0, mVertexBuffer)
        GLES20.glVertexAttribPointer(mTexturePosHandler, 2, GLES20.GL_FLOAT, false, 0, mTextureBuffer)
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4)
    }


    private fun activateTexture() {
        // 激活指定纹理单元
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0) // 纹理单元
        // 绑定纹理ID 到纹理单元
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureId) // 纹理id 绑定纹理
        // 将激活的纹理单元传递到着色器里面
        GLES20.glUniform1i(mTextureHandler, 0) // 第二个参数索引需要和纹理单元索引保持一致。
        // 配置边缘过度参数
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR.toFloat())
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR.toFloat())
        // 配置纹理环绕方式
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE)
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE)

    }

    private fun bindBitmapToTexture() {
        if (!mBitmap.isRecycled) {
            GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, mBitmap, 0)
        }
    }


    /**
     * 限定符号
     * attribute: 一般用于哥哥顶点各不相同的量。 比如顶点颜色、坐标等
     * uniform：一般用于对于3D物体中所有顶点都相同等量。比如光源位置，统一变换矩阵
     * varying：表示易变量，一般用于顶点着色器传递到片元着色器等量
     * const：常量
     */
    private fun getVertexShader(): String {
                // 顶点坐标
        return "attribute vec4 aPosition;" +
                // 纹理坐标
                "attribute vec2 aCoordinate;" +
                // 用于传递纹理坐标给片元着色器,命名和片元着色器一样
                "varying vec2 vCoordinate;" +
                "void main() {" +
                "  gl_Position = aPosition;" +
                "  vCoordinate = aCoordinate;" +
                "}"
    }

    private fun getFragmentShader(): String {
                // 配置float 精度，使用了float数据一定要配置：lowp(低)/mediump(中)/highp(高)
        return "precision mediump float;" +
                // java 传递进入来等纹理单元
                "uniform sampler2D uTexture;" +
                // 从顶点着色器 传递进来的纹理坐标
                "varying vec2 vCoordinate;" +
                "void main() {" +
                // 根据纹理坐标，从纹理单元中取色
                "  vec4 color = texture2D(uTexture, vCoordinate);" +
                "  gl_FragColor = color;" +
                "}"
    }

    private fun loadShader(type: Int, shaderCode: String): Int {
        // 根据type 创建顶点着色器 或者片元着色器
        val shader = GLES20.glCreateShader(type)
        // 将资源加入到着色器中，并编译
        GLES20.glShaderSource(shader,shaderCode)
        GLES20.glCompileShader(shader)
        return shader
    }

}