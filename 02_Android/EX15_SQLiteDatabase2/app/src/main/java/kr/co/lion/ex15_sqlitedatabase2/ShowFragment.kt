package kr.co.lion.ex15_sqlitedatabase2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.ex15_sqlitedatabase2.databinding.FragmentShowBinding

class ShowFragment : Fragment() {

    lateinit var fragmentShowBinding: FragmentShowBinding
    lateinit var mainActivity: MainActivity

    // 학생 정보를 담을 객체
    lateinit var studentModel: StudentModel

    var idx: Int = -1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentShowBinding = FragmentShowBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingData()
        settingToolbar()
        settingView()

        return fragmentShowBinding.root
    }

    // 데이터 설정
    fun settingData() {
        // showBundle에서 넘겨준 idx 값 추출
        idx = arguments?.getInt("idx")!!

        // 학생 데이터 가져오기
        studentModel = StudentDao.selectOneStudent(mainActivity, idx)
    }

    // Toolbar
    fun settingToolbar() {
        fragmentShowBinding.apply {
            toolbarShow.apply {
                // 타이틀
                title = "학생 정보"

                // backBtn
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(FragmentName.SHOW_FRAGMENT)
                }
            }
        }
    }

    // View 설정
    fun settingView() {
        fragmentShowBinding.apply {
            textViewShow.apply {
                text = ""
                append("이름 : ${studentModel.name}\n")
                append("나이 : ${studentModel.age}\n")
                append("국어 점수 : ${studentModel.kor}점\n")
                append("영어 점수 : ${studentModel.eng}점\n")
                append("수학 점수 : ${studentModel.math}점\n")
            }
        }
    }
}