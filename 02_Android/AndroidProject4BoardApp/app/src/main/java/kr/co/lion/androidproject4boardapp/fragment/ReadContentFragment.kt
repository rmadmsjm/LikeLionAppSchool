package kr.co.lion.androidproject4boardapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import kr.co.lion.androidproject4boardapp.ContentActivity
import kr.co.lion.androidproject4boardapp.ContentFragmentName
import kr.co.lion.androidproject4boardapp.R
import kr.co.lion.androidproject4boardapp.databinding.FragmentReadContentBinding
import kr.co.lion.androidproject4boardapp.viewmodel.ReadContentViewModel

class ReadContentFragment : Fragment() {

    lateinit var fragmentReadContentBinding: FragmentReadContentBinding
    lateinit var contentActivity: ContentActivity
    lateinit var readContentViewModel: ReadContentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        // fragmentReadContentBinding = FragmentReadContentBinding.inflate(inflater)
        fragmentReadContentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_read_content, container, false)
        readContentViewModel = ReadContentViewModel()
        fragmentReadContentBinding.readContentViewModel = readContentViewModel
        fragmentReadContentBinding.lifecycleOwner = this

        contentActivity = activity as ContentActivity

        settingToolbar()
        settingBackButton()
        settingInputForm()

        return fragmentReadContentBinding.root
    }

    // 툴바 설정
    fun settingToolbar() {
        fragmentReadContentBinding.apply {
            toolbarReadContent.apply {
                // 타이틀
                title = "게시글 읽기"

                // 네비게이션
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    backProcess()
                }

                // 메뉴
                inflateMenu(R.menu.menu_read_content)
                setOnMenuItemClickListener {
                    // 메뉴 id로 분기
                    when(it.itemId) {
                        // 댓글
                        R.id.menuItemReadContentReply -> {
                            // 댓글 보여줄 BottomSheet 띄우기
                            showBottomSheet()
                        }
                        // 수정
                        R.id.menuItemReadContentModify -> {
                            // 수정 화면으로 이동
                            contentActivity.replaceFragment(ContentFragmentName.MODIFY_CONTENT_FRAGMENT, true, true, null)
                        }
                        // 삭제
                        R.id.menuItemReadContentDelete -> {
                        }
                    }
                    true
                }
            }
        }
    }

    // BackButton 동작 설정
    // 설정 안 하면 뒤로 갔을 때 AddContentFragment가 보임
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
        contentActivity.removeFragment(ContentFragmentName.READ_CONTENT_FRAGMENT)
        contentActivity.removeFragment(ContentFragmentName.ADD_CONTENT_FRAGMENT)
    }

    // 댓글 보여줄 BottomSheet 띄우기
    fun showBottomSheet() {
        val readContentBottomFragment = ReadContentBottomFragment()
        readContentBottomFragment.show(contentActivity.supportFragmentManager, "ReplyBottomSheet")
    }

    // 입력 요소 설정
    fun settingInputForm() {
        readContentViewModel.textFieldReadContentSubject.value = "제목"
        readContentViewModel.textFieldReadContentSubject.value = "자유 게시판"
        readContentViewModel.textFieldReadContentNickname.value = "닉네임"
        readContentViewModel.textFieldReadContentDate.value = "2024-03-12"
        readContentViewModel.textFieldReadContentText.value = "내용"
    }
}