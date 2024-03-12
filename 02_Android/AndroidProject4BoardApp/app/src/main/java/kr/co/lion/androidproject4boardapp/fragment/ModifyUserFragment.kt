package kr.co.lion.androidproject4boardapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import kr.co.lion.androidproject4boardapp.ContentActivity
import kr.co.lion.androidproject4boardapp.ContentFragmentName
import kr.co.lion.androidproject4boardapp.Gender
import kr.co.lion.androidproject4boardapp.R
import kr.co.lion.androidproject4boardapp.Tools
import kr.co.lion.androidproject4boardapp.databinding.FragmentModifyUserBinding
import kr.co.lion.androidproject4boardapp.viewmodel.ModifyUserViewModel

class ModifyUserFragment : Fragment() {

    lateinit var fragmentModifyUserBinding: FragmentModifyUserBinding
    lateinit var contentActivity: ContentActivity
    lateinit var modifyUserViewModel: ModifyUserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        //fragmentModifyUserBinding = FragmentModifyUserBinding.inflate(inflater)
        fragmentModifyUserBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_modify_user, container, false)
        modifyUserViewModel = ModifyUserViewModel()
        fragmentModifyUserBinding.modifyUserViewModel = modifyUserViewModel
        fragmentModifyUserBinding.lifecycleOwner = this

        contentActivity = activity as ContentActivity

        settingToolbar()
        settingInputForm()

        return fragmentModifyUserBinding.root
    }

    // 툴바 설정
    fun settingToolbar() {
        fragmentModifyUserBinding.apply {
            toolbarModifyUser.apply {
                // 타이틀
                title = "사용자 정보 수정"

                // 네비게이션
                setNavigationIcon(R.drawable.menu_24px)
                setNavigationOnClickListener {
                    contentActivity.activityContentBinding.drawerLayoutContent.open()
                }

                // 메뉴
                inflateMenu(R.menu.menu_modify_user)
                setOnMenuItemClickListener {
                    // 메뉴 id로 분기
                    when(it.itemId) {
                        // 초기화
                        R.id.menuItemModifyContentReset -> {
                            settingInputForm()
                        }
                        // 완료
                        R.id.menuItemModifyUserDone -> {
                            val chk = checkInputForm()

                            if(chk == true) {
                                Tools.hideSoftInput(contentActivity)
                            }
                        }
                    }

                    true
                }
            }
        }
    }

    // 입력 요소 초기화
    fun settingInputForm() {
        modifyUserViewModel.textFieldModifyUserInfoNickName.value = "닉네임"
        modifyUserViewModel.textFieldModifyUserInfoAge.value = "20"
        modifyUserViewModel.textFieldModifyUserPw.value = ""
        modifyUserViewModel.textFieldModifyUserPw2.value = ""
        modifyUserViewModel.settingGender(Gender.FEMALE)

        modifyUserViewModel.checkBoxModifyUserInfoHobby1.value = true
        modifyUserViewModel.checkBoxModifyUserInfoHobby2.value = true
        modifyUserViewModel.checkBoxModifyUserInfoHobby3.value = false
        modifyUserViewModel.checkBoxModifyUserInfoHobby4.value = true
        modifyUserViewModel.checkBoxModifyUserInfoHobby5.value = true
        modifyUserViewModel.checkBoxModifyUserInfoHobby6.value = false

        modifyUserViewModel.onCheckBoxChanged()
    }

    // 유효성 검사
    fun checkInputForm(): Boolean {
        // 입력한 값 가져오기
        val nickname = modifyUserViewModel.textFieldModifyUserInfoNickName.value!!
        val age = modifyUserViewModel.textFieldModifyUserInfoAge.value!!
        val pw = modifyUserViewModel.textFieldModifyUserPw.value!!
        val pw2 = modifyUserViewModel.textFieldModifyUserPw2.value!!

        if(nickname.isEmpty()) {
            Tools.showErrorDialog(contentActivity, fragmentModifyUserBinding.textFieldModifyUserInfoNickName, "닉네임 입력 오류", "닉네임을 입력해주세요")
            return false
        }

        if(age.isEmpty()) {
            Tools.showErrorDialog(contentActivity, fragmentModifyUserBinding.textFieldModifyUserInfoAge, "나이 입력 오류", "나이를 입력해주세요")
            return false
        }

        // 둘 중 하나라도 비어있지 않고 서로 다르면
        if((pw.isNotEmpty() || pw2.isNotEmpty()) && pw != pw2) {

            modifyUserViewModel.textFieldModifyUserPw.value = ""
            modifyUserViewModel.textFieldModifyUserPw2.value = ""

            Tools.showErrorDialog(contentActivity, fragmentModifyUserBinding.textFieldModifyUserPw, "비밀번호 입력 오류", "비밀번호가 일치하지 않습니다")
            return false
        }

        return true
    }
}