package com.example.teaching.models

import java.io.Serializable

data class Math5Test(
    var id: Int = 0,
    var ans: String = "",
    var num: Int = 0,
    var ques: String = "",
    var v1: String = "",
    var v2: String = "",
    var v3: String = "",
    var v4: String = ""
) : Serializable