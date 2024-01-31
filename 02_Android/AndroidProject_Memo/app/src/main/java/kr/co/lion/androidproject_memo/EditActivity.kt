package kr.co.lion.androidproject_memo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.androidproject_memo.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {

    lateinit var activityEditBinding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityEditBinding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(activityEditBinding.root)
    }

    // Toolbar
    fun setToolbar() {
        activityEditBinding.apply {
            toolbarEdit.apply {
                // 타이틀
                title = "메모 수정"

                // back button
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }

                // 메뉴 설정
                inflateMenu(R.menu.menu_edit)
                // 메뉴 리스너
                setOnMenuItemClickListener {
                    // 메뉴 id로 분기
                    when(it.itemId) {
                        R.id.menuItemEditSubmit -> {
                            setResult(RESULT_OK)
                            finish()
                        }
                    }

                    true
                }
            }
        }
    }
}