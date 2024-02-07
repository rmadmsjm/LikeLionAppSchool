package kr.co.lion.androidproject1test

import android.content.Context
import android.content.DialogInterface
import android.os.SystemClock
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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

        // 안내를 위한 다이얼로그 보여주기
        fun showInfoDialog(context: Context, title: String, message: String, listener: (DialogInterface, Int) -> Unit) {
            val dialogBuilder = MaterialAlertDialogBuilder(context)
            dialogBuilder.setTitle(title)
            dialogBuilder.setMessage(message)
            dialogBuilder.setPositiveButton("확인", listener)
            dialogBuilder.show()
        }
    }
}

// 동물 종류
enum class AnimalType(var num:Int, var str: String) {
    ANIMAL_TYPE_LION(0, "사자"),
    ANIMAL_TYPE_TIGER(1, "호랑이"),
    ANIMAL_TYPE_GIRAFFE(2, "기린")
}

// 사자 성별
enum class LionGender(var num: Int, var str:String) {
    LION_GENDER1(0, "암컷"),
    LION_GENDER2(1, "수컷")
}