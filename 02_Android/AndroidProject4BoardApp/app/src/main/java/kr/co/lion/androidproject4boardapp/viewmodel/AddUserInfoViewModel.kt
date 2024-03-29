package kr.co.lion.androidproject4boardapp.viewmodel

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.checkbox.MaterialCheckBox
import kr.co.lion.androidproject4boardapp.Gender
import kr.co.lion.androidproject4boardapp.R

class AddUserInfoViewModel : ViewModel() {
    // 닉네임
    val textFieldAddUserInfoNickname = MutableLiveData<String>()
    // 나이
    val textFieldAddUserInfoAge = MutableLiveData<String>()
    // 성별
    val toggleAddUserInfoGender = MutableLiveData<Int>()
    // 취미
    val checkBoxAddUserInfoHobby1 = MutableLiveData<Boolean>()
    val checkBoxAddUserInfoHobby2 = MutableLiveData<Boolean>()
    val checkBoxAddUserInfoHobby3 = MutableLiveData<Boolean>()
    val checkBoxAddUserInfoHobby4 = MutableLiveData<Boolean>()
    val checkBoxAddUserInfoHobby5 = MutableLiveData<Boolean>()
    val checkBoxAddUserInfoHobby6 = MutableLiveData<Boolean>()
    // 취미 전체
    val checkBoxAddUserInfoAllState = MutableLiveData<Int>()
    val checkBoxAddUserInfoAll = MutableLiveData<Boolean>()

    // 성별 세팅 메서드
    fun settingGender(gender: Gender) {
        // 성별로 분기
        when(gender) {
            Gender.MALE -> {
                toggleAddUserInfoGender.value = R.id.buttonAddUserInfoMale
            }
            Gender.FEMALE -> {
                toggleAddUserInfoGender.value = R.id.buttonAddUserInfoFemale
            }
        }
    }

    // 성별값 반환 메서드
    fun gettingGender(): Gender = when(toggleAddUserInfoGender.value) {
            R.id.buttonAddUserInfoMale -> Gender.MALE
            else -> Gender.FEMALE
    }

    // 체크박스 전체 상태 설정
    fun setCheckAll(checked: Boolean) {
        checkBoxAddUserInfoHobby1.value = checked
        checkBoxAddUserInfoHobby2.value = checked
        checkBoxAddUserInfoHobby3.value = checked
        checkBoxAddUserInfoHobby4.value = checked
        checkBoxAddUserInfoHobby5.value = checked
        checkBoxAddUserInfoHobby6.value = checked
    }

    // 전체 취미 체크박스를 누르면
    fun onCheckBoxAllChanged() {
        // 전체 취미 체크박스의 체크 여부를 모든 체크박스에 설정함
        setCheckAll(checkBoxAddUserInfoAll.value!!)
    }

    // 각 체크박스를 누르면
    fun onCheckBoxChanged() {
        // 체크 되어 있는 체크박스 개수를 담을 변수
        var checkCnt = 0

        // 체크 되어 있다면, 체크된 체크 박스 개수를 1 증가시키기
        if(checkBoxAddUserInfoHobby1.value == true) {
            checkCnt++
        }
        if(checkBoxAddUserInfoHobby2.value == true) {
            checkCnt++
        }
        if(checkBoxAddUserInfoHobby3.value == true) {
            checkCnt++
        }
        if(checkBoxAddUserInfoHobby4.value == true) {
            checkCnt++
        }
        if(checkBoxAddUserInfoHobby5.value == true) {
            checkCnt++
        }
        if(checkBoxAddUserInfoHobby6.value == true) {
            checkCnt++
        }

        // 체크 되어 있는 것이 없다면
        if(checkCnt == 0) {
            checkBoxAddUserInfoAll.value = false
            checkBoxAddUserInfoAllState.value = MaterialCheckBox.STATE_UNCHECKED
        }
        // 모두 체크 되어 있다면
        else if(checkCnt == 6) {
            checkBoxAddUserInfoAll.value = true
            checkBoxAddUserInfoAllState.value = MaterialCheckBox.STATE_CHECKED
        }
        // 일부만 체크 되어 있다면
        else {
            checkBoxAddUserInfoAllState.value = MaterialCheckBox.STATE_INDETERMINATE
        }
    }

    companion object {
        // 순방향
        // ViewModel에 값을 설정해 화면에 반영하는 작업할 때 호출
        // () 안에는 속성의 이름을 넣음
        // 속성의 이름은 자유롭게 하면 되지만, 기존 속성 이름과 중복되지 않게 해야 함
        // 매개 변수 : 값이 설정된 View 객체, ViewModel을 통해 설정되는 값
        @BindingAdapter("android:checkedButtonId")
        @JvmStatic
        fun setCheckedButtonId(group: MaterialButtonToggleGroup, buttonId: Int) {
            group.check(buttonId)
        }

        // 값을 속성에 넣어주는 것을 순방향이라고 함
        // 속성의 값이 변경되어 MutableLive 데이터로 전달하는 것을 역방향이라고 함
        // 순방향만 구현 : 단방향
        // 순방향과 역방향 모두 구현 : 양방향
        // 화면 요소가 가진 속성에 새로운 값이 설정되면 ViewModel의 변수에 값이 설정될 때 호출
        @BindingAdapter("checkedButtonChangeListener")
        @JvmStatic
        fun checkedButtonChangeListener(group: MaterialButtonToggleGroup, inverseBindingListener: InverseBindingListener) {
            group.addOnButtonCheckedListener { group, checkedId, isChecked ->
                inverseBindingListener.onChange()
            }
        }
        // 역방향 바인딩이 벌어질 때 호출
        @InverseBindingAdapter(attribute = "android:checkedButtonId", event = "checkedButtonChangeListener")
        @JvmStatic
        fun getCheckedButtonId(group: MaterialButtonToggleGroup): Int {
            return group.checkedButtonId
        }
    }
}