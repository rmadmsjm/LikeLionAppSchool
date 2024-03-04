package kr.co.lion.androidproject_board

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import kr.co.lion.androidproject_board.databinding.FragmentAddUserInfoBinding

class AddUserInfoFragment : Fragment() {

    lateinit var fragmentAddUserInfoBinding: FragmentAddUserInfoBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentAddUserInfoBinding = FragmentAddUserInfoBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingToolbar()
        settingTextField()
        settingButtonGender()
        settingButtonAddUserInfoSignUp()

        return fragmentAddUserInfoBinding.root
    }

    // 툴바 설정
    fun settingToolbar() {
        fragmentAddUserInfoBinding.apply {
            toolbarAddUserInfo.apply {
                title = "회원가입"

                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(FragmentName.ADD_USER_INFO_FRAGMENT)
                }
            }
        }
    }

    // 가입 Button 설정
    fun settingButtonAddUserInfoSignUp() {
        fragmentAddUserInfoBinding.apply {
            buttonAddUserInfoSignUp.setOnClickListener {
                val chk = checkTextField()

                if(chk == true) {
                    // 키보드 내리기
                    mainActivity.hideSoftInput()

                    mainActivity.replaceFragment(FragmentName.LOGIN_FRAGMENT, false, false, null)
                    mainActivity.removeFragment(FragmentName.JOIN_FRAGMENT)
                    mainActivity.removeFragment(FragmentName.ADD_USER_INFO_FRAGMENT)
                }
            }
        }
    }

    // 입력 요소 설정
    fun settingTextField() {
        fragmentAddUserInfoBinding.apply {
            // 첫 번째 요소에 포커스
            mainActivity.showSoftInput(textFieldAddUserInfoNickname)

            // 에러 메시지가 보여지는 상황에서 값을 입력했을 때 에러 메세지 없애기
            textFieldAddUserInfoNickname.addTextChangedListener {
                textInputLayoutAddUserInfoNickname.isErrorEnabled = false
            }
            textFieldAddUserInfoAge.addTextChangedListener {
                textInputLayoutAddUserInfoAge.isErrorEnabled = false
            }
        }
    }

    // 성별 Button 설정
    fun settingButtonGender() {
        fragmentAddUserInfoBinding.apply {
            buttonToggleGroupAddUserInfoGender.check(R.id.buttonAddUserInfoGenderMale)
        }
    }

    // 유효성 검사
    fun checkTextField() : Boolean {
        fragmentAddUserInfoBinding.apply {
            var emptyView: View? = null

            // 닉네임
            if(textFieldAddUserInfoNickname.text.toString().trim().isEmpty()) {
                textInputLayoutAddUserInfoNickname.error = "닉네임을 입력해주세요"

                if(emptyView == null) {
                    emptyView = textFieldAddUserInfoNickname
                }
            } else {
                textInputLayoutAddUserInfoNickname.isErrorEnabled = false
            }

            // 나이
            if(textFieldAddUserInfoAge.text.toString().trim().isEmpty()) {
                textInputLayoutAddUserInfoAge.error = "나이를 입력해주세요"

                if(emptyView == null) {
                    emptyView = textFieldAddUserInfoAge
                }
            } else {
                textInputLayoutAddUserInfoAge.isErrorEnabled = false
            }

            if(emptyView != null) {
                mainActivity.showSoftInput(emptyView)

                return false
            }

            return true
        }
    }
}