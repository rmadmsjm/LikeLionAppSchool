package kr.co.lion.androidproject_board.viewmodel

import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.textfield.TextInputLayout

class LoginViewModel : ViewModel() {
    // 아이디
    val textFieldLoginId = MutableLiveData<String>()
    // 비밀번호
    val textFieldLoginPassword = MutableLiveData<String>()
    // 에러 메시지
    val textInputLayoutLoginIdErrorMessage = MutableLiveData<String>()
    val textInputLayoutLoginPwErrorMessage = MutableLiveData<String>()
    // 에러 메시지 클리어
    val textInputLayoutLoginIdErrorEnabled = MutableLiveData<Boolean>()
    val textInputLayoutLoginPwErrorEnabled = MutableLiveData<Boolean>()

    // 에러 메시지 설정
    fun settingTextInputLayoutLoginIdErrorMessage(message: String) {
        textInputLayoutLoginIdErrorMessage.value = message
    }

    fun settingTextInputLayoutLoginPwErrorMessage(message: String) {
        textInputLayoutLoginPwErrorMessage.value = message
    }

    // 에러 메시지 클리어
    fun clearTextInputLayoutLoginIdErrorMessage() {
        textInputLayoutLoginIdErrorMessage.value = null
    }

    companion object {
        @BindingAdapter("app:errorText")
        @JvmStatic
        fun setErrorText(view: TextInputLayout, message: String?) {
            view.error = message
        }
    }
}