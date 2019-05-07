package kr.dwkim.zigzagchallenge.filter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.dwkim.zigzagchallenge.R
import kr.dwkim.zigzagchallenge.model.FilterItem

class FilterListRecyclerViewAdapter(
    private val adapterType: AdapterType
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    enum class AdapterType{ Style, Age }

    var itemList: List<FilterItem> = emptyList()

    fun addAll(list: List<FilterItem>?){
        list?.run {
            itemList = this
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when(adapterType){
            AdapterType.Style ->
                StyleListRecyclerViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_filter_style,
                        parent,
                        false
                    )
                ).apply {
                    onClickItem = { position ->
                        notifyItemChanged(position)
                    }
                }
            AdapterType.Age ->
                AgeListRecyclerViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_filter_age,
                        parent,
                        false
                    )
                ).apply {
                    onClickItem = { position ->
                        notifyItemChanged(position)
                    }
                }
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        try {
            (holder as? FilterListRecyclerViewViewHolder)?.onBind(itemList[position], position)
        }catch (e: IndexOutOfBoundsException){
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int = itemList.size
}