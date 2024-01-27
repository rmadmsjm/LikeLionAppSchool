package kr.co.lion.ex10_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.ex10_activity.databinding.ActivityStudentInfoBinding

class StudentInfoActivity : AppCompatActivity() {

    lateinit var activityStudentInfoBinding: ActivityStudentInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityStudentInfoBinding = ActivityStudentInfoBinding.inflate(layoutInflater)
        setContentView(activityStudentInfoBinding.root)

        setViewEvent()
    }

    fun init() {
    }

    fun setViewEvent() {
        activityStudentInfoBinding.apply {
            studentInfoTextView.apply {
                // Intent에 담겨서 전달된 데이터 가져오기
                val name = intent.getStringExtra("name")
                val grade = intent.getStringExtra("grade")
                val kor = intent.getStringExtra("kor")
                val eng = intent.getStringExtra("eng")
                val math = intent.getStringExtra("math")

                // 총점 및 평균
                val totalPoint = kor!!.toInt() + eng!!.toInt() + math!!.toInt()
                val avg = totalPoint / 3
                
                append("이름 : $name\n")
                append("학년 : ${grade}학년\n")
                append("\n")
                append("국어 점수 : ${kor}점\n")
                append("영어 점수 : ${eng}점\n")
                append("수학 점수 : ${math}점\n")
                append("\n")
                append("총점 : ${totalPoint}점\n")
                append("평균 : ${avg}점\n")
            }

            studentInfoOkBtn.setOnClickListener {
                finish()
            }
        }
    }
}