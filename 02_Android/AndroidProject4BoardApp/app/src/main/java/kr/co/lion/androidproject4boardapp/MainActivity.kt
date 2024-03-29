package kr.co.lion.androidproject4boardapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.transition.MaterialSharedAxis
import kr.co.lion.androidproject4boardapp.databinding.ActivityMainBinding
import kr.co.lion.androidproject4boardapp.fragment.AddUserInfoFragment
import kr.co.lion.androidproject4boardapp.fragment.JoinFragment
import kr.co.lion.androidproject4boardapp.fragment.LoginFragment
import android.Manifest
import android.content.Intent
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // Fragment 주소값을 담는 프로퍼티
    var oldFragment: Fragment? = null
    var newFragment: Fragment? = null

    // 확인할 권한 목록
    val permissionList = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.ACCESS_MEDIA_LOCATION
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 스클래시 스크린 나타나게 하기
        // 반드시 setContnetView 전에 호출되어야 함
        installSplashScreen()

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        // 권한 확인
        requestPermissions(permissionList, 0)

        /*
        CoroutineScope(Dispatchers.Main).launch {
            val result = fsTest()
            Log.d("test1234", "result : $result")
        }
         */

        // 자동 로그인 시 저장된 사용자 정보 가져오기
        val shardPreferences = getSharedPreferences("AutoLogin", MODE_PRIVATE)
        val loginUserIdx = shardPreferences.getInt("loginUserIdx", -1)
        val loginUserNickname = shardPreferences.getString("loginUserNickname", null)

        // 자동 로그인 시 저장된 사용자 인덱스 값이 없을 경우 (자동 로그인을 체크하지 않았다면)
        if(loginUserIdx == -1) {
            // 첫 화면을 띄우기
            replaceFragment(MainFragmentName.LOGIN_FRAGMENT, false, false, null)
        }
        // 그렇지 않은 경우
        else {
            // ContentActivity를 실행한다.
            val contentIntent = Intent(this, ContentActivity::class.java)

            // 로그인한 사용자의 정보를 전달
            contentIntent.putExtra("loginUserIdx", loginUserIdx)
            contentIntent.putExtra("loginUserNickName", loginUserNickname)

            startActivity(contentIntent)
            // MainActivity 종료
            finish()
        }


        // 첫 화면
        replaceFragment(MainFragmentName.LOGIN_FRAGMENT, false, false, null)
    }

    // 지정한 Fragment를 보여주는 메서드
    // name : Fragment 이름
    // addToBackStack : BackStack에 포함시킬 것인지
    // isAnimate : 애니메이션 보여줄 것인지
    // data : 새로운 Fragment에 전달할 값이 담겨져 있는 Bundle 객체
    fun replaceFragment(name:MainFragmentName, addToBackStack:Boolean, isAnimate:Boolean, data:Bundle?){

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
            // 로그인 화면
            MainFragmentName.LOGIN_FRAGMENT -> {
                newFragment = LoginFragment()
            }
            // 회원가입 화면 1
            MainFragmentName.JOIN_FRAGMENT -> {
                newFragment = JoinFragment()
            }
            // 회원가입 화면 2
            MainFragmentName.ADD_USER_INFO_FRAGMENT -> {
                newFragment = AddUserInfoFragment()
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
            fragmentTransaction.replace(R.id.containerMain, newFragment!!)

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
    fun removeFragment(name:MainFragmentName){
        SystemClock.sleep(200)

        // 지정한 이름의 Fragment를 BackStack에서 제거
        supportFragmentManager.popBackStack(name.str, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    /*
    suspend fun fsTest():Long{
        var result = 0L

        // 컬렉션에 접근
        val collection = Firebase.firestore.collection("c1")

        // 코루틴 가동
        val job1 = CoroutineScope(Dispatchers.IO).launch {
            // 컬렉션에 접근하여 모든 문서를 가져오기
            // 코루틴 내부에서 await을 사용하면 작업이 완료될 때 까지 대기했다가 작업이 완료되면 값을 반환 받을 수 있음
            val data = collection.get().await()

            // 가져온 문서의 수 만큼 반복
            data.documents?.forEach {
                // 문서로부터 필드값을 가져와 출력
                val f1 = it?.getString("f1")
                val f2 = it?.getLong("f2")

                result += f2!!

                Log.d("test1234", "f1 : $f1")
                Log.d("test1234", "f2 : $f2")
            }
        }

        // 코루틴 수행이 모두 끝날 때 까지 대기하게 함
        // 코루틴 내부의 코드가 다 끝난 다음 return 이 수행될 수 있도록 함
        job1.join()

        return result
    }
     */
}