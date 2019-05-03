package kr.dwkim.zigzagchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kr.dwkim.zigzagchallenge.model.DataPresenter
import kr.dwkim.zigzagchallenge.util.show

class MainActivity : AppCompatActivity() {
    private val dataPresenter = DataPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataPresenter.init(this)

        if(dataPresenter.getShopModelList().isEmpty()) {
            showFailedGenerateDate()
        }else{
            setWeek(dataPresenter.weekString)
            setRecyclerView()
        }
    }

    private fun setWeek(text: String?){
        tv_aMain_week.text = text
    }

    private fun setRecyclerView(){
        rv_aMain_shopList.run {
            adapter = ShopRecyclerViewAdapter().apply {
                addAll(dataPresenter.getShopModelList())
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
