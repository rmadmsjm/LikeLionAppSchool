package kr.co.lion.android08_button

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import kr.co.lion.android08_button.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.apply {
            // 버튼
            // 클래스 구현 후 객체 생성
            button.apply {
                // 버튼에 표시될 문자열
                text = "새로운 문자열"
                // 리스너 객체 생성
                val buttonClickListener = ButtonClickListener()
                // 버튼을 눌렀을 대 동작할 리스너 설정
                setOnClickListener(buttonClickListener)
            }

            // 익명 중첩클래스
            button2.apply {
                // 보통 리스너는 다수의 뷰가 하나의 리스너를 사용하는 경우는 별로 없음
                // 작성한 리스너가 하나의 뷰에 대한 리스너라면 중첩클래스를 사용하기도 함
                setOnClickListener(object : OnClickListener {
                    override fun onClick(v: View?) {
                        textView.apply {
                            text = "두 번째 버튼을 눌렀습니다"
                        }
                    }
                })
            }

            // 고차함수
            button3.apply {
                // 코틀린으로 작업할 경우, 구현할 메서드가 하나인 리스너에 대해서는 고차함수도 제공함
                setOnClickListener {
                    textView.apply {
                        text = "세 번째 버튼을 눌렀습니다"
                    }
                }
            }
        }
    }

    // 버튼을 눌렀을 때 동작할 리스너
    inner class ButtonClickListener : OnClickListener {
        // 버튼을 눌렀을 때 호출되는 메서드
        override fun onClick(v: View?) {
            activityMainBinding.apply {
                textView.apply {
                    text = "첫 번째 버튼을 눌렀습니다"
                }
            }
        }
    }
}