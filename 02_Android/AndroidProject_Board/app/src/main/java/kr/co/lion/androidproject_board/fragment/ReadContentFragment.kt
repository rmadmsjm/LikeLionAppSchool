package kr.co.lion.androidproject_board.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.androidproject_board.ContentActivity
import kr.co.lion.androidproject_board.ContentFragmentName
import kr.co.lion.androidproject_board.R
import kr.co.lion.androidproject_board.databinding.FragmentReadContentBinding

class ReadContentFragment : Fragment() {

    lateinit var fragmentReadContentBinding: FragmentReadContentBinding
    lateinit var contentActivity: ContentActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentReadContentBinding = FragmentReadContentBinding.inflate(inflater)
        contentActivity = activity as ContentActivity

        settingToolbar()

        return fragmentReadContentBinding.root
    }

    // 툴바 설정
    fun settingToolbar() {
        fragmentReadContentBinding.apply {
            toolbarReadContent.apply {
                title = "게시글 읽기"

                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    contentActivity.removeFragment(ContentFragmentName.READ_CONTENT_FRAGMENT)
                    contentActivity.removeFragment(ContentFragmentName.ADD_CONTENT_FRAGMENT)
                }

                inflateMenu(R.menu.menu_read_content)
                setOnMenuItemClickListener {
                    when(it.itemId) {
                        R.id.menuItemReadContentModify -> {
                            contentActivity.replaceFragment(ContentFragmentName.MODIFY_CONTENT_FRAGMENT, true, true, null)
                        }
                        R.id.menuItemReadContentDelete -> {
                        }
                    }
                    true
                }
            }
        }
    }
}