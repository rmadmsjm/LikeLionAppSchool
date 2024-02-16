package kr.co.lion.ex16_sqlitedatabase3_2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.ex16_sqlitedatabase3_2.databinding.FragmentInputBinding

class InputFragment : Fragment() {

    lateinit var fragmentInputBinding: FragmentInputBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentInputBinding = FragmentInputBinding.inflate(inflater)
        mainActivity = activity as MainActivity
        return fragmentInputBinding.root
    }

    // 데이터 저장
    fun saveMemoData() {
        fragmentInputBinding.apply {
            // 입력 내용 가져오기
            val memoSubject = textFieldSubject.text.toString()
            val memoText = textFieldText.text.toString()

            // 객체에 담기
            val memoModel = MemoModel(0, memoSubject, memoText)

            // 저장
            MemoDao.insertMemo(mainActivity, memoModel)
        }
    }
}