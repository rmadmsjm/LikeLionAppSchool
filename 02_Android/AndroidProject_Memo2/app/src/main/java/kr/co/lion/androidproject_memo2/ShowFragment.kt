package kr.co.lion.androidproject_memo2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.androidproject_memo2.databinding.FragmentShowBinding

class ShowFragment : Fragment() {

    lateinit var fragmentShowBinding: FragmentShowBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentShowBinding = FragmentShowBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingToolbar()

        return fragmentShowBinding.root
    }

    // 툴바
    fun settingToolbar() {
        fragmentShowBinding.apply {
            toolbarShow.apply {
                // 타이틀
                title = "메모 보기"

                // back
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(FragmentName.SHOW_FRAGMENT)
                }

                // 메뉴
                inflateMenu(R.menu.menu_show)
                // 메뉴 리스너
                setOnMenuItemClickListener {
                    // 메뉴 id로 분기
                    when(it.itemId) {
                        // 메모 수정
                        R.id.menuItemShowEdit -> {
                            mainActivity.replaceFragment(FragmentName.MODIFY_FRAGMENT, true, true, null)
                        }
                        // 메모 삭제
                        R.id.menuItemShowDelete -> {
                            mainActivity.removeFragment(FragmentName.SHOW_FRAGMENT)
                        }
                    }
                    true
                }
            }
        }
    }
}