package kr.co.lion.androidproject_board.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.androidproject_board.ContentActivity
import kr.co.lion.androidproject_board.R
import kr.co.lion.androidproject_board.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var contentActivity: ContentActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentMainBinding = FragmentMainBinding.inflate(inflater)
        contentActivity = activity as ContentActivity

        settingToolbar()

        return fragmentMainBinding.root
    }

    // 툴바 설정
    fun settingToolbar() {
        fragmentMainBinding.apply {
            toolbarMain.apply {
                title = "전체 게시판"

                setNavigationIcon(R.drawable.menu_24px)
                setNavigationOnClickListener {
                    // NavigationDrawer
                    contentActivity.activityContentBinding.drawerLayoutContent.open()
                }

                inflateMenu(R.menu.menu_main)
            }
        }
    }
}