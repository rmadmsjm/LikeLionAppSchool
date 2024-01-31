package kr.co.lion.ex12_project2

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.inputmethod.InputMethodManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import kr.co.lion.ex12_project2.databinding.ActivityInputBinding
import kotlin.concurrent.thread

class InputActivity : AppCompatActivity() {

    lateinit var activityInputBinding: ActivityInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityInputBinding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(activityInputBinding.root)

        setToolbar()
        setView()
    }

    // 툴바 구성
    fun setToolbar() {
        activityInputBinding.apply {
            toolbarInput.apply {
                // 타이틀
                title = "학생 정보 입력"
                // back button
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }
                // 메뉴
                inflateMenu(R.menu.menu_input)
                setOnMenuItemClickListener {
                    when(it.itemId) {
                        R.id.menuInputDone -> {
                            processInputDone()
                        }
                    }
                    true
                }
            }
        }
    }

    // 뷰 설정
    fun setView() {
        activityInputBinding.apply {
            // 뷰에 포커스 주기
            textFieldInputName.requestFocus()

            // 키보드 올리기
            // 이때, View 지정해야 함
            showSoftInput(textFieldInputName)

            // 수학 점수 입력칸
            // 엔터키 누르면 입력 완료 처리
            textFieldInputMath.setOnEditorActionListener { v, actionId, event ->
                processInputDone()
                true
            }
        }
    }

    // 입력 완료 처리
    fun processInputDone() {
        // Toast.makeText(this@InputActivity, "눌러졌습니다", Toast.LENGTH_SHORT).show()

        activityInputBinding.apply {
            // 사용자가 입력한 값 가져오기
            val name = textFieldInputName.text.toString()
            val grade = textFieldInputGrade.text.toString()
            val kor = textFieldInputKor.text.toString()
            val eng = textFieldInputEng.text.toString()
            val math = textFieldInputMath.text.toString()

            // 입력 검사
            if(name.isEmpty()) {
                showDialog("이름 입력 오류", "이름을 입력해주세요", textFieldInputName)
                return
            }
            if(grade.isEmpty()) {
                showDialog("학년 입력 오류", "학년을 입력해주세요", textFieldInputGrade)
                return
            }
            if(kor.isEmpty()) {
                showDialog("국어 점수 입력 오류", "국어 점수를 입력해주세요", textFieldInputKor)
                return
            }
            if(eng.isEmpty()) {
                showDialog("영어 점수 입력 오류", "영어 점수를 입력해주세요", textFieldInputEng)
                return
            }
            if(math.isEmpty()) {
                showDialog("수학 점수 입력 오류", "수학 점수를 입력해주세요", textFieldInputMath)
                return
            }

            // 입력 받은 정보를 객체에 담음
            val studentData = StudentData(name, grade.toInt(), kor.toInt(), eng.toInt(), math.toInt())

            Snackbar.make(activityInputBinding.root, "등록이 완료되었습니다", Snackbar.LENGTH_SHORT)

            // 이전으로 돌아가기
            val resultIntent = Intent()
            resultIntent.putExtra("studentData", studentData)

            setResult(RESULT_OK)
            finish()
        }
    }

    // 다이얼로그 보여주는 메서드
    fun showDialog(title:String, message:String, focusView:TextInputEditText) {
        // 다이얼로그 보여주기
        var builder = MaterialAlertDialogBuilder(this@InputActivity).apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
                focusView.setText("")
                focusView.requestFocus()
                showSoftInput(focusView)
            }
        }
        builder.show()
    }

    // 포커스를 주고 키보드를 올려주는 메서드
    fun showSoftInput(focusView: TextInputEditText) {
        activityInputBinding.apply {
            // 키보드 올리기
            // 이때, View 지정해야 함
            thread {
                SystemClock.sleep(200)
                // 입력에 관련된 것을 관리하는 객체 추출
                val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.showSoftInput(focusView, 0)
            }
        }
    }
}