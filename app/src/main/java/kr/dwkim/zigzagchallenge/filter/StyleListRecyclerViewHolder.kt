package kr.dwkim.zigzagchallenge.filter

import android.view.View
import kotlinx.android.synthetic.main.item_filter_style.view.*
import kr.dwkim.zigzagchallenge.model.FilterItem

class StyleListRecyclerViewHolder(view: View) : FilterListRecyclerViewViewHolder(view) {
    lateinit var onClickItem: (Int) -> Unit

    override fun onBind(item: FilterItem, position: Int) {
        itemView.tv_itemFilterStyle.run {
            text = item.displayText
            isSelected = item.isSelected
            setOnClickListener {
                item.isSelected = !item.isSelected
                if(::onClickItem.isInitialized) onClickItem(position)
            }
        }
    }
}