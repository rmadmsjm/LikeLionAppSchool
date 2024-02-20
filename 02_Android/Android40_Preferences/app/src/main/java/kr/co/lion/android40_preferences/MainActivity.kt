package kr.co.lion.android40_preferences

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android40_preferences.databinding.ActivityMainBinding

// Preferences : 어플리케이션의 환경설정에 대한 값을 저장 또는 간단한 값을 저장하기 위해 사용
// Preferences Screen : 어플리케이션의 환경설정에 사용, 저장 기능 제공

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.apply {
            // 저장하기 Button
            button.setOnClickListener {
                // Preferences 객체 추출
                // 첫 번째 매개변수 : Preferences의 이름, 없으면 생성함
                // 두 번째 매개변수
                // 1) MODE_APPEND : 데이터를 저장할 때 사용한 이름의 데이터가 있어도 추가적으로 저장함
                // 2) MODE_PRIVATE : 데이터를 저장할 떄 사용한 이름의 데이터가 있으면 덮어씌움
                val pref = getSharedPreferences("data", Context.MODE_PRIVATE)

                // 데이터 저장을 위한 객체 추출
                val editor = pref.edit()

                // editor 객체에 값 담기
                editor.putBoolean("data1", true)
                editor.putFloat("data2", 11.11f)
                editor.putInt("data3", 100)
                editor.putLong("data4", 200L)
                editor.putString("data5", "문자열 데이터")

                val set1 = mutableSetOf<String>()
                set1.add("문자열1")
                set1.add("문자열1")
                set1.add("문자열2")
                set1.add("문자열3")
                editor.putStringSet("data6", set1)

                // 담겨진 값 저장 : commit, apply -> apply 권장
                editor.apply()

                textView.text = "저장되었습니다."
            }

            // 읽어오기 Button
            button2.setOnClickListener {
                val pref = getSharedPreferences("data", Context.MODE_PRIVATE)

                // 저장한 데이터 가져오기
                // 두 번째 매개변수에는 기본값 설정 가능
                // 지정된 이르므올 저장된 데이터가 없을 경우, 두 번째 매개변수로 지정한 기본값으로 변환되어 변수에 담김
                val data1 = pref.getBoolean("data1", false)
                val data2 = pref.getFloat("data2", 0.0f)
                val data3 = pref.getInt("data3", 0)
                val data4 = pref.getLong("data4", 0L)
                val data5 = pref.getString("data5", null)
                val data6 = pref.getStringSet("data6", null)

                textView.apply {
                    text = "data1 : ${data1}\n"
                    append("data2 : ${data2}\n")
                    append("data3 : ${data3}\n")
                    append("data4 : ${data4}\n")

                    if(data5 != null) {
                        append("data5 : ${data5}/n")
                    }

                    if(data6 != null) {
                        // 순서대로 값이 나오지 않으므로 반복문 사용해야 함
                        data6.forEach {
                            append("data6 : ${it}\n")
                        }
                    }
                }
            }
        }
    }
}