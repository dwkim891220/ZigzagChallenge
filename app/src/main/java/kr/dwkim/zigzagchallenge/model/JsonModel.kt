package kr.dwkim.zigzagchallenge.model

import com.google.gson.annotations.SerializedName

data class JsonModel (
    @SerializedName("list") val shopList: List<Shop>,
    @SerializedName("week") val week: String
)