package kr.co.lion.ex06

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.ex06.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.apply {

            // 스위치 초기 상태에 따라 체크박스 상태 및 활성화/비활성화 설정
            updateCheckboxState(swtich.isChecked)

            // 스위치 상태 변경 시에 체크박스 상태 및 활성화/비활성화 업데이트
            swtich.setOnCheckedChangeListener { buttonView, isChecked ->
                updateCheckboxState(isChecked)
            }

            submitBtn.setOnClickListener {
                textView.text = ""
                textView.append("아이디 : ${idInput.text}\n")
                textView.append("비밀번호 : ${passwordInput.text}\n")
                textView.append("이름 : ${nameInput.text}\n")

                // 체크박스 상태에 따라 취미 정보 출력
                // 체크박스 상태에 따라 취미 정보를 가져오기 위한 리스트
                val hobbies = mutableListOf<String>()

                // 선택된 취미 리스트에 추가
                if (soccerCheckBox.isChecked) {
                    hobbies.add("축구")
                }
                if (basketballCheckBox.isChecked) {
                    hobbies.add("농구")
                }
                if (baseballCheckBox.isChecked) {
                    hobbies.add("야구")
                }

                // 선택된 취미가 없을 시,  "취미 : 없음" 출력
                // 선택된 취미는 쉼표로 구분하여 한 줄로 출력
                if (hobbies.isEmpty()) {
                    textView.append("취미 : 없음\n")
                } else {
                    textView.append("취미 : ${hobbies.joinToString(", ")}\n")
                }
            }
        }
    }

    // 스위치 상태에 따라 체크박스 상태 및 활성화/비활성화 설정하는 함수
    fun updateCheckboxState(isSwitchChecked: Boolean) {
        activityMainBinding.apply {
            soccerCheckBox.isEnabled = isSwitchChecked
            basketballCheckBox.isEnabled = isSwitchChecked
            baseballCheckBox.isEnabled = isSwitchChecked

            if (!isSwitchChecked) {
                soccerCheckBox.isChecked = false
                basketballCheckBox.isChecked = false
                baseballCheckBox.isChecked = false
            }
        }
    }

}