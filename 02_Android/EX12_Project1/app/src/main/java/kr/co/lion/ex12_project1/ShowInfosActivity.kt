package kr.co.lion.ex12_project1

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.ex12_project1.databinding.ActivityShowInfosBinding

class ShowInfosActivity : AppCompatActivity() {

    lateinit var activityShowInfosBinding: ActivityShowInfosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityShowInfosBinding = ActivityShowInfosBinding.inflate(layoutInflater)
        setContentView(activityShowInfosBinding.root)

        initView()
        initToolbar()
        setView()
    }

    fun initView() {
        activityShowInfosBinding.apply {
            textViewShowInfos.apply {
                //Intent로부터 객체 추출
                val info = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    intent.getParcelableExtra("obj", InfoClass::class.java)
                } else {
                    intent.getParcelableExtra<InfoClass>("obj")
                }

                text = "이름 : ${info?.name}\n"
                append("학년 : ${info?.grade}학년\n")
                append("\n")
                append("국어 점수 : ${info?.kor}점\n")
                append("영어 점수 : ${info?.eng}점\n")
                append("수학 점수 : ${info?.math}점\n")
                append("\n")

                val total = info!!.kor + info!!.eng + info!!.math
                append("총점 : ${total}점\n")
                val avg = total / 3
                append("평균 : ${avg}점")
            }
        }
    }

    fun initToolbar() {
        activityShowInfosBinding.apply {
            toolbarShowInfos.apply {
                title = "학생 정보 보기"
                setNavigationIcon(R.drawable.arrow_back_24px)
            }
        }
    }

    fun setView() {
        activityShowInfosBinding.apply {
            toolbarShowInfos.apply {
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }
            }
        }
    }
}