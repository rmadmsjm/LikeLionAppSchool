package kr.co.lion.android41_preferencesscreen

import android.os.Bundle
import android.preference.PreferenceScreen
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.PreferenceFragmentCompat

// build.gradle에 androidx.preference:perference 라이브러리 추가
class SettingFragment : PreferenceFragmentCompat() {
    // rootKey : Preference 이름
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        // xml 폴더에 있는 preference xml 파일 지정하기
        setPreferencesFromResource(R.xml.pref, rootKey)
    }
}

/*
 pref.xml 파일 설정

 1️⃣ PreferenceCategory
    Preference 요소를 그룹으로 묶어주는 역할
    특별한 기능은 없고, 화면이 구성될 때 그룹으로 묶어서 보여주는 역할 수행
    1) title : 그룹에 표시할 타이틀 문자열

 2️⃣ EditTextPreference : 문자열 입력
    1) defaultValue : 초기값
    2) title : 화면에 보여지는 이름
    3) key : 데이터를 가져올 때 사용하는 이름
    4) summary : 표시하고자 하는 설명
    5) icon : 좌측에 표시될 아이콘
    6) dialogIcon : 입력을 위해 뜨는 다이얼로그의 아이콘
    7) dialogTitle : 입력을 위해 뜨는 다이얼로그의 타이틀
    8) dialogMessage : 입력을 위해 뜨는 다이얼로그의 메시지

 3️⃣ CheckBoxPreference : 체크박스
    1) defaultValue : 초기값
    2) key : 데이터를 가져올 때 사용하는 이름
    3) summary : 표시하고자 하는 설명
    4) icon : 좌측에 표시될 아이콘
    5) summary : 표시하고자 하는 설명
    6) summaryOff : 체크가 해제되어 있을 때 보여줄 설명 문자열
                    설정되어 있지 않으면 summary 문자열 보여줌
    7) summaryOn : 체크가 되어 있을 때 보여줄 설명 문자열
                   설정되어 있지 않으면 summary 문자열 보여줌

 4️⃣ SwitchPreference : 스위치
    1) defaultValue : 초기값
    2) key : 데이터를 가져올 때 사용하는 이름
    3) summary : 표시하고자 하는 설명
    4) icon : 좌측에 표시될 아이콘
    5) summary : 표시하고자 하는 설명
    6) summaryOff : off 상태일 때 보여줄 설명 문자열
                    설정되어 있지 않으면 summary 문자열 보여줌
    7) summaryOn : on 상태일 때 보여줄 설명 문자열
                   설정되어 있지 않으면 summary 문자열 보여줌

 5️⃣ ListPreference : 항목을 보여주고 그중에 하나 선택
    1) defaultValue : 초기값, 반드시 enrtyValues에 설정되어 있는 문자열 중에서 하나를 설정
    2) title : 화면에 보여지는 이름
    3) key : 데이터를 가져올 때 사용하는 이름
    4) summary : 표시하고자 하는 설명
    5) icon : 좌측에 표시될 아이콘
    6) dialogIcon : 입력을 위해 뜨는 다이얼로그의 아이콘
    7) entries : 화면에 보여줄 항목의 문자열 -> values\strings.xml
    8) enrtyValues : 코드에서 사용될 값

 6️⃣ MultiSelectListPreference : 항목을 보여주고 다수 선택 가능
    1) defaultValue : 초기값, 반드시 enrtyValues에 설정되어 있는 문자열 중에 원하는 것을 설정
    2) title : 화면에 보여지는 이름
    3) key : 데이터를 가져올 때 사용하는 이름
    4) summary : 표시하고자 하는 설명
    5) icon : 좌측에 표시될 아이콘
    6) dialogIcon : 입력을 위해 뜨는 다이얼로그의 아이콘
    7) entries : 화면에 보여줄 항목의 문자열 -> values\strings.xml
    8) enrtyValues : 코드에서 사용될 값
 */
