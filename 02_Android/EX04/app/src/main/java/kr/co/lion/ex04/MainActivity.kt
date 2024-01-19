package kr.co.lion.ex04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.ex04.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        setView()
    }

    fun setView() {
        activityMainBinding.apply {
            val num1 = numInput1.text.toString()
            val num2 = numInput2.text.toString()
            var result = 0

            when(toggleGroup.checkedButtonId) {
                R.id.plusToggleBtn -> {
                    result = num1.toInt() + num2.toInt()
                }
                R.id.minusToggleBtn -> {
                    result = num1.toInt() - num2.toInt()
                }
                R.id.multiplyToggleBtn -> {
                    result = num1.toInt() * num2.toInt()
                }
                R.id.divideToggleBtn -> {
                    result = num1.toInt() / num2.toInt()
                }
                else -> {
                    result = -1
                }
            }

            resultBtn.setOnClickListener {
                resultText.text = "결과 : $result"
            }
        }
    }
}