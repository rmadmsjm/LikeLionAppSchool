package kr.co.lion.android41_preferencesscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.PreferenceManager
import kr.co.lion.android41_preferencesscreen.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    lateinit var fragmentResultBinding: FragmentResultBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentResultBinding = FragmentResultBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        fragmentResultBinding.apply {
            // PreferencesScreen을 관리하는 PReference 객체 가져오기
            val pref = PreferenceManager.getDefaultSharedPreferences(mainActivity)

            // 저장된 데이터 가져오기
            // EditTextPreference
            val data1 = pref.getString("data1", null)
            // CheckBoxPreference
            val data2 = pref.getBoolean("data2", false)
            // SwitchPreference
            val data3 = pref.getBoolean("data3", false)
            // ListPreference
            val data4 = pref.getString("data4", null)
            // MultiSelectListPreference
            // getStringSet : 중복값 X, 순서 X
            val data5 = pref.getStringSet("data5", null)

            // 데이터 출럭
            textViewResult.apply {
                text = "data1 : ${data1}\n"
                append("data2 : ${data2}\n")
                append("data3 : ${data3}\n")
                append("data4 : ${data4}\n")
                data5?.forEach {
                    append("data5 : ${data5}\n")
                }
            }
        }

        return fragmentResultBinding.root
    }
}