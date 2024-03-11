package kr.co.lion.androidproject_board.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddContentViewModel : ViewModel() {
    // 제목
    val textFieldAddContentTitle = MutableLiveData<String>()
    // 내용
    val textFieldAddContentText = MutableLiveData<String>()
    // 게시판
    val menuAddContentBoardType = MutableLiveData<String>()
    // 이미지
}