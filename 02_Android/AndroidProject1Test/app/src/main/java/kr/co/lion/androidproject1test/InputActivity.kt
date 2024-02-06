package kr.co.lion.androidproject1test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import kr.co.lion.androidproject1test.databinding.ActivityInputBinding

class InputActivity : AppCompatActivity() {

    lateinit var activityInputBinding: ActivityInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityInputBinding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(activityInputBinding.root)

        setToolbar()
        initView()
        setEvent()
    }

    // 툴바
    fun setToolbar() {
        activityInputBinding.apply {
            toolbarInput.apply {
                // 타이틀
                title = "동물 등록"
                // back button
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }
                // 메뉴
                inflateMenu(R.menu.menu_input)
            }
        }
    }

    // View의 초기 상태 설정
    fun initView() {
        activityInputBinding.apply {
            // 버튼 그룹에서 사자로 기본 설정
            buttonGroupInputType.check(R.id.buttonInputType1)
            // 입력 요소를 관리하는 layout 중 사자는 보이게 하고 나머지는 보이지 않게 함
            containerInputType1.isVisible = true
            containerInputType2.isVisible = false
            containerInputType3.isVisible = false

            // 사자의 성별은 암컷으로 기본 설정
            buttonGroupInputGender.check(R.id.buttonInputGender1)

            // 이름 입력칸에 포커스 주기
            Util.showSoftInput(this@InputActivity, activityInputBinding.textFieldInputName)
        }
    }

    // View 이벤트 설정
    fun setEvent() {
        activityInputBinding.apply {
            // 동물 타입 버튼 그룹
            buttonGroupInputType.addOnButtonCheckedListener { group, checkedId, isChecked ->
                // 전부 안 보이는 상태로 변경
                containerInputType1.isVisible = false
                containerInputType2.isVisible = false
                containerInputType3.isVisible = false

                // 현재 체크되어 있는 버튼에 따라 보여지는 부분을 다르게 함
                when(buttonGroupInputType.checkedButtonId) {
                    // 사자
                    R.id.buttonInputType1 -> {
                        containerInputType1.isVisible = true

                        // 입력 요소 초기화
                        textFieldInputFurCnt.setText("")
                        buttonGroupInputGender.check(R.id.buttonInputGender1)
                    }
                    // 호랑이
                    R.id.buttonInputType2 -> {
                        containerInputType2.isVisible = true

                        // 입력 요소 초기화
                        textFieldInputLineCount.setText("")
                        sliderInputWeight.value = sliderInputWeight.valueFrom
                    }
                    // 기린
                    R.id.buttonInputType3 -> {
                        containerInputType3.isVisible = true

                        // 입력 요소 초기화
                        textFieldInputNeckLenght.setText("")
                        textFieldInputRunSpeed.setText("")
                    }
                }

                // 이름 입력칸에 포커스 주기
                Util.showSoftInput(this@InputActivity, activityInputBinding.textFieldInputName)
            }

            // 호랑이 몸무게 slider
            // 두 번째 : 현재 설정된 값
            // 세 번째 : 사용자에 의해서 값이 변경될 경우 true가 들어옴, 그 외의 모든 건 false
            sliderInputWeight.addOnChangeListener { slider, value, fromUser ->
                // TextView에 출력
                textViewInputWeight.text = "몸무게 : ${value.toInt()}kg"
            }
        }
    }
}