package kr.co.lion.ex07_explanation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import kr.co.lion.ex07_explanation.databinding.ActivityMainBinding

// checkBox 사용 시 com.google.android.material.checkbox.MaterialCheckBox 로 사용하면
// 상태값 사용할 때 매번 형변환 하지 않아도 됨
// 따라서 상태값을 사용하려면 com.google.android.material.checkbox.MaterialCheckBox 사용할 것

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        initView()
        setViewEvent()
    }

    // 화면 요소에 관련된 초기화
    fun initView() {
        activityMainBinding.apply {
            // 취미 스위치 on 상태로 설정
            switchHobby.isChecked = true
        }
    }

    // 화면 요소에 대한 이벤트 설정
    fun setViewEvent() {
        activityMainBinding.apply {
            // 취미 스위치 이벤트
            switchHobby.setOnCheckedChangeListener { buttonView, isChecked ->
                // 스위치 on/off 상태로 분기
                when(isChecked) {
                    // on 상태
                    true -> {
                        // 보이게
//                        checkBoxHobby1.isVisible = true
//                        checkBoxHobby2.isVisible = true
//                        checkBoxHobby3.isVisible = true

                        // 활성화
                        checkBoxHobby1.isEnabled = true
                        checkBoxHobby2.isEnabled = true
                        checkBoxHobby3.isEnabled = true
                    }
                    // off 상태
                    false -> {
                        // 안 보이게
//                        checkBoxHobby1.isVisible = false
//                        checkBoxHobby2.isVisible = false
//                        checkBoxHobby3.isVisible = false

                        // 비활성화
                        checkBoxHobby1.isEnabled = false
                        checkBoxHobby2.isEnabled = false
                        checkBoxHobby3.isEnabled = false
                    }
                }
            }

            // 버튼 이벤트
            buttonSubmit.setOnClickListener {
                textViewResult.text = "아이디 : ${textFieldUserId.text}\n"
                textViewResult.append("비밀번호 : ${textFieldUserPw.text}\n")
                textViewResult.append("이름 : ${textFieldUserName.text}\n")

                // 스위치 on/off 상태에 따라 분기
                when(switchHobby.isChecked) {
                    // off 상태면 취미가 없는 것으로 취급
                    false -> textViewResult.append("선택한 취미가 없습니다")
                    // on 상태면 체크박스에 체크한 것을 출력
                    true -> {
                        // 모든 체크박스가 체크되어 있지 않다면
                        // 상태값으로 처리하면 코드가 길어짐
                        if(checkBoxHobby1.isChecked == false && checkBoxHobby2.isChecked == false && checkBoxHobby3.isChecked == false) {
                            textViewResult.append("선택한 취미가 없습니다")
                        } else {
                            if(checkBoxHobby1.isChecked) {
                                textViewResult.append("선택한 취미 : 축구\n")
                            }
                            if(checkBoxHobby2.isChecked) {
                                textViewResult.append("선택한 취미 : 농구\n")
                            }
                            if(checkBoxHobby3.isChecked) {
                                textViewResult.append("선택한 취미 : 야구\n")
                            }
                        }
                    }
                }
            }
        }
    }
}