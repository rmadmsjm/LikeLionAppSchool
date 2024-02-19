package kr.co.lion.android37_applicationclass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android37_applicationclass.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    lateinit var activitySecondBinding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySecondBinding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(activitySecondBinding.root)

        activitySecondBinding.apply {
            // Application 객체 가져오기
            val appClass = application as AppClass

            textView.apply {
                text = "value1 : ${appClass.value1}\n"
                append("value2 : ${appClass.value2}\n")
                append("value3 : ${appClass.value3}")
            }
        }
    }
}