package com.auvehassan.customerrorscreen

import com.google.gson.Gson

object JsonFormatter {

    private val gson = Gson()

    fun toJson(message: Any?): String = gson.toJson(message)

    fun <T> fromJson(json: String?, clazz: Class<T>?): T = gson.fromJson(json, clazz)
}