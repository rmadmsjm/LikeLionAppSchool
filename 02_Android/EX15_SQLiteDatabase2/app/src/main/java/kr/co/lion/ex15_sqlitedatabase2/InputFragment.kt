package kr.co.lion.ex15_sqlitedatabase2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.ex15_sqlitedatabase2.databinding.FragmentInputBinding

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

    // Toolbar
    fun settingToolbar() {
        fragmentInputBinding.apply {
            toolbarInput.apply {
                // 타이틀
                title = "학생 정보 입력"

                // backBtn
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(FragmentName.INPUT_FRAGMENT)
                }

                // 메뉴
                inflateMenu(R.menu.menu_input)
                setOnMenuItemClickListener {
                    when(it.itemId) {
                        R.id.menuItemInputSubmit -> {
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
            val name = textFieldInputName.text.toString()
            val age = textFieldInputAge.text.toString().toInt()
            val kor = textFieldInputKor.text.toString().toInt()
            val eng = textFieldInputEng.text.toString().toInt()
            val math = textFieldInputMath.text.toString().toInt()

            // 데이터베이스 저장
            val studentModel = StudentModel(0, name, age, kor, eng, math)
            StudentDao.insertStudent(mainActivity, studentModel)

            mainActivity.removeFragment(FragmentName.INPUT_FRAGMENT)
        }
    }

}