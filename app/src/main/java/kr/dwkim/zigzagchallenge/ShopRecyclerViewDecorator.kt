package kr.dwkim.zigzagchallenge

import android.content.Context
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class ShopRecyclerViewDecorator(context: Context) : RecyclerView.ItemDecoration() {
    private val divider = ContextCompat.getDrawable(context, R.drawable.divider)

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        for(index in 0..parent.childCount){
            parent.getChildAt(index)?.also { pChild ->
                val params = pChild.layoutParams as RecyclerView.LayoutParams

                val top = pChild.bottom + params.bottomMargin
                val bottom = top + (divider?.intrinsicHeight ?: 0)

                divider?.run {
                    setBounds(left, top, right, bottom)
                    draw(c)
                }
            }
        }
    }
}