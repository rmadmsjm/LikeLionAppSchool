package kr.co.lion.androidproject_memo2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.androidproject_memo2.databinding.FragmentModifyBinding

class ModifyFragment : Fragment() {

    lateinit var fragmentModifyBinding: FragmentModifyBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentModifyBinding = FragmentModifyBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingToolbar()

        return fragmentModifyBinding.root
    }

    // 툴바
    fun settingToolbar() {
        fragmentModifyBinding.apply {
            toolbarModify.apply {
                // 타이틀
                title = "메모 수정"

                // back
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(FragmentName.MODIFY_FRAGMENT)
                }

                // 메뉴
                inflateMenu(R.menu.menu_modify)
                // 메뉴 리스너
                setOnMenuItemClickListener {
                    true
                }
            }
        }
    }
}