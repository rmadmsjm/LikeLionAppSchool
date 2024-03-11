package kr.co.lion.androidproject_board.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class JoinViewModel : ViewModel() {
    // 아이디
    val textFieldJoinId = MutableLiveData<String>()
    // 비밀번호
    val textFieldJoinPassword = MutableLiveData<String>()
    // 비밀번호 확인
    val textFieldJoinPasswordCheck = MutableLiveData<String>()
}