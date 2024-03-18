package kr.co.lion.androidproject4boardapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.androidproject4boardapp.MainActivity
import kr.co.lion.androidproject4boardapp.MainFragmentName
import kr.co.lion.androidproject4boardapp.R
import kr.co.lion.androidproject4boardapp.Tools
import kr.co.lion.androidproject4boardapp.dao.UserDao
import kr.co.lion.androidproject4boardapp.databinding.FragmentJoinBinding
import kr.co.lion.androidproject4boardapp.viewmodel.JoinViewModel

class JoinFragment : Fragment() {

    lateinit var fragmentJoinBinding: FragmentJoinBinding
    lateinit var mainActivity: MainActivity
    lateinit var joinViewModel: JoinViewModel

    // 아이디 중복 검사 프로퍼티
    // true : 아이디 중복 검사 완료
    var checkUserIdExist = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        // fragmentJoinBinding = FragmentJoinBinding.inflate(inflater)

        // 바인딩 객체 생성, ViewBinding 기능을 포함함
        // 첫 번째 : LayoutInflater
        // 두 번째 : 화면을 만들 때 사용할 layout 폴더의 xml 파일
        // 세 번째 : xml을 통해서 만들어진 화면을 누가 관리하게 할 것인지 설정, 여기서는 Fragment를 의미함
        // 네 번째 : Fragment 상태에 영향을 받을 것인가, Fragment가 영향을 받으면 하위 Fragment도 영향을 받을 것인가(?)
        fragmentJoinBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_join, container, false)
        // ViewModel 객체 생성
        joinViewModel = JoinViewModel()
        // 생성한 ViewModel 객체를 layout 파일에 설정하기
        fragmentJoinBinding.joinViewModel = joinViewModel
        // ViewModel의 생명 주기를 Fragment와 일치시키기
        // Fragment가 살아 있을 때 ViewModel 객체도 살아 있게 해줌
        fragmentJoinBinding.lifecycleOwner = this

        mainActivity = activity as MainActivity

        settingToolbar()
        settingButtonJoinNext()
        settingTextField()
        settingButtonJoinCheckId()

        return fragmentJoinBinding.root
    }

    // 툴바 설정
    fun settingToolbar() {
        // 타이틀 설정
        joinViewModel.toolbarJoinTitle.value = "회원가입"
        // 타이틓 setNavigationIcon
        joinViewModel.toolbarJoinNavigationIcon.value = R.drawable.arrow_back_24px

        fragmentJoinBinding.apply {
            toolbarJoin.apply {
                // 타이틀
                // title = "회원가입"

                // back
                // setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    // 이전 화면으로 이동
                    mainActivity.removeFragment(MainFragmentName.JOIN_FRAGMENT)
                }
            }
        }
    }

    // 다음 버튼
    fun settingButtonJoinNext() {
        fragmentJoinBinding.apply {
            buttonJoinNext.apply {
                setOnClickListener {
                    // 유효성 검사
                    val chk = checkTextInput()

                    // 입력이 모두 잘 되어있을 경우
                    if(chk == true) {
                        // 키보를 내리기
                        // Tools.hideSoftInput(mainActivity)

                        // AddUserInfoFragment 보이게 함
                        // mainActivity.replaceFragment(MainFragmentName.ADD_USER_INFO_FRAGMENT, true, true, null)

                        joinNext()
                    }
                }
            }
        }
    }

    // 입력 요소 초기 설정
    fun settingTextField() {
        joinViewModel.apply {
            // 입력 요소 초기화
            textFieldJoinUserId.value = ""
            textFieldJoinUserPw.value = ""
            textFieldJoinUserPw2.value = ""
        }

        // 첫 번째 입력 요소에 포커스 주기
        Tools.showSoftInput(mainActivity, fragmentJoinBinding.textFieldJoinUserId)

        // 아이디 입력 요소의 값을 변경할 시, 중복 확인 여부 변수값을 false로 설정
        fragmentJoinBinding.textFieldJoinUserId.addTextChangedListener {
            checkUserIdExist = false
        }
    }

    // 입력 요소 유효성 검사
    fun checkTextInput(): Boolean {
        // 사용자가 입력한 내용 가져오기
        val userId = joinViewModel.textFieldJoinUserId.value!!
        val userPw = joinViewModel.textFieldJoinUserPw.value!!
        val userPw2 = joinViewModel.textFieldJoinUserPw2.value!!

        // 아이디를 입력하지 않은 경우
        if(userId.isEmpty()) {
            Tools.showErrorDialog(mainActivity, fragmentJoinBinding.textFieldJoinUserId, "아이디 입력 오류", "아이디를 입력해주세요")
            return false
        }

        // 비밀번호를 입력하지 않은 경우
        if(userPw.isEmpty()) {
            Tools.showErrorDialog(mainActivity, fragmentJoinBinding.textFieldJoinUserPw, "비밀번호 입력 오류", "비밀번호를 입력해주세요")
            return false
        }

        // 비밀번호 확인을 입력하지 않은 경우
        if(userPw2.isEmpty()) {
            Tools.showErrorDialog(mainActivity, fragmentJoinBinding.textFieldJoinUserPw2, "비밀번호 입력 오류", "비밀번호를 입력해주세요")
            return false
        }

        // 입력한 비밀번호가 서로 일치하지 않는 경우
        if(userPw != userPw2) {
            Tools.showErrorDialog(mainActivity, fragmentJoinBinding.textFieldJoinUserPw2, "비밀번호 입력 오류", "비밀번호가 일치하지 않습니다")
            return false
        }

        // 아이디 중복 확인을 하지 않은 경우
        if(checkUserIdExist == false) {
            Tools.showErrorDialog(mainActivity, fragmentJoinBinding.textFieldJoinUserId, "아이디 중복 확인 오류", "아이디 중복 확인을 해주세요")
            return false
        }

        return true
    }

    // 아이디 중복 확인
    fun settingButtonJoinCheckId() {
        fragmentJoinBinding.apply {
            buttonJoinCheckId.apply {
                setOnClickListener {
                    CoroutineScope(Dispatchers.IO).launch {
                        checkUserIdExist = UserDao.checkUserIdExist(joinViewModel?.textFieldJoinUserId?.value!!)
                        // Log.d("test1234", "$checkUserIdExist")

                        if(checkUserIdExist == false) {
                            joinViewModel?.textFieldJoinUserId?.value = ""
                            Tools.showErrorDialog(mainActivity, fragmentJoinBinding.textFieldJoinUserId,
                                "아이디 입력 오류", "존재하는 아이디 입니다\n다른 아이디로 입력해주세요")
                        } else {
                            val materialAlertDialogBuilder = MaterialAlertDialogBuilder(mainActivity)
                            materialAlertDialogBuilder.setTitle("아이디 중복 확인")
                            materialAlertDialogBuilder.setMessage("사용 가능한 아이디 입니다")
                            materialAlertDialogBuilder.setPositiveButton("확인", null)
                            materialAlertDialogBuilder.show()
                        }
                    }
                    // checkUserIdExist = true
                }
            }
        }
    }


    // 다음 회원 정보 입력으로 이동
    fun joinNext() {
        // 사용자가 입력한 데이터 담기
        val joinBundle = Bundle()
        joinBundle.putString("joinUserId", joinViewModel.textFieldJoinUserId.value!!)
        joinBundle.putString("joinUserPw", joinViewModel.textFieldJoinUserPw.value!!)

        // 키보를 내리기
        Tools.hideSoftInput(mainActivity)

        // AddUserInfoFragment 보이게 함
        mainActivity.replaceFragment(MainFragmentName.ADD_USER_INFO_FRAGMENT, true, true, null)
    }
}