package kr.co.lion.android27_toolbar

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android27_toolbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.apply {
            toolbar.apply {
                // 타이틀
                title = "툴바 입니다"
                // 타이틀 문자열 색상
                setTitleTextColor(Color.WHITE)

                // res/menu/main_menu.xml 파일을 이용해 툴바의 메뉴 생성
                // id : 각 메뉴를 구분하기 위한 이름
                // title : 메뉴에 표시되는 문자열
                // showAsAction : 메뉴 항목을 툴바에 배치할 것인지 설정
                //      always : 항상 툴바에 배치 (not 추천)
                //      ifRoom : 공간이 허락된 경우 툴바에 배치
                //      naver : 툴바에 배치하지 않음
                //      withText : 아이콘이 설정되어 있을 경우, 아이콘이 보여지고 공간이 허락되면 title에 설정된 문자열도 보임
                //
                inflateMenu(R.menu.main_menu)

                // 메뉴를 선택하면 동작하는 리스너
                // 매개변수에는 사용자가 선택한 메뉴 항목의 객체가 전달됨
                setOnMenuItemClickListener {
                    // 선태한 메뉴의 id로 분기
                    when(it.itemId) {
                        R.id.menuItem1 -> textView.text = "메뉴 1을 선택했습니다"
                        R.id.menuItem2 -> textView.text = "메뉴 2를 선택했습니다"
                        R.id.menuItem31 -> textView.text = "메뉴 3-1을 선택했습니다"
                        R.id.menuItem32 -> textView.text = "메뉴 3-2를 선택했습니다"
                    }

                    // 반환값 있어야 함
                    // true, false 중 무엇을 반환하든 상관 없음
                    true
                }
            }

            buttonStartSecond.setOnClickListener {
                val newIntent = Intent(this@MainActivity, SecondActivity::class.java)
                startActivity(newIntent)
            }
        }
    }
}