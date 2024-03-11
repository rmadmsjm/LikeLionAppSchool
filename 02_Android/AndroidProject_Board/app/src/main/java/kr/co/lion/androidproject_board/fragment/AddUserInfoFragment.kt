package kr.co.lion.androidproject_board.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.snackbar.Snackbar
import kr.co.lion.androidproject_board.FragmentName
import kr.co.lion.androidproject_board.Gender
import kr.co.lion.androidproject_board.MainActivity
import kr.co.lion.androidproject_board.R
import kr.co.lion.androidproject_board.Tool
import kr.co.lion.androidproject_board.databinding.FragmentAddUserInfoBinding
import kr.co.lion.androidproject_board.viewmodel.AddUserInfoViewModel

class AddUserInfoFragment : Fragment() {

    lateinit var fragmentAddUserInfoBinding: FragmentAddUserInfoBinding
    lateinit var mainActivity: MainActivity
    lateinit var addUserInfoViewModel: AddUserInfoViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentAddUserInfoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_user_info, container, false)
        addUserInfoViewModel = AddUserInfoViewModel()
        fragmentAddUserInfoBinding.addUserInfoViewModel = addUserInfoViewModel
        fragmentAddUserInfoBinding.lifecycleOwner = this

        mainActivity = activity as MainActivity

        settingToolbar()
        settingTextField()
        settingButtonGender()
        settingCheckBox()
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
                    Tool.hideSoftInput(mainActivity)

                    mainActivity.replaceFragment(FragmentName.LOGIN_FRAGMENT, false, false, null)
                    mainActivity.removeFragment(FragmentName.JOIN_FRAGMENT)
                    mainActivity.removeFragment(FragmentName.ADD_USER_INFO_FRAGMENT)

                    Snackbar.make(fragmentAddUserInfoBinding.root, "회원가입이 완료되었습니다ㅇㅇㅇ", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    // 입력 요소 설정
    fun settingTextField() {
        // 입력 요소 초기화
        addUserInfoViewModel.apply {
            textFieldAddUserInfoNickname.value = ""
            textFieldAddUserInfoAge.value = ""
            settingGender(Gender.MALE)
        }

        fragmentAddUserInfoBinding.apply {
            // 첫 번째 요소에 포커스
            Tool.showSoftInput(mainActivity, textFieldAddUserInfoNickname)

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
        addUserInfoViewModel.settingGender(Gender.MALE)
    }

    // 체크박스 설정
    fun settingCheckBox() {
        addUserInfoViewModel.apply {
            checkBoxAddUserInfoHobbyExercise.value = false
            checkBoxAddUserInfoHobbyReading.value = false
            checkBoxAddUserInfoHobbyMovie.value = false
            checkBoxAddUserInfoHobbyCooking.value = false
            checkBoxAddUserInfoHobbyMusic.value = false
            checkBoxAddUserInfoHobbyEtc.value = false
        }
    }

    // 유효성 검사
    fun checkTextField() : Boolean {
        val userNickname = addUserInfoViewModel.textFieldAddUserInfoNickname.value!!
        val userAge = addUserInfoViewModel.textFieldAddUserInfoAge.value!!
        var emptyView: View? = null

        fragmentAddUserInfoBinding.apply {
            // 닉네임
            if(userNickname.trim().isEmpty()) {
                textInputLayoutAddUserInfoNickname.error = "닉네임을 입력해주세요"

                if(emptyView == null) {
                    emptyView = fragmentAddUserInfoBinding.textFieldAddUserInfoNickname
                }
            }
            //else {
                //textInputLayoutAddUserInfoNickname.isErrorEnabled = false
            //}

            // 나이
            if(userAge.trim().isEmpty()) {
                textInputLayoutAddUserInfoAge.error = "나이를 입력해주세요"

                if(emptyView == null) {
                    emptyView = fragmentAddUserInfoBinding.textFieldAddUserInfoAge
                }
            } //else {
               // textInputLayoutAddUserInfoAge.isErrorEnabled = false
           // }

            if(emptyView != null) {
                Tool.showSoftInput(mainActivity, emptyView!!)

                return false
            }

            return true
        }
    }
}