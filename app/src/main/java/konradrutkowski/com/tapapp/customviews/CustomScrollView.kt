package konradrutkowski.com.tapapp.customviews

import android.content.Context
import android.util.AttributeSet
import android.widget.ScrollView

class CustomScrollView : ScrollView {

    private var mOnScrollViewListener: OnScrollViewListener? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    interface OnScrollViewListener {
        fun onScrollChanged(v: CustomScrollView, l: Int, t: Int, oldl: Int, oldt: Int)
    }

    fun setOnScrollViewListener(l: OnScrollViewListener) {
        this.mOnScrollViewListener = l
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        mOnScrollViewListener!!.onScrollChanged(this, l, t, oldl, oldt)
        super.onScrollChanged(l, t, oldl, oldt)
    }
}

