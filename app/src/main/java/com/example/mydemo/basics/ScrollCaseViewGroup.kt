package com.example.mydemo.basics

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Scroller
import android.widget.TextView
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.ColorUtils

class ScrollCaseViewGroup @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ViewGroup(context, attrs, defStyleAttr) {

    protected val mScroller = Scroller(context)

    init {
        init()
    }

    protected var mCurrentIndex = 0


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for (i in 0 until childCount) {
            val view = getChildAt(i)
            view.layout(getWidth() * i, 0, getWidth() * (i + 1), b - t);
        }
    }

    private fun init() {
        for (i in 0..5) {
            val child = TextView(context)
            var color = when (i % 3) {
                0 -> Color.White
                1 -> Color.Yellow
                2 -> Color.Green
                else -> Color.White
            }
            Log.i("ruixi ", "init: ")
//            child.setBackgroundColor()
            child.gravity = Gravity.CENTER
            child.setTextSize(TypedValue.COMPLEX_UNIT_SP, 46F)
            child.setTextColor(Color.Gray.value.toInt())
            child.text = "$i"
            addView(child)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        Log.i("ruixi ", "onMeasure: $childCount")
        for (i in 0 until childCount) {
            val view = getChildAt(i)
            view.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY))
        }
        setMeasuredDimension(width, height)
    }

    fun moveToIndex(targetIndex: Int) {
        Log.d(TAG, "moveToIndex: $targetIndex")
        if (!canMoveToIndex(targetIndex)) {
            return;
        }
        Log.d(TAG, "moveToIndex: $targetIndex")
//        mScroller.startScroll(scrollX, scrollY, targetIndex * getWidth() - getScrollX(), getScrollY())
        scrollTo(targetIndex * getWidth(), getScrollY());
        mCurrentIndex = targetIndex;
        invalidate();
    }

    fun stopMove() {
        if (!mScroller.isFinished) {
            val currentX = mScroller.currX
            val targetIndex = (currentX + getWidth() / 2) / getWidth()
            mScroller.abortAnimation()
            this.scrollTo(targetIndex * width, 0)
            mCurrentIndex = targetIndex
        }
    }

    fun canMoveToIndex(index: Int): Boolean {
        return index in 0 until CHILD_NUMBER
    }

    fun getCurrentIndex(): Int {
        return mCurrentIndex
    }
//
//    override fun computeScroll() {
//        val isNotFinished = mScroller.computeScrollOffset()
//        if (isNotFinished) {
//            scrollTo(mScroller.currX, mScroller.currY)
//            postInvalidate()
//        }
//
//    }

    companion object {
        val CHILD_NUMBER = 6
        private val mCurrentIndex = 0
        const val TAG = "ScrollCaseViewGroup"
    }

}