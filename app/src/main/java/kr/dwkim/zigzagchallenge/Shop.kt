package kr.dwkim.zigzagchallenge

import com.google.gson.annotations.SerializedName

data class Shop(
    @SerializedName("0") val id: Int,
    @SerializedName("A") val ageList: Array<Int>,
    @SerializedName("S") val style: String,
    @SerializedName("n") val name: String,
    @SerializedName("u") val url: String
)