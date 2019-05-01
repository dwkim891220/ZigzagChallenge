package kr.dwkim.zigzagchallenge.util

import android.view.View

fun View.show(show: Boolean = true){
    this.visibility = if(show) View.VISIBLE else View.GONE
}

fun View.isVisibility() : Boolean = this.visibility == View.VISIBLE