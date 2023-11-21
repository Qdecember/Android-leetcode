package com.example.mydemo.decode

import android.media.MediaCodec
import android.media.MediaFormat
import android.util.Log
import java.io.File
import java.nio.ByteBuffer

abstract class BaseDecoder(private val mFilePath: String): IDecoder {
//    private val TAG = "BaseDecoder"
//    // 线程相关
//    /**
//     * 解码器是否在运行
//     */
//    private var mIsRunning = true
//
//    /**
//     * 线程等待锁
//     */
//    private val mLock = Object()
//
//    /**
//     * 是否可以进入解码
//     */
//    private var mReadyForDecode = false
//
//    //---------------解码相关-----------------------
//    /**
//     * 音视频数据读取器
//     */
//    protected var mCodec: MediaCodec? = null
//
//    /**
//     * 音视频数据读取器
//     */
//    protected var mExtractor: IExtractor? = null
//
//    /**
//     * 解码输入缓存区
//     */
//    protected var mInputBuffers: Array<ByteBuffer>? = null
//
//    /**
//     * 解码输出缓存区
//     */
//    protected var mOutputBuffers: Array<ByteBuffer>? = null
//
//    /**
//     * 解码数据信息
//     */
//    private var mBufferInfo = MediaCodec.BufferInfo()
//
//    private var mState = DecodeState.STOP
//
//    private var mStateListener: IDecodeStateListener? = null
//
//    /**
//     * 流数据是否结束
//     */
//    private var mIsEOS = false
//
//    protected var mVideoWidth = 0
//
//    protected var mVideoHeight = 0
//
//    private var mDuration: Long = 0
//
//    private var mStartPos: Long = 0
//
//    private var mEndPos: Long = 0
//
//    /**
//     * 开始解码时间，用于音视频同步
//     */
//    private var mStartTimeForSync = -1L
//
//    private var mSyncRender = true // 是否需要音视频渲染同步
//
//    override fun run() {
//        if (mState == DecodeState.STOP) {
//            mState = DecodeState.START
//        }
//        mStateListener?.decoderPrepare(this)
//
//        // todo init
//        Log.i(TAG, "run: ")
//        try {
//            while (mIsRunning) {
//                if (mState != DecodeState.START &&
//                    mState != DecodeState.DECODING &&
//                    mState != DecodeState.SEEKING) {
//                    Log.i(TAG, "进入等待 $mState")
//                    // todo wait decode
////                    mStartTimeForSync =
//                }
//
//                if (!mIsRunning ||
//                    mState == DecodeState.STOP) {
//                    mIsRunning = false
//                    break
//                }
//
//                if (mStartTimeForSync == -1L) {
//                    mStartTimeForSync = System.currentTimeMillis()
//                }
//
//                // 如果数据没有解码完毕，将数据推入解码器解码
//                if (!mIsEOS) {
//                    //【解码步骤：2. 见数据压入解码器输入缓冲】
//                    mIsEOS = pushBufferToDecoder()
//                }
//
//                // 【解码步骤：3. 将解码好的数据从缓冲区拉取出来】
//                val index = pullBufferFromDecoder()
//                if (index >= 0) {
//                    // ---------【音视频同步】-------------
//                    if (mSyncRender && mState == DecodeState.DECODING) {
//                        sleepRender()
//                    }
//                    // 【解码步骤：4. 渲染】
//                    if (mSyncRender) {
//                        render(mOutputBuffers!![index], mBufferInfo)
//                    }
//
//                    // 将解码数据传递出去
//                    val frame = Frame()
//                    frame.buffer = mOutputBuffers!![index]
//                    frame.setBufferInfo(mBufferInfo)
//                    mStateListener?.decodeOneFrame(this, frame)
//
//                    //【解码步骤：5. 释放输出缓冲】
//                    mCodec?.releaseOutputBuffer(index, true)
//
//                    if (mState == DecodeState.START) {
//                        mState = DecodeState.PAUSE
//                    }
//                }
//                //【解码步骤：6. 判断解码是否完成】
//                if (mBufferInfo.flags ==  MediaCodec.BUFFER_FLAG_END_OF_STREAM) {
//                    Log.i(TAG, "解码结束")
//                    mState = DecodeState.FINISH
//                    mStateListener?.decoderFinish(this)
//                }
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        } finally {
//
//        }
//    }
//
//    private fun init(): Boolean {
//        if (mFilePath.isEmpty() || !File(mFilePath).exists()) {
//            Log.w(TAG, "文件路径为空")
//            mStateListener?.decoderError(this, "文件路径为空")
//            return false
//        }
//
//
//    }
//
//    private fun initParams(): Boolean {
//        try {
//            val format = mExtractor!!.getFormat()!!
//            mDuration = format.getLong(MediaFormat.KEY_DURATION) / 1000
//            if (mEndPos == 0L) mEndPos = mDuration
//
//
//        } catch (e: Exception) {
//            return false
//        }
//        return true
//    }
//
//    private fun initCodec(): Boolean {
//        try {
//            val type = mExtractor!!.getFormat()!!.getString(MediaFormat.KEY_MIME)!!
//            mCodec = MediaCodec.createDecoderByType(type)
//
//        }
//    }
//
//    private fun pullBufferFromDecoder(): Int {
//        // 查询是否有解码完成的数据，index >=0 时，表示数据有效，并且index为缓冲区索引
//        var index = mCodec!!.dequeueOutputBuffer(mBufferInfo, 1000)
//
//    }
//
//    private fun sleepRender() {
//        val passTime = System.currentTimeMillis() - mStartTimeForSync
//        val curTime = getCurTimeStamp()
//        if (curTime > passTime) {
//            Thread.sleep(curTime - passTime)
//        }
//    }
//
//    private fun release() {
//        try {
//            Log.i(TAG,  "解码停止，释放解码器")
//            mState = DecodeState.STOP
//            mIsEOS = false
//            mExtractor?.stop()
//            mCodec?.stop()
//            mCodec?.release()
//            mStateListener?.decoderDestroy(this)
//        } catch (e: Exception) {
//
//        }
//    }
//
//
//    /**
//     * 解码线程进入等待
//     */
//    private fun waitDecode() {
//        try {
//            if (mState == DecodeState.PAUSE) {
//                mStateListener?.decoderPause(this)
//            }
//            synchronized(mLock) {
//                mLock.wait()
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    /**
//     * 通知解码线程继续运行
//     */
//    protected fun notifyDecode() {
//        synchronized(mLock) {
//            mLock.notify()
//        }
//
//        if (mState == DecodeState.DECODING) {
//            mStateListener?.decoderRunning(this)
//        }
//    }
//
//    override fun pause() {
//        mState = DecodeState.DECODING
//    }
//
//    override fun goOn() {
//        mState = DecodeState.DECODING
//        notifyDecode()
//    }
//
//    override fun stop() {
//        mState = DecodeState.STOP
//        mIsRunning = false
//    }
//
//    override fun isDecoding(): Boolean {
//        return mState == DecodeState.DECODING
//    }
//
//    override fun isStop(): Boolean {
//        return mState == DecodeState.STOP
//    }
//
//    override fun setStateListener(l: IDecodeStateListener) {
//       mStateListener = l
//    }
//
//    override fun getWidth(): Int {
//        return mVideoWidth
//    }
//
//    override fun getHeight(): Int {
//        return mVideoHeight
//    }
//
//    override fun getDuration(): Long {
//        return mDuration
//    }
//
//    override fun getRotationAngle(): Int {
//        return 0
//    }
//
//    override fun getMediaFormat(): MediaFormat {
//       return mExtractor?.getFormat()!!
//    }
//
//    override fun getTrack(): Int {
//        return 0
//    }
//
//    override fun getFilePath(): String {
//        return mFilePath
//    }
//
//    override fun withoutSync(): IDecoder {
//        mSyncRender = false
//        return this
//    }
//
//    /**
//     * 检查子类参数
//     */
//    abstract fun check(): Boolean
//
//    abstract fun initExtractor(path: String): IExtractor
//
//    /**
//     * 初始化子类自己特有的参数
//     */
//    abstract fun initSpecParams(format: MediaFormat)
//
//    /**
//     * 配置解码器
//     */
//    abstract fun configCodec(codec: MediaCodec, format: MediaFormat): Boolean
//
//    /**
//     * 初始化渲染器
//     */
//    abstract fun initRender(): Boolean
//
//    /**
//     * 渲染
//     */
//    abstract fun render(outputBuffer: ByteBuffer, )
//    /**
//     * 结束解码
//     */
//    abstract fun doneDecode()

}