package kr.co.lion.androidproject_board.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.androidproject_board.ContentActivity
import kr.co.lion.androidproject_board.ContentFragmentName
import kr.co.lion.androidproject_board.R
import kr.co.lion.androidproject_board.databinding.FragmentModifyContentBinding

class ModifyContentFragment : Fragment() {

    lateinit var fragmentModifyContentBinding: FragmentModifyContentBinding
    lateinit var contentActivity: ContentActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentModifyContentBinding = FragmentModifyContentBinding.inflate(inflater)
        contentActivity = activity as ContentActivity

        settingToolbar()
        settingMenuModifyContnetBoardType()

        return fragmentModifyContentBinding.root
    }

    // 툴바 설정
    fun settingToolbar() {
        fragmentModifyContentBinding.apply {
            toolbarModifyContent.apply {
                title = "게시글 수정"

                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    contentActivity.removeFragment(ContentFragmentName.MODIFY_CONTENT_FRAGMENT)
                }

                inflateMenu(R.menu.menu_modify_content)
            }
        }
    }

    // 게시판 선택 메뉴 설정
    fun settingMenuModifyContnetBoardType() {
        fragmentModifyContentBinding.apply {
            menuModifyContentBoardType.apply {
                val items = arrayOf("자유 게시판", "유머 게시판", "시사 게시판", "스포츠 게시판")
                setSimpleItems(items)

                setText("자유 게시판", false)
            }
        }
    }
}