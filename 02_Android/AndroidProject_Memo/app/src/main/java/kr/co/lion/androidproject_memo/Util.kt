package kr.co.lion.androidproject_memo

import android.content.Context
import android.os.SystemClock
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import com.google.android.material.textfield.TextInputEditText
import kotlin.concurrent.thread

class Util {

    companion object {
        // 메모 데이터 List
        var memoDataList = mutableListOf<MemoClass>()

        // 키보드 올리는 메서드
        fun showSoftInput(context: Context, view: View) {
            // 포커스 주기
            view.requestFocus()

            thread {
                SystemClock.sleep(500)
                val inputMethodManager = context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.showSoftInput(view, 0)
            }
        }
    }
}


enum class ResultCode(var codeNum:Int) {
    RESULT_EDIT(2),
    RESULT_DELETE(3)
}