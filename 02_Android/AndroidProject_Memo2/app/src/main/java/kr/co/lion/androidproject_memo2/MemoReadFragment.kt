package kr.co.lion.androidproject_memo2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.androidproject_memo2.databinding.FragmentMemoReadBinding

class MemoReadFragment : Fragment() {

    lateinit var fragmentMemoReadBinding: FragmentMemoReadBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentMemoReadBinding = FragmentMemoReadBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingToolbarMemoRead()
        settingTextField()

        return fragmentMemoReadBinding.root
    }

    // 툴바 설정
    fun settingToolbarMemoRead() {
        fragmentMemoReadBinding.apply {
            toolbarMemoRead.apply {
                title = "메모 보기"

                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(FragmentName.MEMO_READ_FRAGMENT)
                }

                inflateMenu(R.menu.memo_read_menu)
                setOnMenuItemClickListener {
                    when(it.itemId) {
                        R.id.menuItemMemoReadModify -> {
                            mainActivity.replaceFragment(FragmentName.MEMO_MODIFY_FRAGMENT, true, true, null)
                        }
                        R.id.menuItemMemoReadDelete -> {
                        }
                    }

                    true
                }
            }
        }
    }

    // 메모 내용 설정
    fun settingTextField() {
        fragmentMemoReadBinding.apply {
            textFieldMemoReadTitle.setText("제목")
            textFieldMemoReadDate.setText("2024.03.02")
            textFieldMEmoReadContent.setText("내용")
        }
    }
}