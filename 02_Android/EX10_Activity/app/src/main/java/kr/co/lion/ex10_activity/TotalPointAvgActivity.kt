package kr.co.lion.ex10_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.ex10_activity.databinding.ActivityTotalPointAvgBinding

class TotalPointAvgActivity : AppCompatActivity() {

    lateinit var activityTotalPointAvgBinding: ActivityTotalPointAvgBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityTotalPointAvgBinding = ActivityTotalPointAvgBinding.inflate(layoutInflater)
        setContentView(activityTotalPointAvgBinding.root)
    }

    fun init() {
    }

    fun setViewEvent() {
        activityTotalPointAvgBinding.apply {
            totalPointAvgOkBtn.setOnClickListener {
                finish()
            }
        }
    }
}