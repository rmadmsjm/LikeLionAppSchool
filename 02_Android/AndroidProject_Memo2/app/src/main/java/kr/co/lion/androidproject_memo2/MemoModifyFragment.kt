package kr.co.lion.androidproject_memo2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.androidproject_memo2.databinding.FragmentMemoModifyBinding

class MemoModifyFragment : Fragment() {

    lateinit var fragmentMemoModifyBinding: FragmentMemoModifyBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentMemoModifyBinding = FragmentMemoModifyBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingToolbarMemoModify()
        settingTextField()

        return fragmentMemoModifyBinding.root
    }

    // 툴바 설정
    fun settingToolbarMemoModify() {
        fragmentMemoModifyBinding.apply {
            toolbarMemoModify.apply {
                title = "메모 수정"

                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(FragmentName.MEMO_MODIFY_FRAGMENT)
                }

                inflateMenu(R.menu.memo_modify_menu)
            }
        }
    }

    // 메모 내용 설정
    fun settingTextField() {
        fragmentMemoModifyBinding.apply {
            textFieldMemoModifyTitle.setText("제목")
            textFieldMemoModifyContent.setText("내용")
        }
    }
}