package kr.co.lion.androidproject_memo2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.androidproject_memo2.databinding.FragmentShowBinding

class ShowFragment : Fragment() {

    lateinit var fragmentShowBinding: FragmentShowBinding
    lateinit var mainActivity: MainActivity

    lateinit var memoModel: MemoModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentShowBinding = FragmentShowBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingData()
        settingToolbar()
        settingView()

        return fragmentShowBinding.root
    }

    // Data 설정
    fun settingData() {
        val idx = arguments?.getInt("position")!!

        memoModel = MemoDao.selectOneMemo(mainActivity, idx)
    }

    // 툴바
    fun settingToolbar() {
        fragmentShowBinding.apply {
            toolbarShow.apply {
                // 타이틀
                title = "메모 보기"

                // back
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(FragmentName.SHOW_FRAGMENT)
                }

                // 메뉴
                inflateMenu(R.menu.menu_show)
                // 메뉴 리스너
                setOnMenuItemClickListener {
                    // 메뉴 id로 분기
                    when(it.itemId) {
                        // 메모 수정
                        R.id.menuItemShowEdit -> {
                            mainActivity.replaceFragment(FragmentName.MODIFY_FRAGMENT, true, true, null)
                        }
                        // 메모 삭제
                        R.id.menuItemShowDelete -> {
                            mainActivity.removeFragment(FragmentName.SHOW_FRAGMENT)
                        }
                    }
                    true
                }
            }
        }
    }

    // View 설정
    fun settingView() {
        fragmentShowBinding.apply {
            // 메모 제목 비활성화
            textFieldShowTitle.isClickable = false
            textFieldShowTitle.isFocusable = false
            // 메모 내용 비활성화
            textFieldShowContent.isClickable = false
            textFieldShowContent.isFocusable = false

            textFieldShowTitle.setText(memoModel.title)
            textFieldShowContent.setText(memoModel.content)
            textViewShowDate.text = memoModel.date
        }
    }
}