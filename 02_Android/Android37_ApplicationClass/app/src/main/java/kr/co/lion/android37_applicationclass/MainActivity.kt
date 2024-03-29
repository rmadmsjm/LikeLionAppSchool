package kr.co.lion.android37_applicationclass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android37_applicationclass.databinding.ActivityMainBinding

// Application Class
// 어플리케이션 마다 하나씩 존재할 수 있는 클래스
// Application을 상속 받으며 android_manifest.xml 의 applicaiton 태그의 name 속성에 설정함
// 안드로이드 애플리케이션이 가지고 있는 실행 요소(acitivity, service, br, cp) 중 하나라도 실행이 되면 OS가 객체를 생성하며 os가 관리함
// 같은 애플리케이션 내라면 어디서든지 접근이 가능하기 때문에 공통적으로 사용하는 프로퍼티들을 관리하는 용도로 사용함

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.apply {
            // Application 객체 가져오기
            val appClass = application as AppClass
            appClass.value1 = 100
            appClass.value2 = 11.11
            appClass.value3 = "문자열1"

            val secondIntent = Intent(this@MainActivity, SecondActivity::class.java)
            startActivity(secondIntent)
        }
    }
}