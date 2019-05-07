package kr.dwkim.zigzagchallenge.filter

import android.view.View
import kotlinx.android.synthetic.main.item_filter_age.view.*
import kr.dwkim.zigzagchallenge.model.FilterItem

class AgeListRecyclerViewHolder(view: View) : FilterListRecyclerViewViewHolder(view) {
    lateinit var onClickItem: (Int) -> Unit

    override fun onBind(item: FilterItem, position: Int) {
        itemView.tv_itemFilterAge.run {
            text = item.displayText
            isSelected = item.isSelected
            setOnClickListener {
                item.isSelected = !item.isSelected
                if(::onClickItem.isInitialized) onClickItem(position)
            }
        }
    }
}