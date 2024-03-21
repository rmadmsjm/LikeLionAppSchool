package kr.co.lion.androidproject4boardapp.fragment

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.androidproject4boardapp.ContentActivity
import kr.co.lion.androidproject4boardapp.ContentFragmentName
import kr.co.lion.androidproject4boardapp.ContentState
import kr.co.lion.androidproject4boardapp.ContentType
import kr.co.lion.androidproject4boardapp.R
import kr.co.lion.androidproject4boardapp.dao.ContentDao
import kr.co.lion.androidproject4boardapp.dao.UserDao
import kr.co.lion.androidproject4boardapp.databinding.FragmentReadContentBinding
import kr.co.lion.androidproject4boardapp.viewmodel.ReadContentViewModel

class ReadContentFragment : Fragment() {

    lateinit var fragmentReadContentBinding: FragmentReadContentBinding
    lateinit var contentActivity: ContentActivity
    lateinit var readContentViewModel: ReadContentViewModel

    // 현재 글 번호를 담을 변수
    var contentIdx = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        // fragmentReadContentBinding = FragmentReadContentBinding.inflate(inflater)
        fragmentReadContentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_read_content, container, false)
        readContentViewModel = ReadContentViewModel()
        fragmentReadContentBinding.readContentViewModel = readContentViewModel
        fragmentReadContentBinding.lifecycleOwner = this

        contentActivity = activity as ContentActivity

        // 글 번호 담기
        contentIdx = arguments?.getInt("contentIdx")!!

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
                // 모든 메뉴 보이지 않게 만듦
                // 글 정보를 가져온 다음 메뉴를 노출 시킴
                menu.findItem(R.id.menuItemReadContentReply).isVisible = false
                menu.findItem(R.id.menuItemReadContentModify).isVisible = false
                menu.findItem(R.id.menuItemReadContentDelete).isVisible = false

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
                            // 삭제 확인 다이얼로그
                            MaterialAlertDialogBuilder(contentActivity).apply {
                                setTitle("삭제하기")
                                setMessage("삭제하면 복원할 수 없습니다.\n삭제하시겠습니까?")
                                setNegativeButton("취소", null)
                                setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
                                    CoroutineScope(Dispatchers.Main).launch {
                                        // 글 상태 삭제로 변경
                                        ContentDao.updateContentState(contentIdx, ContentState.COTTENT_STATE_REMOVE)
                                        // 글 목록으로 돌아가기
                                        backProcess()
                                    }
                                }
                                show()
                            }
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
        // imageView 보이지 않게 함
        fragmentReadContentBinding.imageViewReadContent.visibility = View.INVISIBLE

        // 데이터를 받아오는데 걸리는 시간 때문에 입력 요소에 띄어쓰기
        readContentViewModel.textFieldReadContentSubject.value = " "
        readContentViewModel.textFieldReadContentType.value = " "
        readContentViewModel.textFieldReadContentNickname.value = " "
        readContentViewModel.textFieldReadContentDate.value = " "
        readContentViewModel.textFieldReadContentText.value = " "

        CoroutineScope(Dispatchers.Main).launch {
            // 현재 글 번호에 해당하는 글 데이터 가져오기
            val contentModel = ContentDao.selectContentData(contentIdx)

            // 로그인한 사용자와 글을 작성한 사용자의 일치에 따라 메뉴를 보여줌
            // 같을 경우 수정과 삭제 메뉴 보이도록 함
            if(contentActivity.loginUserIdx == contentModel?.contentWriterIdx) {
                fragmentReadContentBinding.toolbarReadContent.menu.findItem(R.id.menuItemReadContentModify).isVisible = true
                fragmentReadContentBinding.toolbarReadContent.menu.findItem(R.id.menuItemReadContentDelete).isVisible = true
            } else {
                fragmentReadContentBinding.toolbarReadContent.menu.findItem(R.id.menuItemReadContentReply).isVisible = true
            }

            // 글을 작성한 사용자의 번호를 통해 사용자 정보 가져오기
            val userModel = UserDao.gettingUserInfoByUserIdx(contentModel?.contentWriterIdx!!)

            // 가져온 데이터 보여주기
            readContentViewModel.textFieldReadContentSubject.value = contentModel?.contentSubject
            readContentViewModel.textFieldReadContentType.value = when(contentModel?.contentType) {
                ContentType.TYPE_FREE.num -> ContentType.TYPE_FREE.str
                ContentType.TYPE_HUMOR.num -> ContentType.TYPE_HUMOR.str
                ContentType.TYPE_SOCIETY.num -> ContentType.TYPE_SOCIETY.str
                ContentType.TYPE_SPORTS.num -> ContentType.TYPE_SPORTS.str
                else -> ""
            }
            readContentViewModel.textFieldReadContentNickname.value = userModel?.userNickname
            readContentViewModel.textFieldReadContentDate.value = contentModel?.contentWriteDate
            readContentViewModel.textFieldReadContentText.value = contentModel?.contentText

            // 이미지 데이터 보여주기
            if(contentModel?.contentImage != null) {
                ContentDao.gettingContentImage(contentActivity, contentModel.contentImage!!, fragmentReadContentBinding.imageViewReadContent)
            }
        }
    }
}