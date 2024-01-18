package kr.co.lion.ex05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.ex05.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        var result = 0

        activityMainBinding.apply {

            // +
            plusBtn.apply {
                setOnClickListener {
                    var num1 = num1.text.toString().toInt()
                    var num2 = num2.text.toString().toInt()
                    result = num1 + num2
                    resultText.text = "결과: $result"
                }
            }

            // -
            minusBtn.apply {
                setOnClickListener {
                    var num1 = num1.text.toString().toInt()
                    var num2 = num2.text.toString().toInt()
                    result = num1 - num2
                    resultText.text = "결과: $result"
                }
            }

            // *
            multiplyBtn.apply {
                setOnClickListener {
                    var num1 = num1.text.toString().toInt()
                    var num2 = num2.text.toString().toInt()
                    result = num1 * num2
                    resultText.text = "결과: $result"
                }
            }

            // /
            divisionBtn.apply {
                setOnClickListener {
                    var num1 = num1.text.toString().toInt()
                    var num2 = num2.text.toString().toInt()
                    result = num1 / num2
                    resultText.text = "결과: $result"
                }
            }
        }
    }
}