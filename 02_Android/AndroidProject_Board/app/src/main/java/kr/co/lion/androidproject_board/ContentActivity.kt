package kr.co.lion.androidproject_board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.transition.MaterialSharedAxis
import kr.co.lion.androidproject_board.databinding.ActivityContentBinding
import kr.co.lion.androidproject_board.databinding.HeaderContentDrawerBinding
import kr.co.lion.androidproject_board.fragment.AddContentFragment
import kr.co.lion.androidproject_board.fragment.MainFragment
import kr.co.lion.androidproject_board.fragment.ModifyContentFragment
import kr.co.lion.androidproject_board.fragment.ReadContentFragment

class ContentActivity : AppCompatActivity() {

    lateinit var activityContentBinding: ActivityContentBinding

    var oldFragment: Fragment? = null
    var newFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityContentBinding = ActivityContentBinding.inflate(layoutInflater)
        setContentView(activityContentBinding.root)

        settingNavigationView()

        // 첫 화면
        replaceFragment(ContentFragmentName.MAIN_FRAGMENT, false, false, null)
    }

    // 네비게이션 뷰 설정
    fun settingNavigationView() {
        activityContentBinding.apply {
            nacigationViewContent.apply {
                // 헤더로 보여줄 View 생성
                val headerContentDrawerBinding = HeaderContentDrawerBinding.inflate(layoutInflater)
                // 헤더로 보여줄 View 설정
                addHeaderView(headerContentDrawerBinding.root)

                // 사용자 닉네임 설정
                headerContentDrawerBinding.textViewHeaderContentDrawerNickname.text = "삼사오"

                // 메뉴를 눌렀을 때 동작하는 리스너
                setNavigationItemSelectedListener {
                    // 딜레이
                    SystemClock.sleep(200)

                    // 메뉴 id로 분기
                    when(it.itemId) {
                        // 전체 게시판
                        R.id.menuItemContentDrawerAll -> {
                            // NavigationView 닫기
                            drawerLayoutContent.close()
                        }
                        // 자유 게시판
                        R.id.menuItemContentDrawerFree -> {
                            // NavigationView 닫기
                            drawerLayoutContent.close()
                        }
                        // 유머 게시판
                        R.id.menuItemContentDrawerFun -> {
                            // NavigationView 닫기
                            drawerLayoutContent.close()
                        }
                        // 시사 게시판
                        R.id.menuItemContentDrawerCurrentAffairs -> {
                            // NavigationView 닫기
                            drawerLayoutContent.close()
                        }
                        // 스포츠 게시판
                        R.id.menuItemContentDrawerSports -> {
                            // NavigationView 닫기
                            drawerLayoutContent.close()
                        }
                        // 사용자 정보 수정
                        R.id.menuItemContentDrawerModifyUserInfo -> {
                        }
                        // 로그아웃
                        R.id.menuItemContentDrawerLogout -> {
                        }
                        // 회원탈퇴
                        R.id.menuItemContentDrawerSignOut -> {
                        }
                    }
                    true
                }
            }
        }
    }

    // 지정한 Fragment를 보여주는 메서드
    // name : Fragment 이름
    // addToBackStack : BackStack에 포함시킬 것인지
    // isAnimate : 애니메이션 보여줄 것인지
    // data : 새로운 Fragment에 전달할 값이 담겨져 있는 Bundle 객체
    fun replaceFragment(name:ContentFragmentName, addToBackStack:Boolean, isAnimate:Boolean, data:Bundle?){

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
            ContentFragmentName.MAIN_FRAGMENT -> {
                newFragment = MainFragment()
            }
            ContentFragmentName.ADD_CONTENT_FRAGMENT -> {
                newFragment = AddContentFragment()
            }
            ContentFragmentName.READ_CONTENT_FRAGMENT -> {
                newFragment = ReadContentFragment()
            }
            ContentFragmentName.MODIFY_CONTENT_FRAGMENT -> {
                newFragment = ModifyContentFragment()
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
            fragmentTransaction.replace(R.id.containerContent, newFragment!!)

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
    fun removeFragment(name:ContentFragmentName){
        SystemClock.sleep(200)

        // 지정한 이름의 Fragment를 BackStack에서 제거
        supportFragmentManager.popBackStack(name.str, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}