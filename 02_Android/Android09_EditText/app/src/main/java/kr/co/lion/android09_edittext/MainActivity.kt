package kr.co.lion.android09_edittext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
import kr.co.lion.android09_edittext.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.apply {
            // EditText
            editTextText.apply {
                // 문자열 설정
                setText("코드로 설정한 문자열")

                // 문자열을 입력할 때마다 동작함
                // it : EditText에 입력된 문자열을 가져옴
                // 고차함수
                addTextChangedListener {
                    textView.text = "실시간 감시 : $it"
                }

                // 리턴키 눌렀을 때 동작함
                setOnEditorActionListener { v, actionId, event ->

                    textView.text = "엔터키를 눌렀습니다\n"
                    textView.append("입력된 문자열 : $text")
                    
                    // true 반환 : 리턴키를 누른 후 포커스가 현재 EditText로 유지됨
                    // false 반환 : 리턴키를 누른 후 포커스가 다음 EditText로 유지됨
                    false
                }
            }

            // Button
            button.apply {
                setOnClickListener {
                    // EditText에서 문자열 가져오기
                    // Editable 타입으로 반환되므로 String 형태로 사용하려면 변환해줘야 함
                    val str1 = editTextText.text.toString()
                    textView.text = str1
                }
            }
        }
    }
}