package kr.co.lion.androidproject_memo2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.androidproject_memo2.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentMainBinding = FragmentMainBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingToolbar()

        return fragmentMainBinding.root
    }

    // 툴바
    fun settingToolbar() {
        fragmentMainBinding.apply {
            toolbarMain.apply {
                title = "메모"

                inflateMenu(R.menu.main_menu)
            }
        }
    }
}