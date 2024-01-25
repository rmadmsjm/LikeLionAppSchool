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
    }

    fun init() {
    }

    fun setViewEvent() {
        activityStudentInfoBinding.apply {
            studentInfoOkBtn.setOnClickListener {
                finish()
            }
        }
    }
}