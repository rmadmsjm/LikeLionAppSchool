package kr.co.lion.androidproject_board.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    // 아이디
    val textFieldLoginId = MutableLiveData<String>()
    // 비밀번호
    val textFieldLoginPassword = MutableLiveData<String>()
}