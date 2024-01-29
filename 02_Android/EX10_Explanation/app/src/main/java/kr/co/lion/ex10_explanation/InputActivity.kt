package kr.co.lion.ex10_explanation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.ex10_explanation.databinding.ActivityInputBinding

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

    // View 초기 셋팅
    fun initView() {
    }

    // 이벤트 설정
    fun setEvent() {
        activityInputBinding.apply {
            // 버튼 이벤트
            buttonSubmit.setOnClickListener {
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
    }
}