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
    }

    fun initView() {
    }

    fun setToolbar() {
    }

    fun setView() {
    }
}