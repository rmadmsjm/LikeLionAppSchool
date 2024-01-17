package kr.co.lion.android03_viewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView
import kr.co.lion.android03_viewbinding.databinding.ActivityMainBinding

// ViewBinding
// 코드를 통해 배치된 View를 사용하기 위해서는 View 객체의 주소값을 가져와야 함
// findViewById 메서드를 이용해 원하는 View 객체의 주소값을 가져올 수 있음
// 만약 ViewBinding를 설정하면 id가 설정되어 있는 View 객체의 주소값이 미리 프로퍼티에 담겨져 있기 때문에 이것을 사용만 하면 됨

// 셋팅 방법
// 1. Module 수준의 build.grade.kts 파일 열기
// 2. build.grade.kts 파일에 다음과 같이 작성 후 sync now 누름
//   android {
//      ...
//      buildFeatures {
//          viewBinding = true
//      }
//    }
// 3. ViewBinding 객체 얻어 오기

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding 사용 X
//        setContentView(R.layout.activity_main)
//
//        // View의 주소값 가져오기
//        val v1 = findViewById<TextView>(R.id.textView)
//        val v2 = findViewById<Button>(R.id.button)
//
//        // Button을 눌렀을 때 동작할 리스너 설정
//        v2.setOnClickListener(object : OnClickListener {
//            // 버튼 누르면 호출되는 메서드
//            override fun onClick(v: View?) {
//                // TextView에 문자열 설정
//                v1.text = "버튼을 눌렀습니다"
//            }
//        })

        // ViewBinding 사용
        // 위의 setContentView(R.layout.activity_main) 부분
        // ActivityMainBinding : activity_main.xml 와 관련 있음
        // activity_main.xml을 관리하는 ViewBinding을 통해 UI 요소 객체를 생성함
        // ViewBinding 클래스 이름은 layout 폴더의 xml 파일 이름을 기초로 결정됨
        // activity_main.xml -> ActivityMainBinding
        // activity_lion.xml -> ActivityLionBinding
        // layoutInflater : xml 파일을 통해서 View 객체 생성할 수 있는 도구
        // xml 파일에 배치한 모든 View의 객체를 생성하고 객체의 주소값을 담을 프로퍼티를 만들어 객체의 주소값에 담아줌
        // 이러한 프로퍼티를 ViewBinding 객체가 가지고 있음
        // ex) activity_test_screen.xml -> ActivityTestScreenBinding
        val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        // ViewBinding이 관리하는 View 중 최상위 View를 설정해 화면에 보여주기
        // ViewBinding이 가지고 있는 root 프로퍼티는 가장 최상위 View를 지칭함
        // 지칭한 View를 화면에 보여줌
        setContentView(activityMainBinding.root)

        // ViewBinding 객체에 id가 설정되어 있는 View 객체의 주소값이 담겨 있는
        // 프로퍼티가 있기 때문에 프로퍼티를 이용해 View 객체에 접근함
        // id와 동일한 이름의 프로퍼티 생성됨
        activityMainBinding.button.setOnClickListener(object : OnClickListener {
            // 버튼 누르면 호출되는 메서드
            override fun onClick(v: View?) {
                // TextView에 문자열 설정
                activityMainBinding.textView.text = "버튼을 눌렀습니다"
            }
        })
    }
}