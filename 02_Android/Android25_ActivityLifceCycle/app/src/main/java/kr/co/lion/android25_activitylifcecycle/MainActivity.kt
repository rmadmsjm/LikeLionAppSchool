package kr.co.lion.android25_activitylifcecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

// Activity가 실행 될 때 : onCreate() -> onStart() -> onResume() -> Activity Running
// 화면 회전이 발생했을 때 : onPause() -> onStop() -> onDestroy() -> Activity Dead ->
//                       onCreate() -> onStart() -> onResume() -> Activity Running
// 화면이 안 보일 때 : onPause() -> onStop() -> Activity Stop
// 화면이 다시 보일 때 : onRestart() -> onStart() -> onResume() -> Activity Running
// Activity가 종료될 때 : onPause() -> onStop() -> onDestroy() -> Activity Dead

// Activity 실행 시 딱 한 번만 동작해야 하는 코드 : init 블럭이나 생성자에 구현
// Activity 실행 시 혹은 화면 회전 시 동작해야 하는 코드 : onCreate
// Activity 일시 정지 혹은 정지 했을 때 동작해야 하는 코드 : onPause
// Activity 다시 가동되거나 보여질 때 동작해야 하는 코드 : onResume
// Activity 완전히 종료될 때 동작해야 하는 코드 : onDestroy

// 정상적인 코드일 때만 동작/호출되는 메서드
// 오류 발생 시, 예외 처리가 안 되어 있다면 메서드 호출되지 않음

class MainActivity : AppCompatActivity() {

    // 화면을 만들기 위해 호출되는 onCreate()
    // Activity가 처음 실행될 때 호출
    // 화면 전환이 발생했을 때 호출
    // 화면 회전이 되었을 때 호출
    // 눈에 보이는 화면을 만들어 줌
    // 가로, 세로 모드 따로 제공 가능
    // 한 번만 호출되면 되는 것은 init 블럭 or 생성자 만들어서 사용할 것
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("test1234", "onCreate")
    }

    // onStrat()
    // Activity 동작이 시작될 때
    // Stop 상태에서 다시 Start 상태가 될 때
    override fun onStart() {
        super.onStart()
        Log.d("test1234", "onStart")
    }

    // onResume
    // Activity 동작이 다시 시작할 때
    // Pause 상태에서 다시 Running 상태가 되기 전
    override fun onResume() {
        super.onResume()
        Log.d("test1234", "onResume")
    }

    // onRestart
    // Stop 상태에서 Start 상태가 되기 전에 호출됨
    override fun onRestart() {
        super.onRestart()
        Log.d("test1234", "onRestart")

        // 일부러 오류를 내면 -> 다른 메서드 호출 안 됨
        // 따라서 예외 처리를 안 하면 해당 앱을 강제 종료 시킴
        // -> 아무 것도 호출되지 않음
        // 10/0
    }

    // onPause
    // Running 상태에서 일시정지가 될 때
    override fun onPause() {
        super.onPause()
        Log.d("test1234", "onPause")
    }

    // onStop
    // Running 상태에서 정지될 때
    override fun onStop() {
        super.onStop()
        Log.d("test1234", "onStop")
    }

    // onDestroy
    // Activity가 종료될 때
    override fun onDestroy() {
        super.onDestroy()
        Log.d("test1234", "onDestroy")
    }
}