package kr.co.lion.androidproject1test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.androidproject1test.databinding.ActivityModifyBinding

class ModifyActivity : AppCompatActivity() {

    lateinit var activityModifyBinding: ActivityModifyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityModifyBinding = ActivityModifyBinding.inflate(layoutInflater)
        setContentView(activityModifyBinding.root)

        setToolbar()
    }

    // 툴바
    fun setToolbar() {
        activityModifyBinding.apply {
            toolbarModify.apply {
                // 타이틀
                title = "동물 정보 수정"

                // back button
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }

                // 메뉴
                inflateMenu(R.menu.menu_modify)
            }
        }
    }
}