package com.task.virginmoney.common

import android.content.Context
import com.task.virginmoney.R

fun getTextColorForRoomAvailability(isOccupied: Boolean): Int {
    return if (!isOccupied) {
        R.color.teal_700
    } else {
        R.color.red
    }
}

fun getTextForRoomAvailability(isOccupied: Boolean, context: Context): String {
    return if(!isOccupied) context.getString(R.string.text_available) else context.getString(R.string.text_unavailable)
}