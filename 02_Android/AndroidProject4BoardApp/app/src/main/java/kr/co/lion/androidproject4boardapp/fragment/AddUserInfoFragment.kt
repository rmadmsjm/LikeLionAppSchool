package kr.co.lion.androidproject4boardapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.androidproject4boardapp.MainActivity
import kr.co.lion.androidproject4boardapp.MainFragmentName
import kr.co.lion.androidproject4boardapp.R
import kr.co.lion.androidproject4boardapp.databinding.FragmentAddUserInfoBinding

class AddUserInfoFragment : Fragment() {

    lateinit var fragmentAddUserInfoBinding: FragmentAddUserInfoBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentAddUserInfoBinding = FragmentAddUserInfoBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingToolbar()
        settingButtonAddUserInfoSubmint()

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
                    mainActivity.removeFragment(MainFragmentName.ADD_USER_INFO_FRAGMENT)
                    mainActivity.removeFragment(MainFragmentName.JOIN_FRAGMENT)
                }
            }
        }
    }
}