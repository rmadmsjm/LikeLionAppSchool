package kr.co.lion.androidproject1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.androidproject1.databinding.ActivityEditAnimalInfosBinding

class EditAnimalInfosActivity : AppCompatActivity() {

    lateinit var activityEditAnimalInfosBinding: ActivityEditAnimalInfosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityEditAnimalInfosBinding = ActivityEditAnimalInfosBinding.inflate(layoutInflater)
        setContentView(activityEditAnimalInfosBinding.root)
    }
}