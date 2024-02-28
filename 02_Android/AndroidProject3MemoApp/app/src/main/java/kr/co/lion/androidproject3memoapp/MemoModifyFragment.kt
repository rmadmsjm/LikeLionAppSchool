package kr.co.lion.androidproject3memoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
                        // 메모 수정 완료 메뉴
                        R.id.menuItemMemoModifyDone -> {
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
}