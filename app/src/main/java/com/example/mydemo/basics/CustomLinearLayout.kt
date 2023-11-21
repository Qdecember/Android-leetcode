package com.example.mydemo.basics

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

class CustomLinearLayout @JvmOverloads constructor(
    context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attributeSet, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(100, 100)

    }


}