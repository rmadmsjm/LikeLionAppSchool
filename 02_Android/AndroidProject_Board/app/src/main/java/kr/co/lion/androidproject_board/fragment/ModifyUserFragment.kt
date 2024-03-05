package kr.co.lion.androidproject_board.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.androidproject_board.ContentActivity
import kr.co.lion.androidproject_board.ContentFragmentName
import kr.co.lion.androidproject_board.R
import kr.co.lion.androidproject_board.databinding.FragmentModifyUserBinding

class ModifyUserFragment : Fragment() {

    lateinit var fragmentModifyUserBinding: FragmentModifyUserBinding
    lateinit var contentActivity: ContentActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentModifyUserBinding = FragmentModifyUserBinding.inflate(inflater)
        contentActivity = activity as ContentActivity

        settingToolbar()

        return fragmentModifyUserBinding.root
    }

    // 툴바 설정
    fun settingToolbar() {
        fragmentModifyUserBinding.apply {
            toolbarModifyUser.apply {
                title = "사용자 정보 수정"

                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    contentActivity.removeFragment(ContentFragmentName.MODIFY_USER_FRAGMENT)
                }

                inflateMenu(R.menu.menu_modify_user)
            }
        }
    }
}