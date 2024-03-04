package kr.co.lion.androidproject_board

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import kr.co.lion.androidproject_board.databinding.FragmentJoinBinding

class JoinFragment : Fragment() {

    lateinit var fragmentJoinBinding: FragmentJoinBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentJoinBinding = FragmentJoinBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingToolbar()
        settingTextField()
        settingButtonJoinNext()

        return fragmentJoinBinding.root
    }

    // 툴바 설정
    fun settingToolbar() {
        fragmentJoinBinding.apply {
            toolbarJoin.apply {
                title = "회원가입"

                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(FragmentName.JOIN_FRAGMENT)
                }
            }
        }
    }

    // 다음 Button 설정
    fun settingButtonJoinNext() {
        fragmentJoinBinding.apply {
            buttonJoinNext.setOnClickListener {
                val chk = checkTextField()

                if(chk == true) {
                    // 키보드 내리기
                    mainActivity.hideSoftInput()

                    mainActivity.replaceFragment(FragmentName.ADD_USER_INFO_FRAGMENT, true, true, null)
                }
            }
        }
    }

    // 입력 요소 설정
    fun settingTextField() {
        fragmentJoinBinding.apply {
            // 첫 번째 요소에 포커스
            mainActivity.showSoftInput(textFieldJoinId)

            // 에러 메시지가 보여지는 상황에서 값을 입력했을 때 에러 메세지 없애기
            textFieldJoinId.addTextChangedListener {
                textInputLayoutJoinId.isErrorEnabled = false
            }
            textFieldJoinPassword.addTextChangedListener {
                textInputLayoutJoinPassword.isErrorEnabled = false
            }
            textFieldJoinPasswordCheck.addTextChangedListener {
                textInputLayoutJoinPasswordCheck.isErrorEnabled = false
            }
        }
    }

    // 유효성 검사
    fun checkTextField() : Boolean {
        fragmentJoinBinding.apply {
            var emptyView: View? = null

            // 아이디
            if(textFieldJoinId.text.toString().trim().isEmpty()) {
                textInputLayoutJoinId.error = "아이디를 입력해주세요"

                if(emptyView == null) {
                    emptyView = textFieldJoinId
                }
            } else {
                textInputLayoutJoinId.isErrorEnabled = false
            }

            // 비밀번호
            if(textFieldJoinPassword.text.toString().trim().isEmpty()) {
                textInputLayoutJoinPassword.error = "비밀번호를 입력해주세요"

                if(emptyView == null) {
                    emptyView = textFieldJoinPassword
                }
            } else {
                textInputLayoutJoinPassword.isErrorEnabled = false
            }

            // 비밀번호 확인
            if(textFieldJoinPasswordCheck.text.toString().trim().isEmpty()) {
                textInputLayoutJoinPasswordCheck.error = "비밀번호를 입력해주세요"

                if(emptyView == null) {
                    emptyView = textInputLayoutJoinPasswordCheck
                }
            } else if(textFieldJoinPasswordCheck.text.toString().trim() != textFieldJoinPassword.text.toString().trim()) {
                textInputLayoutJoinPasswordCheck.error = "비밀번호가 일치하지 않습니다"

                return false
            } else {
                textInputLayoutJoinPasswordCheck.isErrorEnabled = false
            }

            if(emptyView != null) {
                return false
            }

            return true
        }
    }
}