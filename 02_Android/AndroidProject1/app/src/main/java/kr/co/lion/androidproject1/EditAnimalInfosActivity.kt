package kr.co.lion.androidproject1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.androidproject1.databinding.ActivityEditAnimalInfosBinding

class EditAnimalInfosActivity : AppCompatActivity() {

    lateinit var activityEditAnimalInfosBinding: ActivityEditAnimalInfosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityEditAnimalInfosBinding = ActivityEditAnimalInfosBinding.inflate(layoutInflater)
        setContentView(activityEditAnimalInfosBinding.root)

        setToolbar()
    }

    // 툴바
    fun setToolbar() {
        activityEditAnimalInfosBinding.apply {
            toolbarAnimalEditInfos.apply {
                title = "동물 정보 수정"

                // back
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }

                // 메뉴
                inflateMenu(R.menu.menu_edit)
                setOnMenuItemClickListener {
                    // id로 분기
                    when(it.itemId) {
                        R.id.menuItemEditOk -> {
                        }
                    }

                    true
                }
            }
        }
    }
}