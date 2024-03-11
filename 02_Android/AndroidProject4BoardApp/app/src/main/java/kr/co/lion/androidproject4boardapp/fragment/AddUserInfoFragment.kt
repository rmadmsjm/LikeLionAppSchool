package kr.co.lion.androidproject4boardapp.fragment

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kr.co.lion.androidproject4boardapp.Gender
import kr.co.lion.androidproject4boardapp.MainActivity
import kr.co.lion.androidproject4boardapp.MainFragmentName
import kr.co.lion.androidproject4boardapp.R
import kr.co.lion.androidproject4boardapp.Tools
import kr.co.lion.androidproject4boardapp.databinding.FragmentAddUserInfoBinding
import kr.co.lion.androidproject4boardapp.viewmodel.AddUserInfoViewModel

class AddUserInfoFragment : Fragment() {

    lateinit var fragmentAddUserInfoBinding: FragmentAddUserInfoBinding
    lateinit var mainActivity: MainActivity
    lateinit var addUserInfoViewModel: AddUserInfoViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        // fragmentAddUserInfoBinding = FragmentAddUserInfoBinding.inflate(inflater)
        fragmentAddUserInfoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_user_info, container, false)
        addUserInfoViewModel = AddUserInfoViewModel()
        fragmentAddUserInfoBinding.addUserInfoViewModel = addUserInfoViewModel
        fragmentAddUserInfoBinding.lifecycleOwner = this

        mainActivity = activity as MainActivity

        settingToolbar()
        settingButtonAddUserInfoSubmint()
        settingInputUI()
        settingCheckBox()

        return fragmentAddUserInfoBinding.root
    }

    // 툴바 설정
    fun settingToolbar() {
        fragmentAddUserInfoBinding.apply {
            toolbarAddUserInfo.apply {
                // 타이틀
                title = "회원가입"

                // back
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    // 이전 화면으로 이동
                    mainActivity.removeFragment(MainFragmentName.ADD_USER_INFO_FRAGMENT)
                }
            }
        }
    }

    // 가입 버튼
    fun settingButtonAddUserInfoSubmint() {
        fragmentAddUserInfoBinding.apply {
            buttonAddUserInfoSubmit.apply {
                setOnClickListener {
                    // 유효성 검사 수행
                    val chk = checkInputForm()

                    // 모든 유효성 검사 통과했다면
                    if(chk == true) {
                        val materialAlertDialogBuilder = MaterialAlertDialogBuilder(mainActivity)
                        materialAlertDialogBuilder.setTitle("가입 완료")
                        materialAlertDialogBuilder.setMessage("가입이 완료되었습니다\n로그인해주세요")
                        materialAlertDialogBuilder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
                            mainActivity.removeFragment(MainFragmentName.ADD_USER_INFO_FRAGMENT)
                            mainActivity.removeFragment(MainFragmentName.JOIN_FRAGMENT)
                        }
                        materialAlertDialogBuilder.show()
                    }
                }
            }
        }
    }

    // 입력 요소 설저
    fun settingInputUI() {
        // 입력 요소 초기화
        addUserInfoViewModel.apply {
            textFieldAddUserInfoNickname.value = ""
            textFieldAddUserInfoAge.value = ""
            settingGender(Gender.MALE)
        }

        // 첫 번째 요소에 포커스
        Tools.showSoftInput(mainActivity, fragmentAddUserInfoBinding.textFieldAddUserInfoNickName)
    }

    // 체크박스 설정
    fun settingCheckBox() {
        // 모든 체크박스 초기화
        addUserInfoViewModel.apply {
            checkBoxAddUserInfoHobby1.value = false
            checkBoxAddUserInfoHobby2.value = false
            checkBoxAddUserInfoHobby3.value = false
            checkBoxAddUserInfoHobby4.value = false
            checkBoxAddUserInfoHobby5.value = false
            checkBoxAddUserInfoHobby6.value = false
        }
    }

    // 입력 요소 유효성 검사
    fun checkInputForm(): Boolean {
        // 입력한 값 가져오기
        val userNickname = addUserInfoViewModel.textFieldAddUserInfoNickname.value!!
        val userAge = addUserInfoViewModel.textFieldAddUserInfoAge.value!!

        // 유효성 검사
        if(userNickname.isEmpty()) {
            Tools.showErrorDialog(mainActivity, fragmentAddUserInfoBinding.textFieldAddUserInfoNickName, "닉네임 입력 오류", "닉네임을 입력해주세요")
            return false
        }
        if(userAge.isEmpty()) {
            Tools.showErrorDialog(mainActivity, fragmentAddUserInfoBinding.textFieldAddUserInfoAge, "나이 입력 오류", "나이를 입력해주세요")
            return false
        }

        return true
    }
}