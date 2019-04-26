package kr.dwkim.zigzagchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = generateJson()
        list?.run {
            Log.d("dd","dd")
        }
    }

    private fun generateJson(): JsonModel? =
        try {
            val inputStream = assets.open("shop_list.json")
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
