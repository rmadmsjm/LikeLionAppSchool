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
    }
}