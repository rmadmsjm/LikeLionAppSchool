package kr.co.lion.android02_testproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

// step1) 화면부터 구성
// onCreate 메서드에 있는 코드 중에 setContentView 메서드의 매개변수에 설정되어 있는 것을 확인 후
// res/layout 폴더에 있는 xml 파일을 엶

// step2) Activity로 돌아와서 필요한 코드를 작성

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // id 속성이 textView인 화면 요소 객체를 가져오기
        val textView1 = findViewById<TextView>(R.id.textView)
        // id 속성이 button인 화면 요소 객체를 가져오기
        val button1 = findViewById<Button>(R.id.button)
        val button2 = findViewById<Button>(R.id.button2)

        // 화면 요소 중에 이벤트 처리를 설정
        // 어떠한 사건이 벌어지면 Android OS가 호출될 메서드를 구현하여 설정함
        button1.setOnClickListener(object : OnClickListener{
            // 버튼을 클릭하면 호출되는 메서드
            override fun onClick(v: View?) {
                // 버튼이 눌러지면 동작할 코드 작성
                textView1.text = "안녕하세요~~"
            }
        })

        button2.setOnClickListener(object : OnClickListener{
            override fun onClick(v: View?) {
                Toast.makeText(this@MainActivity, "Button2", Toast.LENGTH_SHORT).show()
            }
        })
    }
}