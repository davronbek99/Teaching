package com.example.teaching

import android.app.Application
import com.example.teaching.utils.Common
import com.example.teaching.utils.MySharedPreferences
import com.yariksoffice.lingver.Lingver
class MyApplication:Application() {
    override fun onCreate() {
        super.onCreate()

        val lang = MySharedPreferences.getLang(this) ?: ""
        Lingver.init(this, lang)

        Common.globalContext = this
    }
}