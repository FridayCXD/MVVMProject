package com.mvvm.model.widget

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import androidx.annotation.IntDef
import androidx.appcompat.widget.AppCompatTextView
import com.mvvm.model.R


/**
 * DrawableTextView.kt
 * Info: 自定义带图标, 带点击关闭的TextView
 * Created by cxd on 2020-03-20 16:17
 */

class DrawableTextView : AppCompatTextView {

    //Koltin数组的创建方式 arrayOfNulls,emptyArray
    private var widths = arrayOfNulls<Int>(4)
    private var heights = arrayOfNulls<Int>(4)
    private var drawables = arrayOfNulls<Drawable>(4)

    private lateinit var listener:()-> Unit
    private fun checkListener() = ::listener.isInitialized

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    @IntDef(
        LEFT,
        TOP,
        RIGHT,
        BOTTOM
    )
    @Retention(AnnotationRetention.SOURCE)
    annotation class DrawGravity

    private fun init(context: Context, attrs: AttributeSet?) {
        val typedArray: TypedArray =
            context.obtainStyledAttributes(attrs, R.styleable.DrawableTextView)
        gravity = Gravity.CENTER
        drawables[0] = typedArray.getDrawable(R.styleable.DrawableTextView_leftDrawable)
        drawables[1] = typedArray.getDrawable(R.styleable.DrawableTextView_topDrawable)
        drawables[2] = typedArray.getDrawable(R.styleable.DrawableTextView_rightDrawable)
        drawables[3] = typedArray.getDrawable(R.styleable.DrawableTextView_bottomDrawable)

        widths[0] =
            typedArray.getDimensionPixelSize(R.styleable.DrawableTextView_leftDrawableWidth, 0)
        widths[1] =
            typedArray.getDimensionPixelSize(R.styleable.DrawableTextView_topDrawableWidth, 0)
        widths[2] =
            typedArray.getDimensionPixelSize(R.styleable.DrawableTextView_rightDrawableWidth, 0)
        widths[3] =
            typedArray.getDimensionPixelSize(R.styleable.DrawableTextView_bottomDrawableWidth, 0)

        heights[0] =
            typedArray.getDimensionPixelSize(R.styleable.DrawableTextView_leftDrawableHeight, 0)
        heights[1] =
            typedArray.getDimensionPixelSize(R.styleable.DrawableTextView_topDrawableHeight, 0)
        heights[2] =
            typedArray.getDimensionPixelSize(R.styleable.DrawableTextView_rightDrawableHeight, 0)
        heights[3] =
            typedArray.getDimensionPixelSize(R.styleable.DrawableTextView_bottomDrawableHeight, 0)
        typedArray.recycle()
    }


    public fun setDrawable(
        @DrawGravity gravity: Int,
        drawable: Drawable,
        width: Int,
        height: Int
    ) {
        drawables[gravity] = drawable
        widths[gravity] = width
        heights[gravity] = height
        postInvalidate()
    }

    fun setDrawables(
        drawables: Array<Drawable?>?,
        widths: Array<Int?>,
        heights: Array<Int?>
    ) {
        if (drawables != null && drawables.size >= 4  && widths.size >= 4 && heights.size >= 4
        ) {
            this.drawables = drawables
            this.widths = widths
            this.heights = heights
            postInvalidate()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        val drawablePadding = compoundDrawablePadding
        translateText(canvas, drawablePadding)
        super.onDraw(canvas)
        val centerX =
            (width + paddingLeft - paddingRight) / 2.toFloat()
        val centerY =
            (height + paddingTop - paddingBottom) / 2.toFloat()

        val halfTextWidth = paint.measureText(
            if (text.toString().isEmpty()) hint.toString() else text.toString()
        ) / 2
        val fontMetrics: Paint.FontMetrics = paint.fontMetrics
        val halfTextHeight: Float = (fontMetrics.descent - fontMetrics.ascent) / 2

        if (drawables[0] != null) {
            val left = (centerX - drawablePadding - halfTextWidth - widths[0]!!).toInt()
            val top = (centerY - heights[0]!! / 2).toInt()
            drawables[0]!!.setBounds(
                left,
                top,
                left + widths[0]!!,
                top + heights[0]!!
            )
            canvas!!.save()
            drawables[0]!!.draw(canvas)
            canvas.restore()
        }


        if (drawables[2] != null) {
            val left = (centerX + halfTextWidth + drawablePadding).toInt()
            val top = (centerY - heights[2]!! / 2).toInt()
            drawables[2]!!.setBounds(
                left,
                top,
                left + widths[2]!!,
                top + heights[2]!!
            )
            canvas!!.save()
            drawables[2]!!.draw(canvas)
            canvas.restore()
        }

        if (drawables[1] != null) {
            val left = (centerX - widths[1]!! / 2).toInt()
            val bottom = (centerY - halfTextHeight - drawablePadding).toInt()
            drawables[1]!!.setBounds(
                left,
                bottom - heights[1]!!,
                left + widths[1]!!,
                bottom
            )
            canvas!!.save()
            drawables[1]!!.draw(canvas)
            canvas.restore()
        }


        if (drawables[3] != null) {
            val left = (centerX - widths[3]!! / 2).toInt()
            val top = (centerY + halfTextHeight + drawablePadding).toInt()
            drawables[3]!!.setBounds(
                left,
                top,
                left + widths[3]!!,
                top + heights[3]!!
            )
            canvas!!.save()
            drawables[3]!!.draw(canvas)
            canvas.restore()
        }
    }

    fun setOnCloseListener(click: ()-> Unit){
        this.listener = click
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (drawables[RIGHT] != null){
            val drawableRight = drawables[RIGHT] ?: return super.onTouchEvent(event)
            if (event?.action == MotionEvent.ACTION_DOWN && event.x > width - drawableRight.bounds.width()){
                if (checkListener()) listener()
                return true
            }
        }
        return super.onTouchEvent(event)
    }
    //移动画布的位置
    private fun translateText(
        canvas: Canvas?,
        drawablePadding: Int
    ){
        var translateWidth = 0
        if (drawables[0] != null && drawables[2] != null) {
            translateWidth = (widths[0]!! - widths[2]!!) / 2
        } else if (drawables[0] != null) {
            translateWidth = (widths[0]!! + drawablePadding) / 2
        } else if (drawables[2] != null) {
            translateWidth = -(widths[2]!! + drawablePadding) / 2
        }

        var translateHeight = 0
        if (drawables[1] != null && drawables[3] != null) {
            translateHeight = (heights[1]!! - heights[3]!!) / 2
        } else if (drawables[1] != null) {
            translateHeight = (heights[1]!! + drawablePadding) / 2
        } else if (drawables[3] != null) {
            translateHeight = -(heights[3]!! - drawablePadding) / 2
        }

        canvas!!.translate(translateWidth.toFloat(), translateHeight.toFloat())


    }

    companion object {
        const val LEFT = 0
        const val TOP = 1
        const val RIGHT = 2
        const val BOTTOM = 3
    }
}