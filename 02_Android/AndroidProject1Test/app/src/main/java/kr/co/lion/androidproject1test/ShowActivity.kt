package kr.co.lion.androidproject1test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.androidproject1test.databinding.ActivityShowBinding

class ShowActivity : AppCompatActivity() {

    lateinit var activityShowBinding: ActivityShowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityShowBinding = ActivityShowBinding.inflate(layoutInflater)
        setContentView(activityShowBinding.root)

        setToolbar()
        setView()
    }

    // 툴바
    fun setToolbar() {
        activityShowBinding.apply {
            toolbarShow.apply {
                // 타이틀
                title = "동물 정보"

                // back button
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }

                // 메뉴
                inflateMenu(R.menu.menu_show)
            }
        }
    }

    // View 설정
    fun setView() {
        activityShowBinding.apply {
            // textView
            textViewShowInfo.apply {
                // 공통
                text = "동물 종류 : \n"
                append("이름 : \n")
                append("나이 : 살\n")

                // 사자
                append("털의 개수 : 개\n")
                append("성별 : \n")

                // 호랑이
                append("줄무늬 개수 : 개\n")
                append("몸무게 : kg\n")

                // 기린
                append("목의 길이 : cm\n")
                append("달리는 속도 : km/h\n")
            }
        }
    }
}