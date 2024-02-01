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
}