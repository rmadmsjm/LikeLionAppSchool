package kr.co.lion.android10_logcat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

// 로그캣 : 안드로이드 애플리케이션 개발 시 콘솔 출력용으로 사용하는 도구
// 안드로이드 스튜디오에 있는 LogCat 창을 통해 확인할 수 있음

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 총 6가지
        // 어떤 걸 써도 주어진 메서지를 출력하는 것은 동일함
        // 메서드에 따라서 출력되는 색상이 달라짐
        // 용도에 맞게 사용하는 것을 권장

        // 첫 번째 매개 변수 : 필터 문자열
        // 두 번째 매개 변수 : 출력하고 싶은 문자열

        // d : debug, 주로 개발자가 출력하고 싶은 메세지를 출력하는데 사용
        // 다른 메서드에 해당하지 않는 것들을 출력할 때 사용함
        Log.d("test1234", "debug")

        // e : error, 오류에 관련된 메세지를 출력하는데 사용함
        // 매플리케이션에서 오류가 발생하면 오류 메세지는 그냥 출력함
        Log.e("test1234", "error")

        // i : information, 정보성 메세지를 출력하는데 사용함
        Log.i("test1234", "information")

        // v : verbose, 상세한 설명 메세지를 출력하는데 사용함
        Log.v("test1234", "verbose")

        // w : warning, 경고성 메세지를 출력하는데 사용함
        Log.w("test1234", "warning")

        // wtf : What a Terrible Failure (What The Fuck)
        // 절대로 일어나서는 안 되는 일이 벌어졌을 때 메세지를 출력하는데 사용
        Log.wtf("test1234", "what the fuck")
    }
}