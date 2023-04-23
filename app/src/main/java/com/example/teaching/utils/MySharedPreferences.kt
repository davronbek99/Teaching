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

    @SuppressLint("ApplySharedPref")
    fun setSurname(context: Context, surname: String) {
        val sharedPreferences = context.getSharedPreferences("surname", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("surname", surname).commit()
    }

    fun getSurname(context: Context): String {
        val sharedPreferences = context.getSharedPreferences("surname", Context.MODE_PRIVATE)
        return sharedPreferences.getString("surname", "") ?: ""
    }


    @SuppressLint("ApplySharedPref")
    fun setFirstname(context: Context, firstname: String) {
        val sharedPreferences = context.getSharedPreferences("firstname", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("firstname", firstname).commit()
    }

    fun getFirstname(context: Context): String {
        val sharedPreferences = context.getSharedPreferences("firstname", Context.MODE_PRIVATE)
        return sharedPreferences.getString("firstname", "") ?: ""
    }

    @SuppressLint("ApplySharedPref")
    fun setMiddlename(context: Context, firstname: String) {
        val sharedPreferences = context.getSharedPreferences("middlename", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("middlename", firstname).commit()
    }

    fun getMiddlename(context: Context): String {
        val sharedPreferences = context.getSharedPreferences("middlename", Context.MODE_PRIVATE)
        return sharedPreferences.getString("middlename", "") ?: ""
    }

    @SuppressLint("ApplySharedPref")
    fun setDateOfBirth(context: Context, firstname: String) {
        val sharedPreferences = context.getSharedPreferences("date_of_birth", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("date_of_birth", firstname).commit()
    }

    fun getDateOfBirth(context: Context): String {
        val sharedPreferences = context.getSharedPreferences("date_of_birth", Context.MODE_PRIVATE)
        return sharedPreferences.getString("date_of_birth", "") ?: ""
    }

    @SuppressLint("ApplySharedPref")
    fun setPhone(context: Context, firstname: String) {
        val sharedPreferences = context.getSharedPreferences("phone_number", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("phone_number", firstname).commit()
    }

    fun getPhone(context: Context): String {
        val sharedPreferences = context.getSharedPreferences("phone_number", Context.MODE_PRIVATE)
        return sharedPreferences.getString("phone_number", "") ?: ""
    }

    @SuppressLint("ApplySharedPref")
    fun setEmail(context: Context, firstname: String) {
        val sharedPreferences = context.getSharedPreferences("email", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("email", firstname).commit()
    }

    fun getEmail(context: Context): String {
        val sharedPreferences = context.getSharedPreferences("email", Context.MODE_PRIVATE)
        return sharedPreferences.getString("email", "") ?: ""
    }

    @SuppressLint("ApplySharedPref")
    fun setAddress(context: Context, firstname: String) {
        val sharedPreferences = context.getSharedPreferences("address", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("address", firstname).commit()
    }

    fun getAddress(context: Context): String {
        val sharedPreferences = context.getSharedPreferences("address", Context.MODE_PRIVATE)
        return sharedPreferences.getString("address", "") ?: ""
    }


}