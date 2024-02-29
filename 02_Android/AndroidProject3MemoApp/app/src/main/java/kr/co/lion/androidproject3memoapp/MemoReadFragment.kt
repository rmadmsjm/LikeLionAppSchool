package kr.co.lion.androidproject3memoapp

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kr.co.lion.androidproject3memoapp.databinding.FragmentMemoReadBinding

class MemoReadFragment : Fragment() {

    lateinit var fragmentMemoReadBinding: FragmentMemoReadBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentMemoReadBinding = FragmentMemoReadBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingToolbar()
        settingTextField()

        return fragmentMemoReadBinding.root
    }

    // 툴바 설정
    fun settingToolbar() {
        fragmentMemoReadBinding.apply {
            toolbarMemoRead.apply {
                // 타이틀
                title = "메모 보기"

                // back
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    backProcess()
                }

                // 메뉴
                inflateMenu(R.menu.memo_read_menu)
                // 메뉴 항목 리스너
                setOnMenuItemClickListener {
                    // 메뉴 항목 id로 분기
                    when(it.itemId) {
                        // 메모 수정 메뉴
                        R.id.menuItemMemoReadModify -> {
                            mainActivity.replaceFragment(FragmentName.MEMO_MODIFY_FRAGMENT, true, true, null)
                        }
                        // 메모 삭제 메뉴
                        R.id.menuItemMemoReadDelete -> {
                            // 삭제 다이얼로그
                            val materialAlertDialogBuilder = MaterialAlertDialogBuilder(mainActivity)
                            materialAlertDialogBuilder.setTitle("메모 삭제")
                            materialAlertDialogBuilder.setMessage("메모를 삭제하면 복구할 수 없습니다")
                            materialAlertDialogBuilder.setNegativeButton("취소", null)
                            materialAlertDialogBuilder.setPositiveButton("삭제") { dialogInterface: DialogInterface, i: Int ->
                                // MainFragment로 돌아가기
                                backProcess()
                            }
                            materialAlertDialogBuilder.show()
                        }
                    }
                    true
                }
            }
        }
    }

    // TextField 내용 설정
    fun settingTextField() {
        // 데이터 memoIdx 가져오기
        val memoIdx = arguments?.getInt("memoIdx")!!

        // 현재 데이터 가져오기
        val memoModel = MemoDao.selectMemoDataOne(mainActivity, memoIdx)

        fragmentMemoReadBinding.apply {
            textFieldMemoReadSubject.setText(memoModel.memoSubject)
            textFieldMemoReadWriteDate.setText(memoModel.memoDate)
            textFieldMemoReadText.setText(memoModel.memoText)
        }
    }

    // 뒤로 가기 처리
    fun backProcess() {
        // BackStack에서 제거해 이전 화면이 보이게 함
        mainActivity.removeFragment(FragmentName.MEMO_READ_FRAGMENT)
        // MemoReadFragment에서 뒤로 갔을 때 MainFragment로 갈 수 있도록 BackStack에서 MemoAddFragment 제거
        mainActivity.removeFragment(FragmentName.MEMO_ADD_FRAGMENT)
    }
}