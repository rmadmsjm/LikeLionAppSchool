package kr.co.lion.android46_orientation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kr.co.lion.android46_orientation.databinding.ActivityMainBinding

/*
화면 회전 이벤트가 발생했을 때
1. 화면 회전 처리가 되기 전에 onSaveInstanceState() 메서드가 호출됨
   OS가 이 메서드를 호출할 때 Bundle 객체를 전달함
   화면 복원에 필요한 데이터를 Bundle 객체에 담음
2. 화면 회전 발생
3. onCreate() 가 호출됨
   이 때, 1에서 데이터를 담은 Bundle 객체가 매개변수로 들어옴
   Bundle 객체에 담긴 데이터를 추출하여 View에 넣어서 복원 작업을 수행함
 */

/*
가로 모드 대응 시 주의할 점
1. 세로 모드에서 사용하는 xml과 가로모드에서 사용하는 xml 파일의 이름이 같아야 함
2. 세로 모드의 UI 요소들과 가로 모드의 UI 요소들의 아이디가 모두 일치해야 함
 */

/*
만약 세로 모드만 지원한다면
land 폴더에 layout 파일을 만들지 않는 것으로 끝나면 안 됨
land 폴더에 layout 파일을 만들지 않았다는 것은 단말기 회전에 발생함에 대응을 하지 않는 것에 해당
AndroidManifest.xml에서 Activity에 screenOrientation 속성에 방향을 지정해야 함
세로 portrait, 가로 landscape
 */

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // onCreate()는 화면 회전이 발생했을 때도 호출됨
    // Activity가 처음 생성될 때는 매개변수에 null 값이 들어오고
    // 화면 회전이 발생했을 때는 Bundle 타입의 객체가 들어옴

    // 화면 회전이 발생하게 되면 이 메서드가 호출됨
    // 이때 매개변수로 들어오는 Bundle 객체에 데이터를 담아 놓으면
    // 회전된 화면이 만들어졌을 때 필요한 데이터를 추출해 사용 가능
    // 즉, 회전이 발생하면 일부 UI 요소는 초기화 되는데 이를 복구하기 위한 데이터를 담아서 사용하면 됨
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        if(savedInstanceState == null){
            Log.d("test1234", "Activity 실행")
        } else {
            Log.d("test1234", "화면 회전 발생")
        }

        activityMainBinding.apply {
            if (savedInstanceState != null) {
                // 화면 회전이 발생했다면 초기화된 View 복원하기
                textView.text = savedInstanceState?.getString("data1")
            }

            button.setOnClickListener {
                textView.text = "안녕하세요"
            }

            button2.setOnClickListener {
                textView.text = editTextText.text
            }
        }
    }

    // 화면 회전 이벤트가 발생했을 때 회전되기 전에 호출되는 메서드
    // 이 메서드가 호출된 후 화면 회전이 발생하고 onCreate가 그 다음에 호출됨
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // 화면을 복원하기 위한 데이터를 Bundle 객체에 담기
        // 여기서 데이터를 담은 Bundle 객체는 화면 회전이 발생한 후 onCreate가 호출될 때 전달됨
        outState.putString("data1", activityMainBinding.textView.text.toString())
    }
}