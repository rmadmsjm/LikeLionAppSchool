package kr.co.lion.androidproject4boardapp.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.androidproject4boardapp.ContentActivity
import kr.co.lion.androidproject4boardapp.MainActivity
import kr.co.lion.androidproject4boardapp.MainFragmentName
import kr.co.lion.androidproject4boardapp.R
import kr.co.lion.androidproject4boardapp.Tools
import kr.co.lion.androidproject4boardapp.dao.UserDao
import kr.co.lion.androidproject4boardapp.databinding.FragmentLoginBinding
import kr.co.lion.androidproject4boardapp.viewmodel.LoginViewModel
import kotlin.math.log

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
        settingInputForm()

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
                        loginPro()

//                        // ContentActivity 실행
//                        val contentIntent = Intent(mainActivity, ContentActivity::class.java)
//                        startActivity(contentIntent)
//
//                        // MainActivity 종료
//                        mainActivity.finish()
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

    // 입력 요소 초기화
    fun settingInputForm() {
        loginViewModel.textFieldLoginUserId.value = ""
        loginViewModel.textFieldLoginUserPw.value = ""
    }

    // 로그인 처리
    fun loginPro() {
        // 사용자가 입력한 정보 가져오기
        val userId = loginViewModel.textFieldLoginUserId.value!!
        val userPw = loginViewModel.textFieldLoginUserPw.value!!


        val job1 = CoroutineScope(Dispatchers.IO).launch {
            val loginUserModel = UserDao.getUserDataById(userId)

            // 만약 null이라면
            if(loginUserModel == null) {
                Tools.showErrorDialog(mainActivity, fragmentLoginBinding.textFieldLoginUserId, "로그인 오류",
                    "존재하지 않는 아이디 입니다")
            }
            // 만약 정보를 가져왓다면
            else {
                // 입력한 비밀번호와 서버에서 받아온 사용자의 비밀번호가 다를 경우
                if(userPw != loginUserModel.userPw){
                    Tools.showErrorDialog(mainActivity, fragmentLoginBinding.textFieldLoginUserPw, "로그인 오류",
                        "비밀번호가 잘못되었습니다")
                }
                // 비밀번호가 일치할 경우
                else {
                    // 자동 로그인이 체크되어 있는 경우
                    if(loginViewModel.checkBoxLoginAuto.value == true){
                        // Preferences 에 사용자 정보를 저장하기
                        val sharedPreferences = mainActivity.getSharedPreferences("AutoLogin", Context.MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.putInt("loginUserIdx", loginUserModel.userIdx)
                        editor.putString("loginUserNickname", loginUserModel.userNickname)
                        // sharedPreferences에 데이터 반영
                        editor.apply()
                    }

                    // ContentActivity 실행
                    val contentIntent = Intent(mainActivity, ContentActivity::class.java)

                    // 로그인한 사용자 정보 전달
                    contentIntent.putExtra("loginUserIdx", loginUserModel.userIdx)
                    contentIntent.putExtra("loginUserNickname", loginUserModel.userNickname)

                    startActivity(contentIntent)

                    // MainActivity 종료
                    mainActivity.finish()
                }
            }
        }
    }
}