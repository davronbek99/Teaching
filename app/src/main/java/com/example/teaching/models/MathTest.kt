package com.example.teaching.models

import java.io.Serializable

data class MathTest(
    var id: Int = 0,
    var ans: String = "",
    var num: Int = 0,
    var qImg: String = "",
    var ques: String = "",
    var img1: String = "",
    var img2: String = "",
    var img3: String = "",
    var img4: String = "",
    var v1: String = "",
    var v2: String = "",
    var v3: String = "",
    var v4: String = ""
) : Serializable