package kr.co.lion.androidproject_memo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kr.co.lion.androidproject_memo.databinding.ActivityShowBinding

class ShowActivity : AppCompatActivity() {

    lateinit var acitivityShowBinding: ActivityShowBinding

    // EditActivity Launcher
    lateinit var editActivityLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        acitivityShowBinding = ActivityShowBinding.inflate(layoutInflater)
        setContentView(acitivityShowBinding.root)

        initData()
        setToolbar()
    }

    // 기본 데이터 및 객체 세팅
    fun initData() {
        // EditActivity Launcher
        val editContract = ActivityResultContracts.StartActivityForResult()
        editActivityLauncher = registerForActivityResult(editContract) {
        }
    }

    // Toolbar
    fun setToolbar() {
        acitivityShowBinding.apply {
            toolbarShow.apply {
                // 타이틀
                title = "메모 보기"

                // back button
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }

                // 메뉴 설정
                inflateMenu(R.menu.menu_show)
                // 메뉴 리스너
                setOnMenuItemClickListener {
                    // 메뉴 id로 분기
                    when(it.itemId) {
                        R.id.menuItemShowEdit -> {
                            val editIntent = Intent(this@ShowActivity, EditActivity::class.java)
                            editActivityLauncher.launch(editIntent)
                        }
                        R.id.menuItemShowDelete -> {
                        }
                    }

                    true
                }
            }
        }
    }
}