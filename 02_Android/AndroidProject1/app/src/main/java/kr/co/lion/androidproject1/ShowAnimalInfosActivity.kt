package kr.co.lion.androidproject1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.androidproject1.databinding.ActivityShowAnimalInfosBinding

class ShowAnimalInfosActivity : AppCompatActivity() {

    lateinit var activityShowAnimalInfosBinding: ActivityShowAnimalInfosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityShowAnimalInfosBinding = ActivityShowAnimalInfosBinding.inflate(layoutInflater)
        setContentView(activityShowAnimalInfosBinding.root)
    }

    // 툴바
    fun setToolbar() {
        activityShowAnimalInfosBinding.apply {
            toolbarShowAnimalInfos.apply {
                title = "동물 정보"

                // back
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }

                // 메뉴
                inflateMenu(R.menu.menu_show)
                setOnMenuItemClickListener {
                    // id로 분기
                    when(it.itemId) {
                        R.id.menuItemShowEdit -> {
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