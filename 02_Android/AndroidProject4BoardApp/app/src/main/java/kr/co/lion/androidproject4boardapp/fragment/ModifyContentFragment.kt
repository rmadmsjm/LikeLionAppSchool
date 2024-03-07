package kr.co.lion.androidproject4boardapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import kr.co.lion.androidproject4boardapp.ContentActivity
import kr.co.lion.androidproject4boardapp.ContentFragmentName
import kr.co.lion.androidproject4boardapp.R
import kr.co.lion.androidproject4boardapp.databinding.FragmentModifyContentBinding

class ModifyContentFragment : Fragment() {

    lateinit var fragmentModifyContentBinding: FragmentModifyContentBinding
    lateinit var contentActivity: ContentActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentModifyContentBinding = FragmentModifyContentBinding.inflate(inflater)
        contentActivity = activity as ContentActivity

        settingToolbar()
        settingBackButton()

        return fragmentModifyContentBinding.root
    }

    // 툴바 설정
    fun settingToolbar() {
        fragmentModifyContentBinding.apply {
            toolbarModifyContent.apply {
                // 타이틀
                title = "게시글 수정"

                // 네비게이션
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    backProcess()
                }

                // 메뉴
                inflateMenu(R.menu.menu_modify_content)
                setOnMenuItemClickListener {
                    // 메뉴 id로 분기
                    when(it.itemId) {
                        // 카메라
                        R.id.menuItemModifyContentCamera -> {
                        }
                        // 앨범
                        R.id.menuItemModifyContentAlbum -> {
                        }
                        // 초기화
                        R.id.menuItemModifyContentReset -> {
                        }
                        // 완료
                        R.id.menuItemModifyContentDone -> {
                        }
                    }

                    true
                }
            }
        }
    }

    // BackButton 동작 설정
    fun settingBackButton() {
        contentActivity.onBackPressedDispatcher.addCallback {
            // 뒤로 가기 처리 메서드 호출
            backProcess()
            // 콜백 제거
            remove()
        }
    }

    // 뒤로 가기 처리
    fun backProcess() {
        contentActivity.removeFragment(ContentFragmentName.MODIFY_CONTENT_FRAGMENT)
    }
}