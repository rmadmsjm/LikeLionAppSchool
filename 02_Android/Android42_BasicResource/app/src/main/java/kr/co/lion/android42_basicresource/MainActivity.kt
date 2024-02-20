package kr.co.lion.android42_basicresource

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android42_basicresource.databinding.ActivityMainBinding

/*
res\values 폴더
프로그램에서 사용되는 값을 설정하는 파일이 들어있음
values 폴더의 파일 이름은 중요하지 않음
values 폴더 내에 있는 xml 파일에 등록되어 있는 값의 이름으로 resource id가 만들어짐
 */

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.apply {
            // 기본 문자열 Button
            button.setOnClickListener {
                // res\values\string.xml 에 설정한 문자열 가져오기
                val str1 = resources.getString(R.string.str1)
                textView.text = "${str1}\n"

                // 문자열은 프로그램에서 많이 사용하는 요소이기 때문에 Activity에 등록된 문자열 리소스를 가져오는 메서드를 제공함
                val str2 = getString(R.string.str2)
                textView.append("${str2}\n")

                // 문자열을 설정할 수 있는 View는 문자열 리소스 아이디를 지정할 수 있도록 제공됨
                textView.setText(R.string.str1)
            }

            // 포맷 문자열 Button
            button2.setOnClickListener {
                // res\values\string.xml 에 설정한 문자열 가져오기
                // str3은 %S, %d, %f 등의 출력 양식을 가지고 있는 문자열
                // 각 포맷 문자에 바인딩될 값을 지정해야 함
                val str1 = getString(R.string.str3)
                // 포맷 문자에 바인딩될 값을 지정하여 문자열 완성
                val str2 = String.format(str1, "홍길동", 30)

                textView.text = str2
            }

            // 문자열 배열 Button
            button3.setOnClickListener {
                // res\values\string.xml 에 설정한 문자열 배열 가져오기
                val str4Array = resources.getStringArray(R.array.str4_array)
                textView.text = ""

                str4Array.forEach {
                    textView.append("${it}\n")
                }
            }

            // 색상 Button
            button4.setOnClickListener {
                textView.text = "색상값 테스트"

                // 미리 정의되어 있는 색상값
                textView.setTextColor(Color.RED)

                // RGB 지정 : rgb(R, G, B)
                val c1 = Color.rgb(227, 30, 89)
                textView.setTextColor(c1)

                // ARGB 지정 : argb(alpha, R, G, B)
                val c2 = Color.argb(50, 227, 30, 89)
                textView.setTextColor(c2)

                // res\values\colors.xml 에 설정한 색상 가져오기
                val c3 = getColor(R.color.color1)
                val c4 = getColor(R.color.color2)
                val c5 = getColor(R.color.color3)
                val c6 = getColor(R.color.color4)

                // Android에서 제공하는 색상
                val c7 = getColor(android.R.color.holo_blue_dark)

                textView.setTextColor(c3)
            }

            // 크기 Button
            button5.setOnClickListener {
                // res\values\string.xml 에 설정한 크기 가져오기
                // 단위
                // px : 픽셀의 개수. 실제로 사용 하는 단위
                // dp : 160ppi 액정에서 1dp = 1px
                //      단말기의 ppi 수치에 따른 px 값을 계산해줌
                // sp : 160ppi 액정에서 1sp = 1px
                //      단말기 설정에서 설정한 글자 크기에 반영됨
                //      문자열의 크기를 지정할 때는 sp 단위를 사용함
                // in : 인치
                // mm : mm
                // pt : 1/72 인치. 인쇄물에 사용하는 단위

                val px = resources.getDimension(R.dimen.px)
                val dp = resources.getDimension(R.dimen.dp)
                val sp = resources.getDimension(R.dimen.sp)
                val inch = resources.getDimension(R.dimen.inch)
                val mm = resources.getDimension(R.dimen.mm)
                val pt = resources.getDimension(R.dimen.pt)

                textView.text = "1px = ${px}px\n"
                textView.append("1dp = ${dp}px\n")
                textView.append("1sp = ${sp}px\n")
                textView.append("1inch = ${inch}px\n")
                textView.append("1mm = ${mm}px\n")
                textView.append("1pt = ${pt}px\n")
            }
        }
    }
}