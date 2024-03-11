package kr.co.lion.androidproject_board.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.google.android.material.snackbar.Snackbar
import kr.co.lion.androidproject_board.ContentActivity
import kr.co.lion.androidproject_board.FragmentName
import kr.co.lion.androidproject_board.MainActivity
import kr.co.lion.androidproject_board.Tool
import kr.co.lion.androidproject_board.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    lateinit var fragmentLoginBinding: FragmentLoginBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentLoginBinding = FragmentLoginBinding.inflate(inflater)
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

                    mainActivity.finish()

                    Snackbar.make(fragmentLoginBinding.root, "로그인 되었습니다", Snackbar.LENGTH_SHORT).show()
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
        fragmentLoginBinding.apply {
            // 에러 메시지가 보여지는 상황에서 값을 입력했을 때 에러 메세지 없애기
            textFieldLoginId.addTextChangedListener {
                textInputLayoutLoginId.isErrorEnabled = false
            }
            textFieldLoginPassword.addTextChangedListener {
                textInputLayoutLoginPassword.isErrorEnabled = false
            }
        }
    }

    // 유효성 검사
    fun checkTextField() : Boolean {
        fragmentLoginBinding.apply {
            var emptyView: View? = null

            // 아이디
            if(textFieldLoginId.text.toString().trim().isEmpty()) {
                textInputLayoutLoginId.error = "아이디를 입력해주세요"

                if(emptyView == null) {
                    emptyView = textFieldLoginId
                }
            }

            // 비밀번호
            if(textFieldLoginPassword.text.toString().trim().isEmpty()) {
                textInputLayoutLoginPassword.error = "비밀번호를 입력해주세요"

                if(emptyView == null) {
                    emptyView = textInputLayoutLoginPassword
                }
            }

            if(emptyView != null) {
                return false
            }

            return true
        }
    }
}