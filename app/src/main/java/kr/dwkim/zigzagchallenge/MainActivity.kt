package kr.dwkim.zigzagchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kr.dwkim.zigzagchallenge.model.DataGenerator
import kr.dwkim.zigzagchallenge.model.ShopModel
import kr.dwkim.zigzagchallenge.util.show

class MainActivity : AppCompatActivity() {
    lateinit var shopModelList : List<ShopModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DataGenerator().getShopList(this)?.run {
            shopModelList = this.shopList.map { shop -> ShopModel(shop) }
            setWeek(this.week)
            setRecyclerView()
        } ?: showFailedGenerateDate()
    }

    private fun setWeek(text: String){
        tv_aMain_week.text = text
    }

    private fun setRecyclerView(){
        rv_aMain_shopList.run {
            adapter = ShopRecyclerViewAdapter().apply {
                addAll(shopModelList)
            }
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(ShopRecyclerViewDecorator(this@MainActivity))
        }
    }

    private fun showFailedGenerateDate(){
        cl_aMain_listLayout.show(false)
        tv_aMain_emptyList.show()
    }
}
