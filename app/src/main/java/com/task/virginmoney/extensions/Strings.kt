package com.task.virginmoney.extensions

fun String.concatenate(stringToAppend: String?) = String.format("%s %s",this,stringToAppend)
