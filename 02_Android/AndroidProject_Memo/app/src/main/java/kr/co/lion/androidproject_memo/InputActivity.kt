package kr.co.lion.androidproject_memo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.inputmethod.InputMethodManager
import com.google.android.material.textfield.TextInputEditText
import kr.co.lion.androidproject_memo.databinding.ActivityInputBinding
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
                            setResult(RESULT_OK)
                            finish()
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
                //
                true
            }
        }
    }

    // 입력 완료 처리 메서드

    // 키보드 올리는 메서드
    fun showSoftInput(focusView: TextInputEditText) {
        thread {
            SystemClock.sleep(200)
            val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(focusView, 0)
        }
    }
}