package kr.dwkim.zigzagchallenge.model

import android.content.Context
import android.graphics.Color
import com.google.gson.Gson
import java.io.IOException
import kotlin.random.Random

class DataPresenter {
    private var originShopList: List<Shop>? = null
    private var styleModelList: List<StyleModel>? = null
    private var originShopModelList: List<ShopModel>? = null

    val filterValues: FilterValues = FilterValues()

    var weekString: String? = null

    fun init(context: Context){
        try {
            val inputStream = context.assets.open("shop_list.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()

            val jsonModel = Gson().fromJson<JsonModel>(String(buffer), JsonModel::class.java)

            originShopList = jsonModel.shopList
            generateStyleModelList()
            originShopModelList = jsonModel.shopList.map { shop -> ShopModel(shop, styleModelList) }
            weekString = jsonModel.week

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun generateStyleModelList(){
        val styleTextList: ArrayList<String> = arrayListOf()

        originShopList?.forEach { shop ->
            shop.style.split(",").map { styleText ->
                if(!styleTextList.contains(styleText)){
                    styleTextList.add(styleText)
                }
            }
        }

        val random = Random(256)

        styleModelList = styleTextList.map { style ->
            StyleModel(
                style,
                Color.argb(
                    255,
                    random.nextInt(),
                    random.nextInt(),
                    random.nextInt()
                )
            )
        }
    }

    fun getShopModelList(): List<ShopModel> =
        originShopModelList?.also { shopModelList ->
            val ageSelectedCount = filterValues.ageList.filter { item -> item.isSelected }.count()
            val styleSelectedCount = filterValues.styleList.filter { item -> item.isSelected }.count()

            if (ageSelectedCount < 1 && styleSelectedCount < 1) {
                return shopModelList
            }else{
                if(ageSelectedCount > 0 && styleSelectedCount == 0){
//                    shopModelList.forEach { shopModel ->
//                        shopModel.setAgeEqualsCount(
//                            filterValues.ageList
//                                .map{ item ->
//                                    if (item.isSelected) 0
//                                    else 1
//                                }
//                        )
//                    }

                    return shopModelList.filter { item ->
                        item.ageEqualsCount > 0
                    }.sortedWith(
                        compareBy<ShopModel> { item ->
                            item.ageEqualsCount
                        }.thenBy { item ->
                            item.score
                        }
                    )
                }else if(ageSelectedCount == 0 && styleSelectedCount > 0){
                    shopModelList.forEach { shopModel ->
                        shopModel.setStyleEqualsCount(
                            filterValues.styleList
                                .filter { item -> item.isSelected }
                                .map { item -> item.displayText }
                        )
                    }

                    return shopModelList.filter { item ->
                        item.styleEqualsCount > 0
                    }.sortedWith(
                        compareBy<ShopModel> { item ->
                            item.styleEqualsCount
                        }.thenBy { item ->
                            item.score
                        }
                    ).reversed()
                }else{
                    return shopModelList
                }
            }
        } ?: emptyList()

    fun generateStyleList(): List<FilterItem> =
        styleModelList?.map { model ->
            FilterItem(model.style, false)
        } ?: emptyList()

    fun generateAgeList(): List<FilterItem> =
        listOf(
            "10대",
            "20대 초반", "20대 중반", "20대 후반",
            "30대 초반", "30대 중반", "30대 후반"
        ).map { displayText ->
            FilterItem(displayText, false)
        }

    data class StyleModel(
        val style: String,
        val color: Int
    )
}