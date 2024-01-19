package kr.co.lion.ex06

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.ex06.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    var totalScore = 0
    var totalAvg = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        setView()
    }

    fun setView() {
        activityMainBinding.apply {
            submitBtn.setOnClickListener {
                resultText.apply {
                    // 버튼 누를 때 resultText 리셋
                    resultText.text = "입력 정보\n"
                    // 입력 정보
                    append("\n이름 : ${nameInput.text}")
                    append("\n나이 : ${ageInput.text}살")
                    append("\n국어 : ${korInput.text}점")
                    append("\n수학 : ${mathInput.text}점")
                    // 총점, 평균 메서드
                    printScoreAvg()
                }
            }
        }
    }

    fun printScoreAvg() {
        activityMainBinding.apply {
            // 점수 가져오기
            val korValue = korInput.text.toString().toInt()
            val mathValue = mathInput.text.toString().toInt()

            // 총점, 평균 계산
            totalScore = korValue + mathValue
            totalAvg = totalScore / 2

            // 총점, 평균
            resultText.apply {
                append("\n총점 : ${totalScore}점")
                append("\n평균 : ${totalAvg}점")
            }
        }
    }
}