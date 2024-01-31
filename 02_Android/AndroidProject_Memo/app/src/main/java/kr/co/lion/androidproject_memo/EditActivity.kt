package kr.co.lion.androidproject_memo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.androidproject_memo.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {

    lateinit var activityEditBinding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityEditBinding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(activityEditBinding.root)
    }
}