package kr.co.lion.androidproject1

import android.content.Context
import android.os.SystemClock
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import kotlin.concurrent.thread

class Util {
    companion object {
        val animalList = mutableListOf<Animal>()

        // 키보드 올리는 메서드
        fun showSoftInput(context: Context, view: View) {
            // 포커스 주기
            view.requestFocus()

            thread {
                SystemClock.sleep(1000)
                val inputMethodManager = context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.showSoftInput(view, 0)
            }
        }
    }
}

enum class AnimalType(var num:Int, var str:String) {
    LION(0, "사자"),
    TIGER(1, "호랑이"),
    GIRAFFE(2, "기린")
}

enum class LionGender(var num:Int, var str:String) {
    LIONGENDER_FEMALE(0, "암컷"),
    LIONGENDER_MALE(1, "수컷")
}
