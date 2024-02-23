package kr.co.lion.androidproject_memo2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.androidproject_memo2.databinding.FragmentInputBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class InputFragment : Fragment() {

    lateinit var fragmentInputBinding: FragmentInputBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentInputBinding = FragmentInputBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingToolbar()

        return fragmentInputBinding.root
    }

    // 툴바
    fun settingToolbar() {
        fragmentInputBinding.apply {
            toolbarInput.apply {
                // 타이틀
                title = "메모 등록"

                // back
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(FragmentName.INPUT_FRAGMENT)
                }

                // 메뉴
                inflateMenu(R.menu.menu_input)
                // 메뉴 리스너
                setOnMenuItemClickListener {
                    // 메뉴 id로 분기
                    when(it.itemId) {
                        // 메모 등록
                        R.id.menuItemInputDone -> {
                            inputDone()
                        }
                    }
                    true
                }
            }
        }
    }

    // 입력 완료 메서드
    fun inputDone() {
        fragmentInputBinding.apply {
            val title = textFieldInputTitle.text.toString()
            val content = textFieldInputContent.text.toString()

            val dateFormat = SimpleDateFormat("yyyy.MM.dd EEEE", Locale.getDefault())
            val currentDate = dateFormat.format(Date(System.currentTimeMillis()))
            val date = currentDate

            val memoModel = MemoModel(0, title, content, date)

            MemoDao.insertMemo(mainActivity, memoModel)

            mainActivity.removeFragment(FragmentName.INPUT_FRAGMENT)
            mainActivity.replaceFragment(FragmentName.SHOW_FRAGMENT, true, true, null)
        }
    }
}