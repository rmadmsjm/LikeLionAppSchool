package kr.co.lion.ex12_project1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.ex12_project1.databinding.ActivityInputInfosBinding

class InputInfosActivity : AppCompatActivity() {

    lateinit var activityInputInfosBinding: ActivityInputInfosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityInputInfosBinding = ActivityInputInfosBinding.inflate(layoutInflater)
        setContentView(activityInputInfosBinding.root)

        initView()
        initToolbar()
        setView()
    }

    fun initView() {
    }

    fun initToolbar() {
        activityInputInfosBinding.apply {
            toolbarInputInfos.apply {
                title = "학생 정보 입력"
                inflateMenu(R.menu.input_infos_menu)
                setNavigationIcon(R.drawable.arrow_back_24px)
            }
        }
    }

    fun setView() {
        activityInputInfosBinding.apply {
            toolbarInputInfos.apply {
                setOnMenuItemClickListener {
                    when(it.itemId) {
                        R.id.inputInfosMenuItemOk -> {
                            val name = textInputName.text.toString()
                            val grade = textInputGrade.text.toString().toInt()
                            val kor = textInputKor.text.toString().toInt()
                            val eng = textInputEng.text.toString().toInt()
                            val math = textInputMath.text.toString().toInt()

                            val info = InfoClass(name, grade, kor, eng, math)

                            // 데이터를 담을 Intent를 생성
                            val resultIntent = Intent()
                            // 객체를 Intent에 저장할 때 writeToParcel 메서가 호출됨
                            resultIntent.putExtra("obj", info)

                            // 결과 세팅
                            setResult(RESULT_OK, resultIntent)

                            finish()
                        }
                    }
                    true
                }

                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }
            }
        }
    }
}