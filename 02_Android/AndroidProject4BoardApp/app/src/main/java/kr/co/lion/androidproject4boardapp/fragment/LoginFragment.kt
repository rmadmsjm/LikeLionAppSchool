package kr.co.lion.androidproject4boardapp.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import kr.co.lion.androidproject4boardapp.ContentActivity
import kr.co.lion.androidproject4boardapp.MainActivity
import kr.co.lion.androidproject4boardapp.MainFragmentName
import kr.co.lion.androidproject4boardapp.R
import kr.co.lion.androidproject4boardapp.Tools
import kr.co.lion.androidproject4boardapp.databinding.FragmentLoginBinding
import kr.co.lion.androidproject4boardapp.viewmodel.LoginViewModel

class LoginFragment : Fragment() {

    lateinit var fragmentLoginBinding: FragmentLoginBinding
    lateinit var mainActivity: MainActivity
    lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentLoginBinding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_login, container, false)
        loginViewModel = LoginViewModel()
        fragmentLoginBinding.loginViewModel = loginViewModel
        fragmentLoginBinding.lifecycleOwner = this
        mainActivity = activity as MainActivity

        settingToolbar()
        settingButtonLoginJoin()
        settingButtonLoginSubmit()
        checkInputForm()

        return fragmentLoginBinding.root
    }

    // 툴바 설정
    fun settingToolbar() {
        fragmentLoginBinding.apply {
            toolbarLogin.apply {
                // 타이틀
                title = "로그인"
            }
        }
    }

    // 회원 가입 버튼
    fun settingButtonLoginJoin() {
        fragmentLoginBinding.apply {
            buttonLoginJoin.apply {
                setOnClickListener {
                    // JoinFragment가 보이게 함
                    mainActivity.replaceFragment(MainFragmentName.JOIN_FRAGMENT, true, true, null)
                }
            }
        }
    }

    // 로그인 버튼
    fun settingButtonLoginSubmit() {
        fragmentLoginBinding.apply {
            buttonLoginSubmit.apply {
                setOnClickListener {
                    // 유효성 검사
                    val chk = checkInputForm()

                    // 모든 유효성 검사에 통과를 했다면
                    if(chk == true) {
                        // ContentActivity 실행
                        val contentIntent = Intent(mainActivity, ContentActivity::class.java)
                        startActivity(contentIntent)

                        // MainActivity 종료
                        mainActivity.finish()
                    }
                }
            }
        }
    }

    // 유효성 검사
    fun checkInputForm(): Boolean {
        // 입력한 값 가져오기
        val userId = loginViewModel.textFieldLoginUserId.value!!
        val userPw = loginViewModel.textFieldLoginUserPw.value!!

        if(userId.isEmpty()) {
            Tools.showErrorDialog(mainActivity, fragmentLoginBinding.textFieldLoginUserId, "아이디 입력 오류", "아이디를 입력해주세요")
            return false
        }

        if(userPw.isEmpty()) {
            Tools.showErrorDialog(mainActivity, fragmentLoginBinding.textFieldLoginUserPw, "비밀번호 입력 오류", "비밀번호를 입력해주세요")
            return false
        }

        return true
    }
}