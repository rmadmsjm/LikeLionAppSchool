package kr.co.lion.ex10_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kr.co.lion.ex10_activity.databinding.ActivityTotalPointAvgBinding

class TotalPointAvgActivity : AppCompatActivity() {

    lateinit var activityTotalPointAvgBinding: ActivityTotalPointAvgBinding
    var korTotalPoint = 0
    var korAvg = 0
    var engTotalPoint = 0
    var engAvg = 0
    var mathTotalPoint = 0
    var mathAvg = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityTotalPointAvgBinding = ActivityTotalPointAvgBinding.inflate(layoutInflater)
        setContentView(activityTotalPointAvgBinding.root)

        totalKorPointAvg()
        totalEngPointAvg()
        totalMathPointAvg()

        setViewEvent()
    }

    fun init() {
    }

    fun setViewEvent() {
        activityTotalPointAvgBinding.apply {
            val totalPoint = korTotalPoint + engTotalPoint + mathTotalPoint
            totalPointAvgTextVIew.apply {
                append("\n")
                append("\n전체 총점 : ${totalPoint}점")
                append("\n전체 평균 : ${totalPoint/3}점")
            }

            totalPointAvgOkBtn.setOnClickListener {
                finish()
            }
        }
    }

    // 국어 총점 및 평균 계산 메서드
    fun totalKorPointAvg() {
        // Intent에 담겨서 전달된 데이터를 가져오기
        val korArray = intent.getIntArrayExtra("kor")

        if (korArray != null) {
            // 국어 총점
            var index = 0
            while (index < korArray.size) {
                korTotalPoint += korArray[index]
                index++
            }

            // 국어 평균
            if (korArray.size > 0) {
                korAvg = korTotalPoint / korArray.size
            }

            activityTotalPointAvgBinding.totalPointAvgTextVIew.append("\n국어 총점 : ${korTotalPoint}점")
            activityTotalPointAvgBinding.totalPointAvgTextVIew.append("\n국어 평균 : ${korAvg}점")
        } else {
            val errorMessage = "국어 점수 정보가 전달되지 않았습니다"
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    // 영어 총점 및 평균 계산 메서드
    fun totalEngPointAvg() {
        // Intent에 담겨서 전달된 데이터를 가져오기
        val engArray = intent.getIntArrayExtra("eng")

        if (engArray != null) {
            // 영어 총점
            var index = 0
            while (index < engArray.size) {
                engTotalPoint += engArray[index]
                index++
            }

            // 영어 평균
            if (engArray.size > 0) {
                engAvg = engTotalPoint / engArray.size
            }

            activityTotalPointAvgBinding.totalPointAvgTextVIew.append("\n영어 총점 : ${engTotalPoint}점")
            activityTotalPointAvgBinding.totalPointAvgTextVIew.append("\n영어 평균 : ${engAvg}점")
        } else {
            val errorMessage = "영어 점수 정보가 전달되지 않았습니다"
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }

    }

    // 수학 총점 및 평균 계산 메서드
    fun totalMathPointAvg() {
        // Intent에 담겨서 전달된 데이터를 가져오기
        val mathArray = intent.getIntArrayExtra("math")

        if (mathArray != null) {
            // 수학 총점
            var index = 0
            while (index < mathArray.size) {
                mathTotalPoint += mathArray[index]
                index++
            }

            // 수학 평균
            if (mathArray.size > 0) {
                mathAvg = mathTotalPoint / mathArray.size
            }

            activityTotalPointAvgBinding.totalPointAvgTextVIew.append("\n수학 총점 : ${mathTotalPoint}점")
            activityTotalPointAvgBinding.totalPointAvgTextVIew.append("\n수학 평균 : ${mathAvg}점")
        } else {
            val errorMessage = "수학 점수 정보가 전달되지 않았습니다"
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }
}