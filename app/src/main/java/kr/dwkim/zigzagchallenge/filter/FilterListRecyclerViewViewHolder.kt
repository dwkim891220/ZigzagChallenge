package kr.dwkim.zigzagchallenge.filter

import android.view.View
import kr.dwkim.zigzagchallenge.model.FilterItem
import kr.dwkim.zigzagchallenge.util.AndroidExtensionsViewHolder

abstract class FilterListRecyclerViewViewHolder(view: View) : AndroidExtensionsViewHolder(view){
    abstract fun onBind(item: FilterItem, position: Int)
}