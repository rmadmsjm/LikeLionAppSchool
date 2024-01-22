package kr.co.lion.android17_checkbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.checkbox.MaterialCheckBox
import kr.co.lion.android17_checkbox.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.apply {
            button.setOnClickListener {
                val a1 = getCheckedString(checkBox.isChecked)
                val a2 = getCheckedState((checkBox as MaterialCheckBox).checkedState)
                textView.text = "check1 checked : ${a1}\n"
                textView.append("check1 state : ${a2}\n")

                val a3 = getCheckedString(checkBox2.isChecked)
                val a4 = getCheckedState((checkBox2 as MaterialCheckBox).checkedState)
                textView.append("check2 checked : ${a3}\n")
                textView.append("check2 state : ${a4}\n")

                val a5 = getCheckedString(checkBox3.isChecked)
                val a6 = getCheckedState((checkBox3 as MaterialCheckBox).checkedState)
                textView.append("check3 checked : ${a5}\n")
                textView.append("check3 state : ${a6}\n")

                val a7 = getCheckedString(checkBox4.isChecked)
                val a8 = getCheckedState((checkBox4 as MaterialCheckBox).checkedState)
                textView.append("check4 checked : ${a7}\n")
                textView.append("check4 state : ${a8}\n")

                val a9 = getCheckedString(checkBox5.isChecked)
                val a10 = getCheckedState((checkBox5 as MaterialCheckBox).checkedState)
                textView.append("check5 checked : ${a9}\n")
                textView.append("check5 state : ${a10}\n")
            }

            button2.setOnClickListener {
                checkBox.isChecked = false
                checkBox2.isChecked = true
                (checkBox3 as MaterialCheckBox).checkedState = MaterialCheckBox.STATE_UNCHECKED
                (checkBox4 as MaterialCheckBox).checkedState = MaterialCheckBox.STATE_INDETERMINATE
                (checkBox5 as MaterialCheckBox).checkedState = MaterialCheckBox.STATE_UNCHECKED
            }

            // 리스너
            // 체크 여부가 변경되었을 때
            // 두 번째 : 설정된 체크 여부
            checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
                textView.text = when(isChecked) {
                    true -> "checkBox가 체크 됨"
                    false -> "checkBox가 체크 해제됨"
                }
            }

            // 두 번째 : 체크 상태 값
            (checkBox4 as MaterialCheckBox).addOnCheckedStateChangedListener { checkBox, state ->
                textView.text = when(state) {
                    MaterialCheckBox.STATE_CHECKED -> "checkBox4가 체크 되었습니다"
                    MaterialCheckBox.STATE_UNCHECKED -> "checkBox4가 체크 해제되었습니다"
                    MaterialCheckBox.STATE_INDETERMINATE -> "checkBox4가 빼기 상태가 되었습니다"
                    else -> ""
                }
            }
        }
    }

    fun getCheckedString(checked:Boolean) = when(checked) {
        true -> "체크 되어 있음"
        false -> "체크 되어 있지 않음"
    }

    fun getCheckedState(checkedState: Int) = when(checkedState) {
        MaterialCheckBox.STATE_CHECKED -> "체크 되어 있음"
        MaterialCheckBox.STATE_UNCHECKED -> "체크 되어 있지 않음"
        MaterialCheckBox.STATE_INDETERMINATE -> "빼기 표시 되어 있음"
        else -> ""
    }
}