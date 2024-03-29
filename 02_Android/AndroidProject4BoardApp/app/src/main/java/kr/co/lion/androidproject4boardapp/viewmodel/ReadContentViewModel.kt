package kr.co.lion.androidproject4boardapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReadContentViewModel : ViewModel() {
    // 제목
    val textFieldReadContentSubject = MutableLiveData<String>()
    // 게시판
    val textFieldReadContentType = MutableLiveData<String>()
    // 닉네임
    val textFieldReadContentNickname = MutableLiveData<String>()
    // 작성 날짜
    val textFieldReadContentDate = MutableLiveData<String>()
    // 내용
    val textFieldReadContentText = MutableLiveData<String>()
}