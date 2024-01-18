package kr.co.lion.android07_textview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android07_textview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        // TextView 사용
        // activityMainBinding.textView2.text = "새로운 문자열"
        activityMainBinding.apply {
            // TextView의 프로퍼티의 메서드 사용
            textView.apply {
                // 문자열 설정
                text = "새로운 문자열\n"

            }

            textView2.apply {
                // 문자열 추가
                // \n : 아래로 내린다는 의미의 글자
                append("\n문자열2\n")
                append("문자열3\n")
            }
        }
    }
}