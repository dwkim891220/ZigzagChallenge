package kr.dwkim.zigzagchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kr.dwkim.zigzagchallenge.filter.FilterFragment
import kr.dwkim.zigzagchallenge.model.DataPresenter
import kr.dwkim.zigzagchallenge.model.FilterValues
import kr.dwkim.zigzagchallenge.util.show

class MainActivity : AppCompatActivity(), MainActivityListener {
    private val dataPresenter = DataPresenter()
    private val listAdapter = ShopListRecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataPresenter.init(this)

        if(dataPresenter.getShopModelList().isEmpty()) {
            showFailedGenerateDate()
        }else{
            setWeek(dataPresenter.weekString)
            setFilterValue()
            setRecyclerView()
            setListeners()
        }
    }

    private fun setWeek(text: String?){
        tv_aMain_week.text = text
    }

    private fun setFilterValue(){
        dataPresenter.filterValues.ageList = dataPresenter.generateAgeList()
        dataPresenter.filterValues.styleList = dataPresenter.generateStyleList()
    }

    private fun setRecyclerView(){
        rv_aMain_shopList.run {
            adapter = listAdapter.apply {
                addAll(dataPresenter.getShopModelList())
            }
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(ShopListRecyclerViewDecorator(this@MainActivity))
        }
    }

    private fun setListeners(){
        btn_aMain_filter.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .add(
                    R.id.fl_aMain_fragmentContainer,
                    FilterFragment.newInstance(dataPresenter.filterValues),
                    FilterFragment.TAG
                )
                .addToBackStack(FilterFragment.TAG)
                .commit()
        }
    }

    private fun showFailedGenerateDate(){
        cl_aMain_listLayout.show(false)
        tv_aMain_emptyList.show()
    }

    override fun closeFilter() {
        supportFragmentManager.findFragmentByTag(FilterFragment.TAG)?.run{
            if(isVisible){
                supportFragmentManager.popBackStack()
            }
        }
    }

    override fun applyFilter() {
        closeFilter()
        listAdapter.addAll(dataPresenter.getShopModelList())
    }
}