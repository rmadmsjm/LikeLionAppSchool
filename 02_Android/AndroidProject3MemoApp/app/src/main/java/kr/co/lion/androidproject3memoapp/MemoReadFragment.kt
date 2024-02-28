package kr.co.lion.androidproject3memoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
                    // BackStack에서 제거해 이전 화면이 보이게 함
                    mainActivity.removeFragment(FragmentName.MEMO_READ_FRAGMENT)
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
                        }
                    }
                    true
                }
            }
        }
    }

    // TextField 내용 설정
    fun settingTextField() {
        fragmentMemoReadBinding.apply {
            textFieldMemoReadSubject.setText("제목")
            textFieldMemoReadWriteDate.setText("2024.02.28")
            textFieldMemoReadText.setText("내용")
        }
    }
}