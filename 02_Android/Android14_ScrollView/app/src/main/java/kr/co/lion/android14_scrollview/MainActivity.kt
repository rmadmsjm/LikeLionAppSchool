package kr.co.lion.android14_scrollview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android14_scrollview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.apply {
            // ScrollView의 Scroll 이벤트
            // 첫 번째 : 이벤트가 발생한 view 객체
            // 두 번째 : 스크롤 된 X 좌표
            // 세 번째 : 스크롤 된 Y 좌표
            // 네 번째 : 스크롤 되기 전 X 좌표
            // 다섯 번째 : 스크롤 되기전 Y 좌표
            scroll1.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                textView.text = "Y : $oldScrollY -> $scrollY"
            }

            // HorizontalScrollView의 Scroll 이벤트
            scroll2.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                textView2.text = "X : $oldScrollX -> $scrollX"
            }

            button.setOnClickListener {
                // 현재의 좌표 가져오기
                textView.text = "현재의 X좌표 : ${scroll1.scrollX}"
                textView2.text = "현재의 Y좌표 : ${scroll2.scrollY}"
            }

            button2.setOnClickListener {
                // 지정된 위치로 이동
//                scroll1.scrollTo(0, 300)
//                scroll2.scrollTo(300, 0)
                // 자정한 만큼 이동
//                scroll1.scrollBy(0, 100)
//                scroll2.scrollBy(100, 0)
                // 지정된 위치로 이동 (애니메이션)
//                scroll1.smoothScrollTo(0, 300)
//                scroll2.smoothScrollTo(300, 0)
                // 지정한 만큼 이동 (애니메이션)
                scroll1.smoothScrollBy(0, 100)
                scroll2.smoothScrollBy(100, 0)
            }
        }
    }
}