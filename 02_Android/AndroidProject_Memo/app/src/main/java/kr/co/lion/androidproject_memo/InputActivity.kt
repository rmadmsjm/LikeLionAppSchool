package kr.co.lion.androidproject_memo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import kr.co.lion.androidproject_memo.databinding.ActivityInputBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
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

    // Toolbar
    fun setToolbar() {
        activityInputBinding.apply {
            toolbarInput.apply {
                // 타이틀
                title = "메모 작성"

                // back button
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }

                // 메뉴 설정
                inflateMenu(R.menu.menu_input)
                // 메뉴 리스너
                setOnMenuItemClickListener {
                    // 메뉴 id로 분기
                    when(it.itemId) {
                        R.id.menuItemInputSubmit -> {
                            inputDone()
                        }
                    }
                    true
                }
            }
        }
    }

    // View 설정
    fun setView() {
        activityInputBinding.apply {
            // 메모 제목 textField 포커스
            textFieldInputTitle.requestFocus()

            // 키보드 올리기
            showSoftInput(textFieldInputTitle)

            // 메모 내용 textField 엔터키 클릭 시 입력 완료 처리
            textFieldInputContext.setOnEditorActionListener { v, actionId, event ->
                inputDone()
                true
            }
        }
    }

    // 입력 완료 처리 메서드
    fun inputDone() {
        activityInputBinding.apply {
            // 입력값 가져오기
            val title = textFieldInputTitle.text.toString()
            val context = textFieldInputContext.text.toString()

            // 작성 날짜
            val dateFormat = SimpleDateFormat("yyyy.MM.dd EEEE", Locale.getDefault())
            val currentDate = dateFormat.format(Date(System.currentTimeMillis()))
            val date = currentDate

            // 데이터 유효성 검사
            if(title.isEmpty()) {
                textFieldLayoutTitle.error = "제목을 입력해 주세요"
                textFieldInputTitle.requestFocus()
                showSoftInput(textFieldInputTitle)
                if(context.isEmpty()) {
                    textFieldLayoutContext.error = "내용을 입력해 주세요"
                }
                return
            }
            if(context.isEmpty()) {
                textFieldLayoutContext.error = "내용을 입력해 주세요"
                textFieldInputContext.requestFocus()
                showSoftInput(textFieldInputContext)
                return
            }

            // 객체에 데이터 담기
            val memoData = MemoClass(title, date, context)

            // 데이터 전달 Intent
            val resultIntent = Intent()
            resultIntent.putExtra("memoData", memoData)

            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }

    // 키보드 올리는 메서드
    fun showSoftInput(focusView: TextInputEditText) {
        thread {
            SystemClock.sleep(440)
            val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(focusView, 0)
        }
    }
}