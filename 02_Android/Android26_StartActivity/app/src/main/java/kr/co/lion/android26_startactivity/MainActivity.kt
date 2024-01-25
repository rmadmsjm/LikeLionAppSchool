package kr.co.lion.android26_startactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android26_startactivity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.apply {
            buttonStartSecond.setOnClickListener {
                // intent 객체
                // intent : 안드로이드 4대 구성요소를 실행하기 위해 OS에게 전달하는 요청 정보 객체
                // 실행하고자 하는 것과 관련된 모든 정보들을 담아 OS로 전달하면 OS가 이를 보고 실행함
                // 두 번재 매개변수 : 실행하고자 하는 Activity의 자바 클래스 지정함 (자바 코드로 변경됐을 때의 클래스 지정해야 함)
                // ❓ 리플렉션 : 클래스에 대한 정보를 파악하기 위해서 사용
                val seconIntent = Intent(this@MainActivity, SecondActivity::class.java)

                // Activity 실행
                // Intent에 등록한 클래스를 확인해 그 클래스의 객체를 생성하고 onCreate 메서드 호출
                // 이 때, 만들어진 화면이 보임
                startActivity(seconIntent)
            }
        }
    }
}