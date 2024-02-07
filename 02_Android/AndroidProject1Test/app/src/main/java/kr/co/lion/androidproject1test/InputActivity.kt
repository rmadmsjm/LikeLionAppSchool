package kr.co.lion.androidproject1test

import android.content.DialogInterface
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
                setOnMenuItemClickListener {
                    when(it.itemId) {
                        // 입력 완료
                        R.id.menu_item_input_done -> {
                            // 유효성 검사 메서드 호출
                            checkInput()
                        }
                    }
                    true
                }
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

    // 입력 유효성 검사
    fun checkInput() {
        activityInputBinding.apply {
            // 이름
            val name = textFieldInputName.text.toString()
            if(name.trim().isEmpty()) {
                Util.showInfoDialog(this@InputActivity, "이름 입력 오류", "이름을 입력해주세요") { dialogInterface, i ->
                    Util.showSoftInput(this@InputActivity, textFieldInputName)
                }
                return
            }

            // 나이
            val age = textFieldInputAge.text.toString()
            if(age.trim().isEmpty()){
                Util.showInfoDialog(this@InputActivity, "나이 입력 오류", "나이를 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                    Util.showSoftInput(this@InputActivity, textFieldInputAge)
                }
                return
            }

            // 동물 종류별로 분기
            when(buttonGroupInputType.checkedButtonId) {
                // 사자
                R.id.buttonInputType1 -> {
                    // 털의 개수
                    val furCnt = textFieldInputFurCnt.text.toString()
                    if(furCnt.trim().isEmpty()) {
                        Util.showInfoDialog(this@InputActivity, "털의 개수 입력 오류", "털의 개수를 입력해주세요") { dialogInterface: DialogInterface, i: Int ->
                            Util.showSoftInput(this@InputActivity, textFieldInputFurCnt)
                        }
                        return
                    }
                }

                // 호랑이
                R.id.buttonInputType2 -> {
                    // 줄무늬 개수
                    val lineCount = textFieldInputLineCount.text.toString()
                    if(lineCount.trim().isEmpty()) {
                        Util.showInfoDialog(this@InputActivity, "줄무늬 개수 입력 오류", "줄무늬 개수를 입력해주세요") { dialogInterface: DialogInterface, i: Int ->
                            Util.showSoftInput(this@InputActivity, textFieldInputLineCount)
                        }
                        return
                    }
                }

                // 기린
                R.id.buttonInputType3 -> {
                    // 목의 길이
                    val neckLength = textFieldInputNeckLenght.text.toString()
                    if(neckLength.trim().isEmpty()) {
                        Util.showInfoDialog(this@InputActivity, "목의 길이 입력 오류", "목의 길이를 입력하세요") { dialogInterface: DialogInterface, i: Int ->
                            Util.showSoftInput(this@InputActivity, textFieldInputNeckLenght)
                        }
                        true
                    }

                    // 달리는 속도
                    val runSpeed = textFieldInputRunSpeed.text.toString()
                    if(runSpeed.trim().isEmpty()) {
                        Util.showInfoDialog(this@InputActivity, "달리는 속도 입력 오류", "달리는 속도를 입력하세요") { dialogInterface: DialogInterface, i: Int ->
                            Util.showSoftInput(this@InputActivity, textFieldInputRunSpeed)
                        }
                        true
                    }
                }
            }
        }
    }

    // 저장 처리 메서드
    fun addAnimalData() {
        activityInputBinding.apply {
            // 사용자가 선택한 동물의 종류에 따라 분기
            when(buttonGroupInputType.checkedButtonId) {
                // 사자
                R.id.buttonInputType1 -> {
                    val lion = Lion()
                    lion.type = AnimalType.ANIMAL_TYPE_LION
                    lion.name = textFieldInputName.text.toString()
                    lion.age = textFieldInputAge.text.toString().toInt()
                    lion.furCnt = textFieldInputFurCnt.text.toString().toInt()
                    lion.gender = when(buttonGroupInputGender.checkedButtonId) {
                        R.id.buttonInputGender1 -> LionGender.LION_GENDER1
                        R.id.buttonInputGender2 -> LionGender.LION_GENDER2
                        else -> LionGender.LION_GENDER1
                    }
                    Util.animalList.add(lion)
                }
                // 호랑이
                R.id.buttonInputType2 -> {
                    val tiger = Tiger()
                    tiger.type= AnimalType.ANIMAL_TYPE_TIGER
                    tiger.name = textFieldInputName.text.toString();
                    tiger.age = textFieldInputAge.text.toString().toInt();
                    tiger.lineCount = textFieldInputLineCount.text.toString().toInt();
                    tiger.weight = sliderInputWeight.value.toInt()
                    Util.animalList.add(tiger)
                }
                // 기린
                R.id.buttonInputType3 -> {
                    val giraffe = Giraffe()
                    giraffe.type = AnimalType.ANIMAL_TYPE_GIRAFFE
                    giraffe.name = textFieldInputName.text.toString()
                    giraffe.age = textFieldInputAge.text.toString().toInt()
                    giraffe.neckLength = textFieldInputNeckLenght.text.toString().toInt()
                    giraffe.runSpeed = textFieldInputRunSpeed.text.toString().toInt()
                    Util.animalList.add(giraffe)
                }
            }
        }

        // 저장 처리 메서드 호출
        addAnimalData()
        // 액티비티 종료
        finish()
    }
}