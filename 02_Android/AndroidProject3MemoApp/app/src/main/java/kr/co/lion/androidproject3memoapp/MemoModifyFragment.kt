package kr.co.lion.androidproject3memoapp

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kr.co.lion.androidproject3memoapp.databinding.FragmentMemoModifyBinding

class MemoModifyFragment : Fragment() {

    lateinit var fragmentMemoModifyBinding: FragmentMemoModifyBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentMemoModifyBinding = FragmentMemoModifyBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingToolbar()
        settingTextField()

        return fragmentMemoModifyBinding.root
    }

    // 툴바 설정
    fun settingToolbar() {
        fragmentMemoModifyBinding.apply {
            toolbarMemoModify.apply {
                // 타이틀
                title = "메모 수정"

                // back
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(FragmentName.MEMO_MODIFY_FRAGMENT)
                }

                // 메뉴
                inflateMenu(R.menu.memo_modify_menu)
                // 메뉴 항목 리스너
                setOnMenuItemClickListener {
                    // 메뉴 항목 id로 분기
                    when(it.itemId) {
                        // 완료 메뉴
                        R.id.menuItemMemoModifyDone -> {
                            // 유효성 검사 메서드 호출
                            val chk = checkTextField()

                            // 모든 입력 요소가 입력되어 있을 경우, 수정 여부를 묻는 다이얼로그 띄우기
                            if(chk == true) {
                                val materialAlertDialogBuilder = MaterialAlertDialogBuilder(mainActivity)

                                materialAlertDialogBuilder.setTitle("수정 확인")
                                materialAlertDialogBuilder.setMessage("수정을 완료하면 이전 내용으로 복구할 수 없습니다")
                                materialAlertDialogBuilder.setNegativeButton("취소", null)
                                materialAlertDialogBuilder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
                                    // 키보드 내리고 포커스 제거
                                    mainActivity.hideSoftInput()

                                    // MemoReadFragment로 돌아가기
                                    mainActivity.removeFragment(FragmentName.MEMO_MODIFY_FRAGMENT)
                                }

                                materialAlertDialogBuilder.show()
                            }
                        }
                        // 초기화 메뉴
                        R.id.memoItemMemoModifyReset -> {
                            // 입력 요소 수정 전으로 초기화
                            textFieldMemoModifySubject.setText("제목")
                            textFieldMemoModifyText.setText("내용")

                            // 첫 번째 입력 요소에 포커스
                            mainActivity.showSoftInput(textFieldMemoModifySubject)
                        }
                    }
                    true
                }
            }
        }
    }

    // TextField 내용 설정
    fun settingTextField() {
        fragmentMemoModifyBinding.apply {
            textFieldMemoModifySubject.setText("제목")
            textFieldMemoModifyText.setText("내용")
        }
    }

    // 유효성 검사
    fun checkTextField() : Boolean {
        fragmentMemoModifyBinding.apply {
            // 제목
            if(textFieldMemoModifySubject.text.toString().trim().isEmpty()) {
                mainActivity.showErrorDialog(textFieldMemoModifySubject, "제목 입력 오류","제목을 입력해주세요")
                return false
            }
            // 내용
            if(textFieldMemoModifyText.text.toString().trim().isEmpty()) {
                mainActivity.showErrorDialog(textFieldMemoModifyText, "내용 입력 오류", "내용을 입력해주세요")
                return false
            }

            return true
        }
    }
}