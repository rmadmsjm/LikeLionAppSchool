package kr.co.lion.ex13_fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.transition.MaterialSharedAxis
import kr.co.lion.ex13_fragment.databinding.ActivityMainBinding

/*
📌 Fragment 기반의 어플리케이션 작업 시 초기 작성 코드 순서
 1. activity_main.xml 파일에 FragmentContainerView 배치
 2. MainActivity.kt 파일에 Fragment의 이름을 표현하기 위한 enum class 작성
 3. MainActivity.kt 파일에 oldFragment와 newFragment 프로퍼티 정의
 4. fragment_basic_code.txt 파일에 있는 코드 붙여 넣기
 5. import 설정
 */

/*
📌 새로운 Fragment 추가
1. Fragment 생성
2. enum class FragmentName에 Fragment 이름 추가
3. replaceFragment 메서드 내의 when에 Fragment 관련 코드 작성
 */

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    var oldFragment: Fragment? = null
    var newFragment: Fragment? = null

    // 학생 정보 리스트
    val studentInfoList = mutableListOf<StudentInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        // 첫 프래그먼트를 보여주기
        replaceFragment(FragmentName.MAIN_FRAGMENT, false, false, null)
    }

    // 지정한 Fragment를 보여주는 메서드
    // name : Fragment 이름
    // addToBackStack : BackStack에 포함시킬 것인지
    // isAnimate : 애니메이션 보여줄 것인지
    // data : 새로운 Fragment에 전달할 값이 담겨져 있는 Bundle 객체
    fun replaceFragment(name:FragmentName, addToBackStack:Boolean, isAnimate:Boolean, data:Bundle?){

        SystemClock.sleep(200)

        // Fragment를 교체할 수 있는 객체 추출
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        // oldFragment에 newFragment가 가지고 있는 Fragment 객체 담기
        if(newFragment != null){
            oldFragment = newFragment
        }

        // 이름으로 분기
        // Fragment의 객체를 생성해 변수에 담기
        when(name){
            // 첫 화면
            FragmentName.MAIN_FRAGMENT -> {
                newFragment = MainFragment()
            }
            FragmentName.INPUT_FRAGMENT -> {
                newFragment = InputFragment()
            }
            FragmentName.SHOW_FRAGMENT -> {
                newFragment = ShowFragment()
            }
        }

        // 새로운 Fragment에 전달할 객체가 있다면 arguments 프로퍼티에 넣어줌
        if(data != null){
            newFragment?.arguments = data
        }

        if(newFragment != null){

            // 애니메이션 설정
            if(isAnimate == true){
                // oldFragment -> newFragment
                // oldFragment : exitTransition
                // newFragment : enterTransition

                // newFragment -> oldFragment
                // oldFragment : reenterTransition
                // newFragment : returnTransition

                // MaterialSharedAxis : 좌우, 상하, 공중 바닥 사이로 이동하는 애니메이션 효과
                // 첫 번째 매개변수
                // 1) X : 좌우
                // 2) Y : 상하
                // 3) Z : 공중 ~ 바닥
                // 두 번째 매개변수 : 새로운 화면이 나타나는 것인지에 대한 여부 설정
                // 1) true : 새로운 화면이 나타나는 애니메이션 동작
                // 2) false : 이전으로 되돌아가는 애니메이션 동작

                if(oldFragment != null){
                    // oldFragment -> newFragment일 때, oldFragment의 애니메이션
                    oldFragment?.exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
                    // newFragment -> oldFragment일 때, oldFragment의 애니메이션
                    oldFragment?.reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)

                    oldFragment?.enterTransition = null
                    oldFragment?.returnTransition = null
                }

                // oldFragment -> newFragment일 때, newFragment의 애니메이션
                newFragment?.enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
                // newFragment -> oldFragment일 때, newFragment의 애니메이션
                newFragment?.returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)

                newFragment?.exitTransition = null
                newFragment?.reenterTransition = null
            }

            // Fragment를 교체 (이전 Fragment가 없으면 새롭게 추가하는 역할 수행)
            // 첫 번째 매개 변수 : Fragment를 배치할 FragmentContainerView의 ID
            // 두 번째 매개 변수 : 보여 주고자 하는 Fragment 객체를
            fragmentTransaction.replace(R.id.mainContainer, newFragment!!)

            // addToBackStack 변수의 값이 true면 새롭게 보여질 Fragment를 BackStack에 포함시키기
            if(addToBackStack == true){
                // BackStack에 포함시킬 때 이름을 지정하면 원하는 Fragment를 BackStack에서 제거 가능
                fragmentTransaction.addToBackStack(name.str)
            }
            // Fragment 교체를 확정
            fragmentTransaction.commit()
        }
    }

    // BackStack에서 Fragment 제거
    fun removeFragment(name:FragmentName){
        SystemClock.sleep(200)

        // 지정한 이름의 Fragment를 BackStack에서 제거
        supportFragmentManager.popBackStack(name.str, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}

enum class FragmentName(var str: String) {
    MAIN_FRAGMENT("MainFragment"),
    INPUT_FRAGMENT("InputFragment"),
    SHOW_FRAGMENT("ShowFragment")
}

// 학생 정보 담을 클래스
data class StudentInfo(var name:String, var age:Int, var kor:Int, var eng: Int, var math:Int)