package kr.dwkim.zigzagchallenge.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Shop(
    @SerializedName("0") val score: Int,
    @SerializedName("A") val ageArray: IntArray,
    @SerializedName("S") val style: String,
    @SerializedName("n") val name: String,
    @SerializedName("u") val url: String
) {
    override fun equals(other: Any?): Boolean {
        if(this === other) return true

        (other as? Shop)?.run {
            return Arrays.equals(this@Shop.ageArray, this.ageArray)
        } ?: return false
    }

    override fun hashCode(): Int {
        return Arrays.hashCode(ageArray)
    }
}