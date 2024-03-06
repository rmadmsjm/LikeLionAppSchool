package kr.co.lion.androidproject_board.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.androidproject_board.ContentActivity
import kr.co.lion.androidproject_board.ContentFragmentName
import kr.co.lion.androidproject_board.R
import kr.co.lion.androidproject_board.databinding.FragmentAddContentBinding
import kr.co.lion.androidproject_board.databinding.FragmentMainBinding

class AddContentFragment : Fragment() {

    lateinit var fragmentAddContentBinding: FragmentAddContentBinding
    lateinit var contentActivity: ContentActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentAddContentBinding = FragmentAddContentBinding.inflate(inflater)
        contentActivity = activity as ContentActivity

        settingToolbar()
        settingMenuAddContentBoardType()

        return fragmentAddContentBinding.root
    }

    // 툴바 설정
    fun settingToolbar() {
        fragmentAddContentBinding.apply {
            toolbarAddContent.apply {
                title = "게시글 작성"

                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    contentActivity.removeFragment(ContentFragmentName.ADD_CONTENT_FRAGMENT)
                }

                inflateMenu(R.menu.menu_add_content)
                setOnMenuItemClickListener {
                    when(it.itemId) {
                        R.id.menuItemAddContentCarmera -> {
                        }
                        R.id.menuItemAddContentAlbum -> {
                        }
                        R.id.menuItemAddContentReset -> {
                        }
                        R.id.menuItemAddContentSubmit -> {
                            contentActivity.replaceFragment(ContentFragmentName.READ_CONTENT_FRAGMENT, true, true, null)
                        }
                    }
                    true
                }
            }
        }
    }

    // 게시판 선택 메뉴 설정
    fun settingMenuAddContentBoardType() {
        fragmentAddContentBinding.apply {
            menuAddContentBoardType.apply {
                val items = arrayOf("자유 게시판", "유머 게시판", "시사 게시판", "스포츠 게시판")
                setSimpleItems(items)

                setText("자유 게시판", false)
            }
        }
    }
}