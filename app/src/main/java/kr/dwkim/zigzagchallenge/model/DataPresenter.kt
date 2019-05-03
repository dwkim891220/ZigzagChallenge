package kr.dwkim.zigzagchallenge.model

import android.content.Context
import android.graphics.Color
import com.google.gson.Gson
import java.io.IOException
import kotlin.random.Random

class DataPresenter {
    private var originShopList: List<Shop>? = null
    private var originShopModelList: List<ShopModel>? = null
    private var styleModelList: List<StyleModel>? = null

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

    fun getShopModelList(): List<ShopModel> = originShopModelList ?: listOf()

    data class StyleModel(
        val style: String,
        val color: Int
    )
}