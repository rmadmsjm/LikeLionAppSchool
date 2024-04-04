package kr.co.lion.androidproject4boardapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReadContentBottomViewModel : ViewModel() {

    // 댓글 내용 입력 요소
    val textFieldAddContentReply = MutableLiveData<String>()
}