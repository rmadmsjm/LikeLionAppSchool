package kr.co.lion.androidproject4boardapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import kr.co.lion.androidproject4boardapp.databinding.ActivityContentBinding
import kr.co.lion.androidproject4boardapp.databinding.HeaderContentDrawerBinding

class ContentActivity : AppCompatActivity() {

    lateinit var activityContentBinding: ActivityContentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityContentBinding = ActivityContentBinding.inflate(layoutInflater)
        setContentView(activityContentBinding.root)

        settingNavigationView()

//        activityContentBinding.buttonTest.setOnClickListener {
//            activityContentBinding.drawerLayoutContent.open()
//        }
    }

    // 네비게이션 뷰 설정
    fun settingNavigationView() {
        activityContentBinding.apply {
            navigationViewContent.apply {
                // 헤더로 보여줄 View 생성
                val headerContentDrawerBinding = HeaderContentDrawerBinding.inflate(layoutInflater)
                // 헤더로 보여줄 View 설정
                addHeaderView(headerContentDrawerBinding.root)

                // 사용자 닉네임 설정
                headerContentDrawerBinding.headerContentDrawerNickName.text = "삼사오"

                // 메뉴 눌렀을 때 동작하는 리스너
                setNavigationItemSelectedListener {
                    // 딜레이
                    SystemClock.sleep(200)

                    // 메뉴 id로 분기
                    when(it.itemId) {
                        // 전체 게시판
                        R.id.menuItemContentNavigationAll -> {
                            // NavigationView 닫기
                            drawerLayoutContent.close()
                        }
                        // 자유 게시판
                        R.id.menuItemContentNavigation1 -> {
                            // NavigationView 닫기
                            drawerLayoutContent.close()
                        }
                        // 유머 게시판
                        R.id.menuItemContentNavigation2 -> {
                            // NavigationView 닫기
                            drawerLayoutContent.close()
                        }
                        // 시사 게시판
                        R.id.menuItemContentNavigation3 -> {
                            // NavigationView 닫기
                            drawerLayoutContent.close()
                        }
                        // 스포츠 게시판
                        R.id.menuItemContentNavigation4 -> {
                            // NavigationView 닫기
                            drawerLayoutContent.close()
                        }
                    }

                    true
                }
            }
        }
    }
}