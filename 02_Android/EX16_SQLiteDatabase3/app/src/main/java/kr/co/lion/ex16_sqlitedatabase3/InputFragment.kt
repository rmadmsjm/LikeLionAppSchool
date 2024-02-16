package kr.co.lion.ex16_sqlitedatabase3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.ex16_sqlitedatabase3.databinding.FragmentInputBinding

class InputFragment : Fragment() {

    lateinit var fragmentInputBinding: FragmentInputBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentInputBinding = FragmentInputBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingView()

        return inflater.inflate(R.layout.fragment_input, container, false)
    }

    // View 설정
    fun settingView() {
        fragmentInputBinding.apply {
            buttonInputFragmentSubmit.setOnClickListener {
                inputDone()
            }
        }
    }

    // 입력 완료 메서드
    fun inputDone() {
        fragmentInputBinding.apply {
            val title = textFieldInputFragmentTitle.text.toString()
            val content = textFieldInputFragmentContent.text.toString()

            // 데이터 유효성 검사
            if(title.isEmpty()) {
                textLayoutInputFragmentTitle.error = "제목을 입력해주세요"
                if(content.isEmpty()) {
                    textLayoutInputFragmentContent.error = "내용을 입력해주세요"
                }
                return
            }
            if(content.isEmpty()) {
                textLayoutInputFragmentContent.error = "내용을 입력해주세요"
                return
            }

            // 객체에 담기
            val memoModel = MemoModel(0, title, content)

            // 데이터베이스에 저장
            MemoDao.insertMemo(mainActivity, memoModel)

            mainActivity.removeFragment(MainActivity.FragmentName.INPUT_FRAGMENT)
        }
    }
}