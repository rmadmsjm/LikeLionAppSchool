package kr.co.lion.androidproject1test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kr.co.lion.androidproject1test.databinding.ActivityShowBinding

class ShowActivity : AppCompatActivity() {

    lateinit var activityShowBinding: ActivityShowBinding

    // ModifyActivity Launcher
    lateinit var modifyActivityLauncher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityShowBinding = ActivityShowBinding.inflate(layoutInflater)
        setContentView(activityShowBinding.root)

        setLauncher()
        setToolbar()
        setView()
    }

    // Launcher 설정
    fun setLauncher() {
        // ModifyActivity Launcher
        val contract1 = ActivityResultContracts.StartActivityForResult()
        modifyActivityLauncher = registerForActivityResult(contract1) {
        }
    }

    // 툴바
    fun setToolbar() {
        activityShowBinding.apply {
            toolbarShow.apply {
                // 타이틀
                title = "동물 정보"

                // back button
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }

                // 메뉴
                inflateMenu(R.menu.menu_show)
                setOnMenuItemClickListener {
                    // 메뉴 항목 id로 분기
                    when(it.itemId) {
                        // 수정
                        R.id.menu_item_show_modify -> {
                            val modifyIntent = Intent(this@ShowActivity, ModifyActivity::class.java)
                            modifyActivityLauncher.launch(modifyIntent)
                        }

                        // 삭제
                        R.id.menu_item_show_delete -> {
                        }
                    }

                    true
                }
            }
        }
    }

    // View 설정
    fun setView() {
        activityShowBinding.apply {
            // textView
            textViewShowInfo.apply {
                // 공통
                text = "동물 종류 : \n"
                append("이름 : \n")
                append("나이 : 살\n")

                // 사자
                append("털의 개수 : 개\n")
                append("성별 : \n")

                // 호랑이
                append("줄무늬 개수 : 개\n")
                append("몸무게 : kg\n")

                // 기린
                append("목의 길이 : cm\n")
                append("달리는 속도 : km/h\n")
            }
        }
    }
}