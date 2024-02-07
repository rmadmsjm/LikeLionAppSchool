package kr.co.lion.androidproject1test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import kr.co.lion.androidproject1test.databinding.ActivityModifyBinding

class ModifyActivity : AppCompatActivity() {

    lateinit var activityModifyBinding: ActivityModifyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityModifyBinding = ActivityModifyBinding.inflate(layoutInflater)
        setContentView(activityModifyBinding.root)

        setToolbar()
        initView()
    }

    // 툴바
    fun setToolbar() {
        activityModifyBinding.apply {
            toolbarModify.apply {
                // 타이틀
                title = "동물 정보 수정"

                // back button
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }

                // 메뉴
                inflateMenu(R.menu.menu_modify)
            }
        }
    }

    // View 설정
    fun initView() {
        activityModifyBinding.apply {
            // 각 동물별 입력 요소 숨기기
            containerModifyType1.isVisible = false
            containerModifyType2.isVisible = false
            containerModifyType3.isVisible = false

            // 순서값 추출
            val position = intent.getIntExtra("position", 0)
            // position 번째 객체 추출
            val animal = Util.animalList[position]

            // 공통
            textFieldModifyName.setText(animal.name)
            textFieldModifyAge.setText("${animal.age}")

            // 동물 타입으로 분기
            when(animal.type) {
                // 사자
                AnimalType.ANIMAL_TYPE_LION -> {
                    // 입력 요소 보이게 하기
                    containerModifyType1.isVisible = true
                    // 형변환
                    val lion = animal as Lion
                    // 값 설정
                    textFieldModifyFurCnt.setText("${lion.furCnt}")
                    when(lion.gender) {
                        LionGender.LION_GENDER1 -> {
                            buttonGroupModifyGender.check(R.id.buttonModifyGender1)
                        }
                        LionGender.LION_GENDER2 -> {
                            buttonGroupModifyGender.check(R.id.buttonModifyGender2)
                        }
                    }
                }

                // 호랑이
                AnimalType.ANIMAL_TYPE_TIGER -> {
                    // 입력 요소 보이게 하기
                    containerModifyType2.isVisible = true
                    // 형변환
                    val tiger = animal as Tiger
                    // 값 설정
                    textFieldModifyLineCount.setText("${tiger.lineCount}")
                    sliderModifyWeight.value = tiger.weight.toFloat()
                }

                // 기린
                AnimalType.ANIMAL_TYPE_GIRAFFE -> {
                    // 입력 요소 보이게 하기
                    containerModifyType3.isVisible = true
                    // 형변환
                    val giraffe = animal as Giraffe
                    // 값 설정
                    textFieldModifyNeckLenght.setText("${giraffe.neckLength}")
                    textFieldModifyRunSpeed.setText("${giraffe.runSpeed}")
                }
            }
        }
    }
}