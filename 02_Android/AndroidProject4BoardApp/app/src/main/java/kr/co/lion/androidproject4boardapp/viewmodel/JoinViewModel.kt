package kr.co.lion.androidproject4boardapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class JoinViewModel : ViewModel() {
    // 아이디
    val textFieldJoinUserId = MutableLiveData<String>()
    // 비밀버호
    val textFieldJoinUserPw = MutableLiveData<String>()
    // 비밀번호 확인
    val textFieldJoinUserPw2 = MutableLiveData<String>()
    // 툴바 타이틀
    val toolbarJoinTitle = MutableLiveData<String>()
    // 툴바 Navigation Icon
    val toolbarJoinNavigationIcon = MutableLiveData<Int>()
}