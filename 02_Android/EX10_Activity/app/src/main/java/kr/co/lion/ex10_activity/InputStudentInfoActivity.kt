package kr.co.lion.ex10_activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import kr.co.lion.ex10_activity.databinding.ActivityInputStudentInfoBinding

class InputStudentInfoActivity : AppCompatActivity() {

    lateinit var activityInputStudentInfoBinding: ActivityInputStudentInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityInputStudentInfoBinding = ActivityInputStudentInfoBinding.inflate(layoutInflater)
        setContentView(activityInputStudentInfoBinding.root)
        
        setViewEvent()
    }

    fun init() {
    }

    fun setViewEvent() {
        activityInputStudentInfoBinding.apply {
            submitBtn.setOnClickListener {
                // 이전 Activity로 전달되는 Intent
                val studentInfos = Intent()

                studentInfos.putExtra("name", nameInput.text.toString())
                studentInfos.putExtra("grade", gradeInput.text.toString())
                studentInfos.putExtra("kor", korInput.text.toString())
                studentInfos.putExtra("eng", engInput.text.toString())
                studentInfos.putExtra("math", mathInput.text.toString())

                // 작업의 결과를 설정할 때 Intent도 설정해주면 이전 Activity로 전달됨
                setResult(RESULT_OK, studentInfos)

                // Activity 종료
                finish()
            }
        }
    }
}