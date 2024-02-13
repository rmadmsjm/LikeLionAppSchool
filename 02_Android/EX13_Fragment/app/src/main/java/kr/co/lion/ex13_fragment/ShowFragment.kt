package kr.co.lion.ex13_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.ex13_fragment.databinding.FragmentShowBinding

class ShowFragment : Fragment() {

    lateinit var fragmentShowBinding: FragmentShowBinding
    lateinit var mainActivity: MainActivity

    // RecyclerView position
    var position: Int = -1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentShowBinding = FragmentShowBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        // RecyclerView position 가져오기
        position = arguments?.getInt("position", -1)!!

        settingToolbar()
        settingView()

        return fragmentShowBinding.root
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
                append("이름 : ${mainActivity.studentInfoList[position].name}\n")
                append("나이 : ${mainActivity.studentInfoList[position].age}\n")
                append("국어 점수 : ${mainActivity.studentInfoList[position].kor}\n")
                append("영어 점수 : ${mainActivity.studentInfoList[position].eng}\n")
                append("수학 점수 : ${mainActivity.studentInfoList[position].math}\n")
            }
        }
    }
}