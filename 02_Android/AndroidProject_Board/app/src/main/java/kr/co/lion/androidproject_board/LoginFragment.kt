package kr.co.lion.androidproject_board

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.androidproject_board.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    lateinit var fragmentLoginBinding: FragmentLoginBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentLoginBinding = FragmentLoginBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingToolbar()

        return fragmentLoginBinding.root
    }

    // 툴바 설정
    fun settingToolbar() {
        fragmentLoginBinding.apply {
            toolbarLogin.apply {
                title = "로그인"
            }
        }
    }
}