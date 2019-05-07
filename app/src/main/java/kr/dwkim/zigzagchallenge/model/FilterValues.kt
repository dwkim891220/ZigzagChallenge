package kr.dwkim.zigzagchallenge.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FilterValues(
    var ageList: List<FilterItem> = emptyList(),
    var styleList: List<FilterItem> = emptyList()
) : Parcelable