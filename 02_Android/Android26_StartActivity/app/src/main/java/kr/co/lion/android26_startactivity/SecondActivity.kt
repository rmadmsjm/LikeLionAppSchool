package kr.co.lion.android26_startactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android26_startactivity.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    lateinit var activitySecondBinding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySecondBinding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(activitySecondBinding.root)

        activitySecondBinding.apply {
            buttonFinishSecond.setOnClickListener {
                // 현재 Activity 종료
                // ACtivity가 종료 되면 BackStack에서 Activity가 제거되고 메모리에서도 제거됨 (완전 제거)
                finish()
            }
        }
    }
}