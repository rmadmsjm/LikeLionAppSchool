package kr.co.lion.androidproject_board.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import kr.co.lion.androidproject_board.FragmentName
import kr.co.lion.androidproject_board.MainActivity
import kr.co.lion.androidproject_board.R
import kr.co.lion.androidproject_board.Tool
import kr.co.lion.androidproject_board.databinding.FragmentJoinBinding
import kr.co.lion.androidproject_board.viewmodel.JoinViewModel

class JoinFragment : Fragment() {

    lateinit var fragmentJoinBinding: FragmentJoinBinding
    lateinit var mainActivity: MainActivity
    lateinit var joinViewModel: JoinViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentJoinBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_join, container, false)
        joinViewModel = JoinViewModel()
        fragmentJoinBinding.joinViewModel = joinViewModel
        fragmentJoinBinding.lifecycleOwner = this

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
                    //mainActivity.hideSoftInput()

                    mainActivity.replaceFragment(FragmentName.ADD_USER_INFO_FRAGMENT, true, true, null)
                }
            }
        }
    }

    // 입력 요소 설정
    fun settingTextField() {
        // 입력 요소 초기화
        joinViewModel.apply {
            textFieldJoinId.value = ""
            textFieldJoinPassword.value = ""
            textFieldJoinPasswordCheck.value = ""
        }

        fragmentJoinBinding.apply {
            // 첫 번째 요소에 포커스
            Tool.showSoftInput(mainActivity, textFieldJoinId)

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
        val userId = joinViewModel.textFieldJoinId.value!!
        val userPw = joinViewModel.textFieldJoinPassword.value!!
        val userPwCheck = joinViewModel.textFieldJoinPasswordCheck.value!!
        var emptyView: View? = null

        fragmentJoinBinding.apply {
            // 아이디
            if(userId.trim().isEmpty()) {
                textInputLayoutJoinId.error = "아이디를 입력해주세요"

                if(emptyView == null) {
                    emptyView = textFieldJoinId
                }
            } else {
                textInputLayoutJoinId.isErrorEnabled = false
            }

            // 비밀번호
            if(userPw.trim().isEmpty()) {
                textInputLayoutJoinPassword.error = "비밀번호를 입력해주세요"

                if(emptyView == null) {
                    emptyView = textFieldJoinPassword
                }
            } else {
                textInputLayoutJoinPassword.isErrorEnabled = false
            }

            // 비밀번호 확인
            if(userPwCheck.trim().isEmpty()) {
                textInputLayoutJoinPasswordCheck.error = "비밀번호를 입력해주세요"

                if(emptyView == null) {
                    emptyView = textInputLayoutJoinPasswordCheck
                }
            } else if(userPw.trim() != userPwCheck.trim()) {
                textInputLayoutJoinPasswordCheck.error = "비밀번호가 일치하지 않습니다"

                return false
            } else {
                textInputLayoutJoinPasswordCheck.isErrorEnabled = false
            }

            if(emptyView != null) {
                Tool.showSoftInput(mainActivity, emptyView!!)

                return false
            }
        }

        return true
    }
}