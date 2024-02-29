package kr.co.lion.androidproject3memoapp

import android.app.Activity
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.google.android.material.transition.MaterialSharedAxis
import kr.co.lion.androidproject3memoapp.databinding.ActivityMainBinding
import kr.co.lion.androidproject3memoapp.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActivity: MainActivity

    // 프래그먼트를 담을 프로퍼티
    var oldFragment: Fragment? = null
    var newFragment: Fragment? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentMainBinding = FragmentMainBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingToolbar()

        // MainActivity에 정의한 mainSubFragmentName 프로퍼티에 담긴 이름으로 Fragment를 보이도록 설정
        changeSubFragment(mainActivity.mainSubFragmentName)

        return fragmentMainBinding.root
    }

    // 툴바 설정
    fun settingToolbar() {
        fragmentMainBinding.apply {
            toolbarMain.apply {
                // 타이틀
                title = "메모"

                // 메뉴
                inflateMenu(R.menu.main_menu)
                // 메뉴 항목 리스너
                setOnMenuItemClickListener {
                    // 메뉴 항목 id로 분기
                    when(it.itemId) {
                        // 일자별 메모 보기 메뉴
                        R.id.menuItemMainCalendar -> {
                            changeSubFragment(MainSubFragmentName.CALENDAR_FRAGMENT)
                        }
                        // 전체 메모 보기 메뉴
                        R.id.menuItemMainShowAll -> {
                            changeSubFragment(MainSubFragmentName.SHOW_ALL_FRAGMENT)
                        }
                        // 메모 추가 메뉴
                        R.id.menuItemMainAdd -> {
                            mainActivity.replaceFragment(FragmentName.MEMO_ADD_FRAGMENT, true, true, null)
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
    fun replaceFragment(name:MainSubFragmentName, addToBackStack:Boolean, isAnimate:Boolean, data:Bundle?){

        SystemClock.sleep(200)

        // Fragment를 교체할 수 있는 객체 추출
        // supportFragmentManager : Activity가 가지는 프로퍼티
        val fragmentTransaction = mainActivity.supportFragmentManager.beginTransaction()

        // oldFragment에 newFragment가 가지고 있는 Fragment 객체 담기
        if(newFragment != null){
            oldFragment = newFragment
        }

        // 이름으로 분기
        // Fragment의 객체를 생성해 변수에 담기
        when(name){
            // 일자별 메모 보기 화면
            MainSubFragmentName.CALENDAR_FRAGMENT -> {
                newFragment = CalendarFragment()
            }
            // 전체 메모 보기 화면
            MainSubFragmentName.SHOW_ALL_FRAGMENT -> {
                newFragment = ShowAllFragment()
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
            fragmentTransaction.replace(R.id.containerMain2, newFragment!!)

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
    fun removeFragment(name:MainSubFragmentName){
        SystemClock.sleep(200)

        // 지정한 이름의 Fragment를 BackStack에서 제거
        mainActivity.supportFragmentManager.popBackStack(name.str, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    // 하위 Fragment를 교체하는 메서드
    fun changeSubFragment(name: MainSubFragmentName) {
        // Fragment 이름으로 분기
        when(name) {
            // 일자별 메모 보기 화면
            MainSubFragmentName.CALENDAR_FRAGMENT -> {
                fragmentMainBinding.apply {
                    toolbarMain.apply {
                        // 일자별 메모 보기 항목 숨기기
                        menu.findItem(R.id.menuItemMainCalendar).isVisible = false
                        // 전체 메모 보기 항목을 보이게 하기
                        menu.findItem(R.id.menuItemMainShowAll).isVisible = true
                    }
                }
            }
            // 전체 메모 보기 화면
            MainSubFragmentName.SHOW_ALL_FRAGMENT -> {
                fragmentMainBinding.apply {
                    toolbarMain.apply {
                        // 일자별 메모 보기 항목 보이게 하기
                        menu.findItem(R.id.menuItemMainCalendar).isVisible = true
                        // 전체 메모 보기 항목 숨기기
                        menu.findItem(R.id.menuItemMainShowAll).isVisible = false
                    }
                }
            }
        }

        // 현재 보이고 있는 Fragment 이름 설정
        mainActivity.mainSubFragmentName = name

        replaceFragment(name, false, false, null)
    }
}