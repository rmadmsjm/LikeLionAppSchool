package kr.co.lion.android33_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.android33_fragment.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActivity: MainActivity

    // Fragment가 눈에 보여질 때 호출되는 메서드
    // 반환하는 View를 화면에 보여줌
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentMainBinding = FragmentMainBinding.inflate(inflater)
        // fragmentMainBinding = FragmentMainBinding.inflate(layoutInflater)

        // Activity 주소값 담기
        mainActivity = activity as MainActivity

        viewSetting()

        return fragmentMainBinding.root
    }

    // View 설정
    fun viewSetting() {
        fragmentMainBinding.apply {
            // 버튼
            buttonShowInput.setOnClickListener {
                // InputFragment로 교체

                // 데이터를 담을 Bundle 객체 생성
                val bundle = Bundle()
                // 데이터 담기
                bundle.putInt("data1", 100)
                bundle.putDouble("data2", 11.11)
                bundle.putString("data3", "문자열")

                mainActivity.replaceFragment(FragmentName.INPUT_FRAGMENT, true, true, bundle)
            }
        }
    }
}