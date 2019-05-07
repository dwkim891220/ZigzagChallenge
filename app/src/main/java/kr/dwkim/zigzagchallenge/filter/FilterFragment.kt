package kr.dwkim.zigzagchallenge.filter

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexboxLayoutManager
import kr.dwkim.zigzagchallenge.filter.FilterListRecyclerViewAdapter.AdapterType
import kotlinx.android.synthetic.main.fragment_filter.*
import kr.dwkim.zigzagchallenge.MainActivityListener
import kr.dwkim.zigzagchallenge.R
import kr.dwkim.zigzagchallenge.model.FilterValues

class FilterFragment : Fragment() {
    companion object {
        val TAG = FilterFragment::class.java.simpleName
        fun newInstance(filterValues: FilterValues) : FilterFragment {
            return FilterFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(PARAMS_FILTER_VALUES, filterValues)
                }
            }
        }
    }

    private val PARAMS_FILTER_VALUES = "paramsFilterValues"

    private val ageListAdapter = FilterListRecyclerViewAdapter(AdapterType.Age)
    private val styleListAdapter = FilterListRecyclerViewAdapter(AdapterType.Style)

    private var listener: MainActivityListener? = null
    private var filterValues: FilterValues? = null

    override fun onAttach(context: Context) {
        listener = context as? MainActivityListener
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_filter, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        filterValues = arguments?.getParcelable(PARAMS_FILTER_VALUES)
        initLayouts()
        setRecyclerViews()
    }

    private fun initLayouts(){
        iv_fFilter_close.setOnClickListener {
            listener?.closeFilter()
        }

        tv_fFilter_apply.setOnClickListener {
            listener?.applyFilter()
        }

        btn_fFilter_clear.setOnClickListener {
            filterValues?.ageList?.forEach { item -> item.isSelected = false }
            ageListAdapter.notifyDataSetChanged()

            filterValues?.styleList?.forEach { item -> item.isSelected = false }
            styleListAdapter.notifyDataSetChanged()
        }
    }

    private fun setRecyclerViews() {
        rv_fFilter_ageList.run {
            adapter = ageListAdapter.apply {
                addAll(filterValues?.ageList)
            }
            layoutManager = FlexboxLayoutManager(context)
            addItemDecoration(FilterListDecorator(context))
        }

        rv_fFilter_styleList.run {
            adapter = styleListAdapter.apply {
                addAll(filterValues?.styleList)
            }
            layoutManager = FlexboxLayoutManager(context)
            addItemDecoration(FilterListDecorator(context))
        }
    }

    inner class FilterListDecorator(private val context: Context): RecyclerView.ItemDecoration(){
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            super.getItemOffsets(outRect, view, parent, state)

            outRect.right = context.resources.getDimension(R.dimen.defaultHalfSpacing).toInt()
            outRect.bottom = context.resources.getDimension(R.dimen.defaultHalfSpacing).toInt()
        }
    }
}