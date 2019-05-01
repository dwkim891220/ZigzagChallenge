package kr.dwkim.zigzagchallenge.model

import android.content.Context
import com.google.gson.Gson
import java.io.IOException

class DataGenerator {
    fun getShopList(context: Context): JsonModel? =
        try {
            val inputStream = context.assets.open("shop_list.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()

            Gson().fromJson<JsonModel>(String(buffer), JsonModel::class.java)
        } catch (e: IOException){
            e.printStackTrace()
            null
        }
}