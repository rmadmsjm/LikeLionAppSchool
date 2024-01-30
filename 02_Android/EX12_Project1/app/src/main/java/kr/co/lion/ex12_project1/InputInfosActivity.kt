package kr.co.lion.ex12_project1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.ex12_project1.databinding.ActivityInputInfosBinding

class InputInfosActivity : AppCompatActivity() {

    lateinit var activityInputInfosBinding: ActivityInputInfosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityInputInfosBinding = ActivityInputInfosBinding.inflate(layoutInflater)
        setContentView(activityInputInfosBinding.root)

        initView()
        initToolbar()
        setView()
    }

    fun initView() {
    }

    fun initToolbar() {
        activityInputInfosBinding.apply {
            toolbarInputInfos.apply {
                title = "학생 정보 입력"
                inflateMenu(R.menu.input_infos_menu)
                setNavigationIcon(R.drawable.arrow_back_24px)
            }
        }
    }

    fun setView() {
        activityInputInfosBinding.apply {
            toolbarInputInfos.apply {
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }
            }
        }
    }
}