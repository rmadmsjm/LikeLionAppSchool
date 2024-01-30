package kr.co.lion.ex11_toolbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.ex11_toolbar.databinding.ActivityInputBinding

class InputActivity : AppCompatActivity() {

    lateinit var activityInputBinding: ActivityInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityInputBinding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(activityInputBinding.root)

        initData()
        initView()
        setEvent()
    }

    // 초기 데이터 셋팅
    fun initData() {
    }

    // 강사님 설명
    // 툴바 설정
    fun initToolbar(){
        activityInputBinding.apply {
            inputToolbar.apply {
                title = "학생 정보 입력"

                // 뒤로 가기
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }

                inflateMenu(R.menu.input_activity_menu)
                setOnMenuItemClickListener{

                    when(it.itemId){
                        R.id.menuOk ->{
                            // 입력한 정보를 객체에 담는다.
                            val name = inputName.text.toString()
                            val grade = inputGrade.text.toString().toInt()
                            val kor = inputKor.text.toString().toInt()
                            val eng = inputEng.text.toString().toInt()
                            val math = inputMath.text.toString().toInt()

                            val info1 = InfoClass(name, grade, kor, eng, math)

                            // 데이터를 담을 Intent를 생성한다.
                            val resultIntent = Intent()
                            // 객체를 Intent에 저장할 때 writeToParcel 메서가 호출된다.
                            resultIntent.putExtra("obj", info1)

                            // 결과를 셋팅한다.
                            setResult(RESULT_OK, resultIntent)

                            // 현재 Activity를 종료한다.
                            finish()
                        }
                    }

                    true
                }
            }
        }
    }

    // View 초기 셋팅
    fun initView() {
        activityInputBinding.apply {
            inputToolbar.apply {
                title = "학생 정보 입력"

                inflateMenu(R.menu.input_activity_menu)

                // 좌측 상단의 홈 버튼 아이콘 설정
                setNavigationIcon(R.drawable.arrow_back_24px)
            }
        }
    }

    // 이벤트 설정
    fun setEvent() {
        activityInputBinding.apply {
            inputToolbar.apply {
                setOnMenuItemClickListener {
                    when(it.itemId) {
                        R.id.menuOk -> {
                            // 입력한 정보 객체에 담기
                            val name = inputName.text.toString()
                            val grade = inputGrade.text.toString().toInt()
                            val kor = inputKor.text.toString().toInt()
                            val eng = inputEng.text.toString().toInt()
                            val math = inputMath.text.toString().toInt()

                            val info1 = InfoClass(name, grade, kor, eng, math)

                            // 데이터를 담을 Intent 생성
                            val resultIntent = Intent()
                            // 객체를 Intent에 저장할 때 writeToParcel 메서드가 호출됨
                            resultIntent.putExtra("obj", info1)

                            // 결과를 세팅
                            setResult(RESULT_OK, resultIntent)

                            // 현재 Activity 종료
                            finish()
                        }
                    }
                    true
                }

                setNavigationOnClickListener {
                    finish()
                }
            }
        }
    }
}