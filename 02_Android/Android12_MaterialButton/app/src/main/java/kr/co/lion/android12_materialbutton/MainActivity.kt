package kr.co.lion.android12_materialbutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android12_materialbutton.databinding.ActivityMainBinding

// MaterialButton
// 그냥 Button을 사용하면 android.widget.Button 클래스
// MaterialButton을 사용하면 com.google.android.material.button.MaterialButton 클래스
// Material3 라이브러리를 적용하면 그냥 Button을 사용해도 MaterialButton처럼 사용 가능
// 이는 MaterialButton과 Button이 크게 다르지 않고 다양한 테마를 제공하는 형태로 되어 있음

// icon : 표시할 아이콘. Material3 테마 중 Icon 테마가 설정되어 있을 경우
// iconGravity : 표시할 아이콘의 위치
//  top : 버튼의 상단위치
//  start : 버튼의 좌측 끝 위치
//  end : 버튼의 우측 끝 위치
//  textTop : 버튼에 표시되어 있는 문자열 상단 위치
//  textStart : 버튼에 표시되어 있는 문자열 좌측 위치
//  textEnd : 버튼에 표시되어 있는 문자열 우측 위치

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.apply {
            // Button과 MaterialButton은 서로 같은 것으로 보면 됨
            button.setOnClickListener {
                textView.text = "Material Button을 눌렀습니다"
            }
            button2.setOnClickListener {
                textView.text = "Basic Button을 눌렀습니다"
            }

            // MaterialButtonToggleGroup 의 버튼을 선택한 상태
            toggleGroup1.check(R.id.toggleButton1)
            toggleGroup1.check(R.id.toggleButton3)

            // 선택 취소 (check 해제)
            toggleGroup1.check(R.id.toggleButton2)
            toggleGroup1.uncheck(R.id.toggleButton2)

            // SingleSelection에 true가 설정되어 있는 그룹에 버튼 선택
            toggleGroup2.check(R.id.toggleButton4)
            toggleGroup2.check(R.id.toggleButton5)

            // 그룹 내부의 버튼의 체크 상태가 변경되었을 때
            // 두 번째 : 체크 상태가 변경된 버튼 id
            // 세 번째 : 체크 상태 여부 (true/false)
            toggleGroup1.addOnButtonCheckedListener { group, checkedId, isChecked ->
                // 버튼 id로 분기
                when(checkedId) {
                    R.id.toggleButton1 -> {
                        if(isChecked) {
                            textView.text = "토글 버튼 1이 선택되었습니다"
                        } else {
                            textView.text = "토글 버튼 1이 선택 해제되었습니다"
                        }
                    }
                    R.id.toggleButton2 -> {
                        if(isChecked) {
                            textView.text = "토글 버튼 2가 선택되었습니다"
                        } else {
                            textView.text = "토글 버튼 2가 선택 해제되었습니다"
                        }
                    }
                    R.id.toggleButton3 -> {
                        if(isChecked) {
                            textView.text = "토글 버튼 3이 선택되었습니다"
                        } else {
                            textView.text = "토글 버튼 3이 선택 해제되었습니다"
                        }
                    }
                }
            }

            // singleSelection
            toggleGroup2.addOnButtonCheckedListener { group, checkedId, isChecked ->
                // 버튼 id로 분기
                when(checkedId) {
                    R.id.toggleButton4 -> {
                        if(isChecked) {
                            textView.text = "토글 버튼 4가 선택되었습니다"
                        }
                    }
                    R.id.toggleButton5 -> {
                        if(isChecked) {
                            textView.text = "토글 버튼 5가 선택되었습니다"
                        }
                    }
                    R.id.toggleButton6 -> {
                        if(isChecked) {
                            textView.text = "토글 버튼 6이 선택되었습니다"
                        } 
                    }
                }
            }
            
            buttonResult.setOnClickListener { 
                textView.text = ""
                
                // toggle1에서 체크된 버튼의 아이디를 모두 가져오기
                // singleSelection이 아니기 때문에 0개 이상이 선택되어 있을 수 있음
                toggleGroup1.checkedButtonIds.forEach { 
                    when(it) {
                        R.id.toggleButton1 -> textView.append("토글 1이 체크됨\n")
                        R.id.toggleButton2 -> textView.append("토글 2가 체크됨\n")
                        R.id.toggleButton3 -> textView.append("토글 3이 체크됨\n")
                    }
                }
                
                // toggle2에서 선택된 버튼 아이디 가져오기
                // singleSelection이기 때문에 1개만 가져옴
                when(toggleGroup2.checkedButtonId) {
                    R.id.toggleButton4 -> textView.append("토글 4가 체크됨\n")
                    R.id.toggleButton5 -> textView.append("토글 5가 체크됨\n")
                    R.id.toggleButton6 -> textView.append("토글 6이 체크됨\n")
                }
            }
        }
    }
}