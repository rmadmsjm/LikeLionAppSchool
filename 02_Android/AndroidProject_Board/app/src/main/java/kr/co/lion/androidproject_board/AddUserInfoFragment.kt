package kr.co.lion.androidproject_board

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.androidproject_board.databinding.FragmentAddUserInfoBinding

class AddUserInfoFragment : Fragment() {

    lateinit var fragmentAddUserInfoBinding: FragmentAddUserInfoBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentAddUserInfoBinding = FragmentAddUserInfoBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingToolbar()
        settingView()

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

    // View 설정
    fun settingView() {
        fragmentAddUserInfoBinding.apply {
            buttonAddUserInfoSignUp.setOnClickListener {
                mainActivity.replaceFragment(FragmentName.LOGIN_FRAGMENT, false, false, null)
                mainActivity.removeFragment(FragmentName.JOIN_FRAGMENT)
                mainActivity.removeFragment(FragmentName.ADD_USER_INFO_FRAGMENT)
            }
        }
    }
}