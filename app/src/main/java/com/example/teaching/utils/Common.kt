package com.example.teaching.utils

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.MutableLiveData

@SuppressLint("StaticFieldLeak")
object Common {

    lateinit var globalContext: Context
    private var something: String = ""

    //change language
    var changeLanguage: MutableLiveData<Boolean> = MutableLiveData(false)
//
//    //adding assignment
//    var selectedClass: Class? = null
//    var selectedTopic: Topic? = null
//
//    var studentWork: Studentwork? = null
//
//    var yearForTimeTable: MutableLiveData<List<Int>> = MutableLiveData()
//    var timeTableList: MutableLiveData<List<Etimetablelesson>?> = MutableLiveData()
//    var teacherTimeTableList: MutableLiveData<List<uz.tbteam.abisparents.modelApi.teacherTimeTable.Etimetablelesson>?> =
//        MutableLiveData()
//    var isLoaded = false
//    var isLoadedTeacher = false
//
//    var isOnline: MutableLiveData<String> = MutableLiveData()
//
//    var isList: MutableLiveData<Boolean> = MutableLiveData()
//    var isStreamFragment: Boolean = false
//
//    var isPair: MutableLiveData<Boolean> = MutableLiveData()
//
//    //teacher
//    var selectedUsers: HashSet<Int> = HashSet()
//
//    //student
//    var selectedStudent: HashSet<Int> = HashSet()
//    var selectedTeacher: HashSet<Int> = HashSet()

}