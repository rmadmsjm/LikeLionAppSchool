package kr.co.lion.ex12_project1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.ex12_project1.databinding.ActivityShowInfosBinding

class ShowInfosActivity : AppCompatActivity() {

    lateinit var activityShowInfosBinding: ActivityShowInfosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityShowInfosBinding = ActivityShowInfosBinding.inflate(layoutInflater)
        setContentView(activityShowInfosBinding.root)

        initView()
        initToolbar()
        setView()
    }

    fun initView() {
    }

    fun initToolbar() {
        activityShowInfosBinding.apply {
            toolbarShowInfos.apply {
                title = "학생 정보 보기"
                setNavigationIcon(R.drawable.arrow_back_24px)
            }
        }
    }

    fun setView() {
        activityShowInfosBinding.apply {
            toolbarShowInfos.apply {
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }
            }
        }
    }
}