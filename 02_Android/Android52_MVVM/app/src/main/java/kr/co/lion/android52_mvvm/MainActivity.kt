package kr.co.lion.android52_mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import kr.co.lion.android52_mvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    lateinit var testViewModel: TestViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = DataBindingUtil.inflate(layoutInflater, R.layout.activity_main, null, false)

        testViewModel = TestViewModel()
        activityMainBinding.testViewModel = testViewModel
        activityMainBinding.lifecycleOwner = this

        setContentView(activityMainBinding.root)
    }
}