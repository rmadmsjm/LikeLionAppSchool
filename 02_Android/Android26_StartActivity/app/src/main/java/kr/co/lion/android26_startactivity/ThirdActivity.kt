package kr.co.lion.android26_startactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android26_startactivity.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    lateinit var activityThirdBinding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityThirdBinding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(activityThirdBinding.root)

        activityThirdBinding.apply {
            textViewThrid.apply {
                // Intent에 담겨서 전달된 데이터를 가져오기
                val data1 = intent?.getIntExtra("data1", 0)
                val data2 = intent?.getDoubleExtra("data2", 0.0)
                val data3 = intent?.getBooleanExtra("data3", false)

                text = "ThirdActivity\n"
                append("data1 : ${data1}\n")
                append("data2 : ${data2}\n")
                append("data3 : $data3")
            }

            buttonFinishThird.setOnClickListener {
                // 현재 Activity가 종료되고 이전 Activity가 실행될 때
                // 혐재 Activity의 작업의 결과를 이전 Activity가 알 수 있도록 설정해줌
                // RESULT_OK : 작업이 잘 되었다는 의미로 사용함
                // RESULT_CANCELED : 작업이 취소 되었다는 의미로 사용함
                //                   사용자가 Back Button을 누르면 이 값이 설정됨
                // RESULT_FIRST_USER : 그 외의 결과를 정의할 때 사용함
                //                     +1, +2, +3 식으로 해서 다양한 결과를 정의함
//                 setResult(RESULT_OK)

                // 이전 Activity로 전달되는 Intent
                val resultIntent = Intent()
                resultIntent.putExtra("value1", 300)
                resultIntent.putExtra("value2", 33.33)
                resultIntent.putExtra("value3", true)

                // 작업의 결과를 설정한때 Intent도 설정해주면 이전 Activity로 전달됨
                setResult(RESULT_OK, resultIntent)


                finish()
            }
        }
    }
}