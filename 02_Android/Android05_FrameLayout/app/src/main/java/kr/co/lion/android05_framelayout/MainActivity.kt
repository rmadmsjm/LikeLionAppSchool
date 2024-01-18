package kr.co.lion.android05_framelayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

// FrameLayout
// 배치되는 View가 좌측 상단에 붙어서 겹쳐지게 배치되는 View
// 탭이나 자유로운 배치 등을 목적으로 사용함
// 지금은 다른 view를 더 많이 사용함

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}