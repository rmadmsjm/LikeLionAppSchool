package kr.co.lion.androidproject4boardapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RowReadContentReplyViewModel : ViewModel() {
    val textViewRowReplyText = MutableLiveData<String>()
    val textViewRowReplyNickname = MutableLiveData<String>()
    val textViewRowReplyDate = MutableLiveData<String>()
}