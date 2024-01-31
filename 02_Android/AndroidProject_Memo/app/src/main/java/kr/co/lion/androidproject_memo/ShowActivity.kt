package kr.co.lion.androidproject_memo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.androidproject_memo.databinding.ActivityShowBinding

class ShowActivity : AppCompatActivity() {

    lateinit var acitivityShowBinding: ActivityShowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        acitivityShowBinding = ActivityShowBinding.inflate(layoutInflater)
        setContentView(acitivityShowBinding.root)
    }
}