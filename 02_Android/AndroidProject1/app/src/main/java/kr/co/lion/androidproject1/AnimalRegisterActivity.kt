package kr.co.lion.androidproject1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.androidproject1.databinding.ActivityAnimalRegisterBinding

class AnimalRegisterActivity : AppCompatActivity() {

    lateinit var activityAnimalRegisterBinding: ActivityAnimalRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityAnimalRegisterBinding = ActivityAnimalRegisterBinding.inflate(layoutInflater)
        setContentView(activityAnimalRegisterBinding.root)

        setToolbar()
    }

    // 툴바
    fun setToolbar() {
        activityAnimalRegisterBinding.apply {
            toolbarAnimalRegister.apply {
                title = "동물 등록"

                // back
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }

                // 메뉴
                inflateMenu(R.menu.menu_register)
                setOnMenuItemClickListener {
                    // id로 분기
                    when(it.itemId) {
                        R.id.menuItemRegisterOk -> {
                        }
                    }
                    true
                }
            }
        }
    }
}