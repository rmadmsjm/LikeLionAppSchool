package kr.co.lion.android06_constraintlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

// ConstrainLayout
// ConstraintLayout에 배치도는 뷰는 부모 혹은 다른 뷰와의 관계를 나타내는 제약조건을 설정해 뷰을 배치함

// 제약 조건은 선으로 표시됨
// 직선 : 절대적 길이 -> 물리적인 액정 사이즈에 관계 없이 고정된 길이, 길이
// 지그재그선 : 상대적 길이 -> 물리적인 액정 사이즈에 따라 변환되는 길이, 비율

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}