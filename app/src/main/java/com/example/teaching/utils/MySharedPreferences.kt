package com.example.teaching.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

object MySharedPreferences {
    private val NAME = "exam"
    private val MODE = Context.MODE_PRIVATE
    private val settings: SharedPreferences? = null
    private const val keyPurchase = "app_purchased"

    /** change theme app */
    @SuppressLint("ApplySharedPref")
    fun setNightMode(context: Context, nightMode: String) {
        val sharedPreferences = context.getSharedPreferences("nightMode", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("nightMode", nightMode).commit()
    }

    fun getNightMode(context: Context): String {
        val sharedPreferences = context.getSharedPreferences("nightMode", Context.MODE_PRIVATE)
        return sharedPreferences.getString("nightMode", "auto") ?: "auto"
    }

    //    language
    @SuppressLint("ApplySharedPref")
    fun setLang(context: Context, lang: String) {
        val sharedPreferences = context.getSharedPreferences("lang", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("lang", lang).commit()
    }

    fun getLang(context: Context): String {
        val sharedPreferences = context.getSharedPreferences("lang", Context.MODE_PRIVATE)
        return sharedPreferences.getString("lang", "") ?: ""
    }



}