package kr.dwkim.zigzagchallenge.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FilterItem(
    val displayText: String,
    var isSelected: Boolean
) : Parcelable