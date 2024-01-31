package kr.co.lion.androidproject_memo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.androidproject_memo.databinding.ActivityInputBinding

class InputActivity : AppCompatActivity() {

    lateinit var activityInputBinding: ActivityInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityInputBinding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(activityInputBinding.root)
    }
}