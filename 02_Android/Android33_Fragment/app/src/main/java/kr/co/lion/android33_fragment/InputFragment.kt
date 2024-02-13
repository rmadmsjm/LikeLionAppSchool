package kr.co.lion.android33_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.android33_fragment.databinding.FragmentInputBinding

class InputFragment : Fragment() {

    lateinit var fragmentInputBinding: FragmentInputBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentInputBinding = FragmentInputBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        viewSetting()

        return fragmentInputBinding.root
    }

    fun viewSetting() {
        fragmentInputBinding.apply {
            // 버튼
            buttonPrev.setOnClickListener {
                // BackStack에서 Fragment를 제거해 이전 Fragment가 보이도록 함
                mainActivity.removeFragment(FragmentName.INPUT_FRAGMENT)
            }

            // textView
            textViewInput.apply {
                // arguments를 통해 데이터 추출
                text = "data1 : ${arguments?.getInt("data1")}\n"
                append("data2 : ${arguments?.getDouble("data2")}\n")
                append("data3 : ${arguments?.getString("data3")}\n")
            }
        }
    }

}