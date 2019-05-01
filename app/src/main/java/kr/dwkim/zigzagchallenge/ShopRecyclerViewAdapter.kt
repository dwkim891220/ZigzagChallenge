package kr.dwkim.zigzagchallenge

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_shop.view.*
import kr.dwkim.zigzagchallenge.model.ShopModel
import kr.dwkim.zigzagchallenge.util.AndroidExtensionsViewHolder

class ShopRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var itemList: List<ShopModel> = listOf()

    fun addAll(list: List<ShopModel>){
        itemList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_shop, parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        try {
            (holder as? ItemViewHolder)?.run {
                val item = itemList[position]

                Glide.with(this.containerView.context)
                    .load(item.imageUrl)
                    .apply(RequestOptions.circleCropTransform())
                    .into(itemView.iv_itemShop)

                itemView.tv_itemShop_name.text = item.name
                itemView.tv_itemShop_age.text = item.ageStringList.joinToString { age -> String.format("%s ", age) }
                itemView.tv_itemShop_style.text = item.styleList.joinToString { style -> String.format("%s ", style) }
                itemView.tv_itemShop_rank.text = (position+1).toString()
            }
        }catch (e: IndexOutOfBoundsException){
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int = itemList.size

    inner class ItemViewHolder(view: View): AndroidExtensionsViewHolder(view)
}