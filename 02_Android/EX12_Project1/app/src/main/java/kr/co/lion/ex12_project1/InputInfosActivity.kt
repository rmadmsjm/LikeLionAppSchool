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
    }

    fun initView() {
    }

    fun setToolbar() {
    }

    fun setView() {
    }
}