package kr.co.lion.androidproject4boardapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.androidproject4boardapp.ContentActivity
import kr.co.lion.androidproject4boardapp.Gender
import kr.co.lion.androidproject4boardapp.R
import kr.co.lion.androidproject4boardapp.Tools
import kr.co.lion.androidproject4boardapp.dao.UserDao
import kr.co.lion.androidproject4boardapp.databinding.FragmentModifyUserBinding
import kr.co.lion.androidproject4boardapp.model.UserModel
import kr.co.lion.androidproject4boardapp.viewmodel.ModifyUserViewModel


class ModifyUserFragment : Fragment() {

    lateinit var fragmentModifyUserBinding: FragmentModifyUserBinding
    lateinit var contentActivity: ContentActivity

    lateinit var modifyUserViewModel: ModifyUserViewModel

    // 서버에서 가져온 사용자 정보를 담고 있을 객체
    lateinit var originalUserModel:UserModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        // fragmentModifyUserBinding = FragmentModifyUserBinding.inflate(inflater)
        fragmentModifyUserBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_modify_user, container, false)
        modifyUserViewModel = ModifyUserViewModel()
        fragmentModifyUserBinding.modifyUserViewModel = modifyUserViewModel
        fragmentModifyUserBinding.lifecycleOwner = this

        contentActivity = activity as ContentActivity

        settingToolbarModifyUser()
        // settingInputForm()
        gettingUserData()

        return fragmentModifyUserBinding.root
    }

    // 툴바 설정
    fun settingToolbarModifyUser(){
        fragmentModifyUserBinding.apply {
            toolbarModifyUser.apply {
                // 타이틀
                title = "회원 정보 수정"

                // 네비게이션 아이콘 설정
                setNavigationIcon(R.drawable.menu_24px)
                setNavigationOnClickListener {
                    contentActivity.activityContentBinding.drawerLayoutContent.open()
                }

                // 메뉴
                inflateMenu(R.menu.menu_modify_user)
                setOnMenuItemClickListener {

                    when(it.itemId){
                        // 초기화
                        R.id.menuItemModifyUserReset ->{
                            settingInputForm()
                        }
                        // 완료
                        R.id.menuItemModifyUserDone ->{
                            // 유효성 감사를 한다.
                            val chk = checkInputForm()

                            if(chk == true){
                                // Tools.hideSoftInput(contentActivity)
                                updateUserData()
                            }
                        }
                    }
                    true
                }
            }
        }
    }

    // 입력 요소 초기화
    fun settingInputForm(){
        modifyUserViewModel.textFieldModifyUserInfoNickName.value = originalUserModel.userNickName
        modifyUserViewModel.textFieldModifyUserInfoAge.value = originalUserModel.userAge.toString()
        modifyUserViewModel.textFieldModifyUserPw.value = ""
        modifyUserViewModel.textFieldModifyUserPw2.value = ""

        when(originalUserModel.userGender){
            Gender.MALE.num -> modifyUserViewModel.settingGender(Gender.MALE)
            Gender.FEMALE.num -> modifyUserViewModel.settingGender(Gender.FEMALE)
        }

        modifyUserViewModel.checkBoxModifyUserInfoHobby1.value = originalUserModel.userHobby1
        modifyUserViewModel.checkBoxModifyUserInfoHobby2.value = originalUserModel.userHobby2
        modifyUserViewModel.checkBoxModifyUserInfoHobby3.value = originalUserModel.userHobby3
        modifyUserViewModel.checkBoxModifyUserInfoHobby4.value = originalUserModel.userHobby4
        modifyUserViewModel.checkBoxModifyUserInfoHobby5.value = originalUserModel.userHobby5
        modifyUserViewModel.checkBoxModifyUserInfoHobby6.value = originalUserModel.userHobby6

        modifyUserViewModel.onCheckBoxChanged()
    }

    // 입력 요소에 대한 유효성 검사
    fun checkInputForm():Boolean{
        // 입력 요소 값들을 가져온다.
        val nickName = modifyUserViewModel.textFieldModifyUserInfoNickName.value!!
        val age = modifyUserViewModel.textFieldModifyUserInfoAge.value!!
        val pw = modifyUserViewModel.textFieldModifyUserPw.value!!
        val pw2 = modifyUserViewModel.textFieldModifyUserPw2.value!!

        if(nickName.isEmpty()){
            Tools.showErrorDialog(contentActivity, fragmentModifyUserBinding.textFieldModifyUserInfoNickName,
                "닉네임 입력 오류", "닉네임을 입력해주세요")
            return false
        }

        if(age.isEmpty()){
            Tools.showErrorDialog(contentActivity, fragmentModifyUserBinding.textFieldModifyUserInfoAge,
                "나이 입력 오류", "나이를 입력해주세요")
            return false
        }

        // 비밀번호 둘 중 하나라도 비어있지 않고 서로 다르다면....
        if((pw.isNotEmpty() || pw2.isNotEmpty()) && pw != pw2){

            modifyUserViewModel.textFieldModifyUserPw.value = ""
            modifyUserViewModel.textFieldModifyUserPw2.value = ""

            Tools.showErrorDialog(contentActivity, fragmentModifyUserBinding.textFieldModifyUserPw,
                "비밀번호 입력 오류", "비밀번호가 다릅니다.")
            return false
        }

        return true
    }

    // 사용자 정보를 수정하는 메서드
    fun updateUserData(){
        // 1. 사용자가 입력한 데이터를 가져온다
        val userNickName = modifyUserViewModel.textFieldModifyUserInfoNickName.value!!
        val userAge = modifyUserViewModel.textFieldModifyUserInfoAge.value!!.toInt()
        val userGender = modifyUserViewModel.gettingGender()
        val userHobby1 = modifyUserViewModel.checkBoxModifyUserInfoHobby1.value!!
        val userHobby2 = modifyUserViewModel.checkBoxModifyUserInfoHobby2.value!!
        val userHobby3 = modifyUserViewModel.checkBoxModifyUserInfoHobby3.value!!
        val userHobby4 = modifyUserViewModel.checkBoxModifyUserInfoHobby4.value!!
        val userHobby5 = modifyUserViewModel.checkBoxModifyUserInfoHobby5.value!!
        val userHobby6 = modifyUserViewModel.checkBoxModifyUserInfoHobby6.value!!
        val userPw = modifyUserViewModel.textFieldModifyUserPw.value!!
        // 객체에 담는다.
        val userModel = UserModel(contentActivity.loginUserIdx, "", userPw, userNickName, userAge, userGender,
            userHobby1, userHobby2, userHobby3, userHobby4, userHobby5, userHobby6, 0)

        // 2. 정보를 수정한다.
        // 비밀번호가 새롭게 입력되어 있는지 값
        val chk = userPw.isNotEmpty()

        CoroutineScope(Dispatchers.Main).launch {
            // 수정메서드를 호출한다.
            UserDao.updateUserData(userModel, chk)

            Snackbar.make(fragmentModifyUserBinding.root, "수정되었습니다", Snackbar.LENGTH_SHORT).show()
            Tools.hideSoftInput(contentActivity)
        }
    }

    // 서버에서 사용자 데이터를 가져온다.
    fun gettingUserData(){
        CoroutineScope(Dispatchers.Main).launch{
            // 입력 요소들에 공백 문자열을 넣어준다.
            modifyUserViewModel.textFieldModifyUserInfoNickName.value = " "
            modifyUserViewModel.textFieldModifyUserInfoAge.value = " "
            modifyUserViewModel.textFieldModifyUserPw.value = ""
            modifyUserViewModel.textFieldModifyUserPw2.value = ""

            // 서버에서 데이터를 가져온다.
            originalUserModel = UserDao.gettingUserInfoByUserIdx(contentActivity.loginUserIdx)!!

            // 사용자 정보들을 뷰에 넣어준다.
            modifyUserViewModel.textFieldModifyUserInfoNickName.value = originalUserModel.userNickName
            modifyUserViewModel.textFieldModifyUserInfoAge.value = originalUserModel.userAge.toString()

            when(originalUserModel.userGender){
                Gender.MALE.num -> modifyUserViewModel.settingGender(Gender.MALE)
                Gender.FEMALE.num -> modifyUserViewModel.settingGender(Gender.FEMALE)
            }

            modifyUserViewModel.checkBoxModifyUserInfoHobby1.value = originalUserModel.userHobby1
            modifyUserViewModel.checkBoxModifyUserInfoHobby2.value = originalUserModel.userHobby2
            modifyUserViewModel.checkBoxModifyUserInfoHobby3.value = originalUserModel.userHobby3
            modifyUserViewModel.checkBoxModifyUserInfoHobby4.value = originalUserModel.userHobby4
            modifyUserViewModel.checkBoxModifyUserInfoHobby5.value = originalUserModel.userHobby5
            modifyUserViewModel.checkBoxModifyUserInfoHobby6.value = originalUserModel.userHobby6

            modifyUserViewModel.onCheckBoxChanged()
        }
    }
}











