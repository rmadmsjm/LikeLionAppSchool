package kr.co.lion.android34_bottomappbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android34_bottomappbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.apply {
            bottomAppBar.apply {
                // Navigation
                setNavigationIcon(R.drawable.menu_24px)
                setNavigationOnClickListener {
                    textView.text = "메뉴를 눌렀습니다"
                }

                // 메뉴
                inflateMenu(R.menu.menu_main)
                setOnMenuItemClickListener {
                    // 메뉴 id로 분기
                    textView.text = when(it.itemId) {
                        R.id.item1 -> "메뉴1을 눌렀습니다"
                        R.id.item2 -> "메뉴2를 눌렀습니다"
                        R.id.item3 -> "메뉴3을 눌렀습니다"
                        else -> ""
                    }
                    true
                }

                // FloatingActionButton
                // layout 파일에서 layout_anchor 속성에 BottomAppBar의 id를 설정해야 함
                floatingActionButton.setOnClickListener {
                    textView.text = "FloatingActionButton을 눌렀습니다"
                }
            }
        }
    }
}