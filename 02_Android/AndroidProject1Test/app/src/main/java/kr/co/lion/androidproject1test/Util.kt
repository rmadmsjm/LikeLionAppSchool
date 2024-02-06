package kr.co.lion.androidproject1test

import android.content.Context
import android.os.SystemClock
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import kotlin.concurrent.thread

class Util {

    companion object {
        // 동물 객체를 담을 리스트
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

        // 키보드 내리는 메서드
        fun hideSoftInput(activity: AppCompatActivity) {
            // 현재 포커스를 가지고 있는 View가 있다면 키보드 내리기
            if(activity.window.currentFocus != null) {
                val inputMethodManager = activity.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(activity.window.currentFocus?.windowToken, 0)
            }
        }
    }
}

// 동물 종류
enum class AnimalType(num:Int, str: String) {
    ANIMAL_TYPE_LION(0, "사자"),
    ANIMAL_TYPE_TIGER(1, "호랑이"),
    ANIMAL_TYPE_GIRAFFE(2, "기린")
}

// 사자 성별
enum class LionGender(num: Int, str:String) {
    LION_GENDER1(0, "암컷"),
    LION_GENDER2(1, "수컷")
}