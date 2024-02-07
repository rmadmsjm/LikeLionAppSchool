package kr.co.lion.androidproject1test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kr.co.lion.androidproject1test.databinding.ActivityShowBinding

class ShowActivity : AppCompatActivity() {

    lateinit var activityShowBinding: ActivityShowBinding

    // ModifyActivity Launcher
    lateinit var modifyActivityLauncher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityShowBinding = ActivityShowBinding.inflate(layoutInflater)
        setContentView(activityShowBinding.root)

        setLauncher()
        setToolbar()
        //setView()
        setView2()
    }

    // Launcher 설정
    fun setLauncher() {
        // ModifyActivity Launcher
        val contract1 = ActivityResultContracts.StartActivityForResult()
        modifyActivityLauncher = registerForActivityResult(contract1) {
        }
    }

    // 툴바
    fun setToolbar() {
        activityShowBinding.apply {
            toolbarShow.apply {
                // 타이틀
                title = "동물 정보"

                // back button
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }

                // 메뉴
                inflateMenu(R.menu.menu_show)
                setOnMenuItemClickListener {
                    // 메뉴 항목 id로 분기
                    when(it.itemId) {
                        // 수정
                        R.id.menu_item_show_modify -> {
                            val modifyIntent = Intent(this@ShowActivity, ModifyActivity::class.java)
                            // 동물 순서값 저장
                            val position = intent.getIntExtra("position", 0)
                            modifyIntent.putExtra("position", position)
                            modifyActivityLauncher.launch(modifyIntent)
                        }

                        // 삭제
                        R.id.menu_item_show_delete -> {
                        }
                    }

                    true
                }
            }
        }
    }

    // View 설정
    fun setView() {
        activityShowBinding.apply {
            // textView
            textViewShowInfo.apply {
                // 항목 순서값 가져오기
                val position = intent.getIntExtra("position", 0)
                // position 번째 객체 추출
                val animal = Util.animalList[position]

                // 공통
                text = "동물 종류 : ${animal.type.str}\n"
                append("이름 : ${animal.name}\n")
                append("나이 : ${animal.age}살\n")

                when(animal.type) {
                    // 사자
                    AnimalType.ANIMAL_TYPE_LION -> {
                        val lion = animal as Lion
                        append("털의 개수 : ${lion.furCnt}개\n")
                        append("성별 : ${lion.gender}\n")
                    }

                    // 호랑이
                    AnimalType.ANIMAL_TYPE_TIGER -> {
                        val tiger = animal as Tiger
                        append("줄무늬 개수 : ${tiger.lineCount}개\n")
                        append("몸무게 : ${tiger.weight}kg\n")
                    }

                    // 기린
                    AnimalType.ANIMAL_TYPE_GIRAFFE -> {
                        val giraffe = animal as Giraffe
                        append("목의 길이 : ${giraffe.neckLength}cm\n")
                        append("달리는 속도 : ${giraffe.runSpeed}km/h\n")
                    }
                }
            }
        }
    }

    fun setView2() {
        activityShowBinding.apply {
            // textView
            textViewShowInfo.apply {
                // 항목 순서값 가져오기
                val position = intent.getIntExtra("position", 0)
                // position 번째 객체 추출
                val animal = Util.animalList[position]

                // 공통
                text = "동물 종류 : ${animal.type.str}\n"
                append("이름 : ${animal.name}\n")
                append("나이 : ${animal.age}살\n")

                // 스마트 캐스팅 : 특정 조건을 만족하면 자동으로 형변환 됨
                // 클래스 타입으로 분기
                if(animal is Lion) {
                    // 사자
                    append("털의 개수 : ${animal.furCnt}개\n")
                    append("성별 : ${animal.gender}\n")
                } else if(animal is Tiger) {
                    // 호랑이
                    append("줄무늬 개수 : ${animal.lineCount}개\n")
                    append("몸무게 : ${animal.weight}kg\n")
                } else if(animal is Giraffe) {
                    // 기린
                    append("목의 길이 : ${animal.neckLength}cm\n")
                    append("달리는 속도 : ${animal.runSpeed}km/h\n")
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // 다른 곳 갔다 왔을 경우 출력 내용을 다시 구성
        setView2()
    }
}