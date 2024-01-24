package kr.co.lion.android21_materialcardview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android21_materialcardview.databinding.ActivityMainBinding

// CardView
// 화면에 배치되는 뷰를 Card로 묶어서 표현할 수 있음

// style
// 1) Outlined : 기본
// 2) Filled
// 3) Elevated


class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
    }
}