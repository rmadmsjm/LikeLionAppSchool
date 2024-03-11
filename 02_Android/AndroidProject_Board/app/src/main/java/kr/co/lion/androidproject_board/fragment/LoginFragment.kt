package kr.co.lion.androidproject_board.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import kr.co.lion.androidproject_board.ContentActivity
import kr.co.lion.androidproject_board.FragmentName
import kr.co.lion.androidproject_board.MainActivity
import kr.co.lion.androidproject_board.R
import kr.co.lion.androidproject_board.Tool
import kr.co.lion.androidproject_board.databinding.FragmentLoginBinding
import kr.co.lion.androidproject_board.viewmodel.LoginViewModel
import kotlin.math.log

class LoginFragment : Fragment() {

    lateinit var fragmentLoginBinding: FragmentLoginBinding
    lateinit var mainActivity: MainActivity
    lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentLoginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        loginViewModel = LoginViewModel()
        fragmentLoginBinding.loginViewModel = loginViewModel
        fragmentLoginBinding.lifecycleOwner = this

        mainActivity = activity as MainActivity

        settingToolbar()
        settingTextField()
        settingButtonLoginSignIn()
        settingButtonLoginSignUp()

        return fragmentLoginBinding.root
    }

    // 툴바 설정
    fun settingToolbar() {
        fragmentLoginBinding.apply {
            toolbarLogin.apply {
                title = "로그인"
            }
        }
    }

    // 로그인 Button 설정
    fun settingButtonLoginSignIn() {
        fragmentLoginBinding.apply {
            buttonLoginSignIn.setOnClickListener {
                val chk = checkTextField()

                if(chk == true) {
                    // 키보드 내리기
                    Tool.hideSoftInput(mainActivity)

                    val contentIntent = Intent(mainActivity, ContentActivity::class.java)
                    startActivity(contentIntent)

                    Snackbar.make(requireView(), "로그인 되었습니다", Snackbar.LENGTH_SHORT).show()

                    mainActivity.finish()
                }
            }
        }
    }

    // 회원가입 Button 설정
    fun settingButtonLoginSignUp() {
        fragmentLoginBinding.apply {
            buttonLoginSignUp.setOnClickListener {
                mainActivity.replaceFragment(FragmentName.JOIN_FRAGMENT, true, true, null)
            }
        }
    }

    // 입력 요소 설정
    fun settingTextField() {
        loginViewModel.textFieldLoginId.value = ""
        loginViewModel.textFieldLoginPassword.value = ""

        fragmentLoginBinding.apply {
            // 에러 메시지가 보여지는 상황에서 값을 입력했을 때 에러 메세지 없애기
            textFieldLoginId.addTextChangedListener {
                loginViewModel!!.textInputLayoutLoginIdErrorEnabled.value = false
            }
            textFieldLoginPassword.addTextChangedListener {
                loginViewModel!!.textInputLayoutLoginPwErrorEnabled.value = false
            }
        }
    }

    // 유효성 검사
    fun checkTextField() : Boolean {
        val userId = loginViewModel.textFieldLoginId.value!!
        val userPw = loginViewModel.textFieldLoginPassword.value!!
        var emptyView: View? = null

        loginViewModel.apply {
            // 아이디
            if(userId.trim().isEmpty()) {
                settingTextInputLayoutLoginIdErrorMessage("아이디를 입력해주세요")

                if(emptyView == null) {
                    emptyView = fragmentLoginBinding.textFieldLoginId
                }
            }

            // 비밀번호
            if(userPw.trim().isEmpty()) {
                settingTextInputLayoutLoginPwErrorMessage("비밀번호를 입력해주세요")

                if(emptyView == null) {
                    emptyView = fragmentLoginBinding.textInputLayoutLoginPassword
                }
            }

            if(emptyView != null) {
                return false
            }

            return true
        }
    }
}