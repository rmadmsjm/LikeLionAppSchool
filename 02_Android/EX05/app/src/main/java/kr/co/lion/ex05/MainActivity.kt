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


        // 강사님 설명
        setEvent()

    }

    // 강사님 설명
    // 메서드를 나눠서 작업
    // 각 뷰를 초기화 하는 메서드
//    fun initView() {
//
//    }

    // 이벤트를 설정하는 메서드
    fun setEvent() {
        activityMainBinding.apply {
            // 더하기 버튼
            buttonPlus.setOnClickListener {
                // 입력한 값 가져오기
                val number1 = editTextNumber1.text.toString().toInt()
                val number2 = editTextNumber2.text.toString().toInt()

                // 계산
                val result = number1 + number2

                // 출력
                textViewResult.text = "결과 : $result"
            }

            // 빼기 버튼
            buttonMinus.setOnClickListener {
                // 입력한 값 가져오기
                val number1 = editTextNumber1.text.toString().toInt()
                val number2 = editTextNumber2.text.toString().toInt()

                // 계산
                val result = number1 - number2

                // 출력
                textViewResult.text = "결과 : $result"
            }

            // 곱하기 버튼
            buttonMultiply.setOnClickListener {
                // 입력한 값 가져오기
                val number1 = editTextNumber1.text.toString().toInt()
                val number2 = editTextNumber2.text.toString().toInt()

                // 계산
                val result = number1 * number2

                // 출력
                textViewResult.text = "결과 : $result"
            }

            // 나누기 버튼
            buttonDivide.setOnClickListener {
                // 입력한 값 가져오기
                val number1 = editTextNumber1.text.toString().toInt()
                val number2 = editTextNumber2.text.toString().toInt()

                // 계산
                val result = number1 / number2

                // 출력
                textViewResult.text = "결과 : $result"
            }
        }
    }
}