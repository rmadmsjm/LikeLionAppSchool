package kr.co.lion.androidproject_board.viewmodel

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textfield.TextInputLayout
import kr.co.lion.androidproject_board.Gender
import kr.co.lion.androidproject_board.R

class AddUserInfoViewModel : ViewModel() {
    // 닉네임
    val textFieldAddUserInfoNickname = MutableLiveData<String>()
    // 나이
    val textFieldAddUserInfoAge = MutableLiveData<String>()
    // 성별
    val buttonToggleGroupAddUserInfoGender = MutableLiveData<Int>()
    // 전체 체크박스
    val checkBoxAddUserInfoHobbyTotal = MutableLiveData<Boolean>()
    val checkBoxAddUserInfoHobbyTotalState = MutableLiveData<Int>()
    // 취미 체크박스
    val checkBoxAddUserInfoHobbyExercise = MutableLiveData<Boolean>()
    val checkBoxAddUserInfoHobbyReading = MutableLiveData<Boolean>()
    val checkBoxAddUserInfoHobbyMovie = MutableLiveData<Boolean>()
    val checkBoxAddUserInfoHobbyCooking = MutableLiveData<Boolean>()
    val checkBoxAddUserInfoHobbyMusic = MutableLiveData<Boolean>()
    val checkBoxAddUserInfoHobbyEtc = MutableLiveData<Boolean>()

    // 성별 세팅 메서드
    fun settingGender(gender: Gender) {
        when(gender) {
            Gender.MALE -> {
                buttonToggleGroupAddUserInfoGender.value = R.id.buttonAddUserInfoGenderMale
            }
            Gender.FEMALE -> {
                buttonToggleGroupAddUserInfoGender.value = R.id.buttonAddUserInfoGenderFemale
            }
        }
    }

    val checkBoxList = listOf(
        checkBoxAddUserInfoHobbyExercise,
        checkBoxAddUserInfoHobbyReading,
        checkBoxAddUserInfoHobbyMovie,
        checkBoxAddUserInfoHobbyCooking,
        checkBoxAddUserInfoHobbyMusic,
        checkBoxAddUserInfoHobbyEtc
    )

    // 취미 전체 체크 박스
    fun onCheckBoxAllChange() {
        if(checkBoxAddUserInfoHobbyTotal.value == true) {
            checkBoxList.forEach {
                it.value = true
            }
        } else {
            checkBoxList.forEach {
                it.value = false
            }
        }
    }

    // 취미 체크 박스
    fun onCheckBoxChange() {
        checkBoxList.forEach {
            val isChecked = checkBoxList.all { it.value == true }
            val isUnChecked = checkBoxList.all { it.value == false }

            if(isChecked) {
                checkBoxAddUserInfoHobbyTotalState.value = MaterialCheckBox.STATE_CHECKED
            } else if(isUnChecked) {
                checkBoxAddUserInfoHobbyTotalState.value = MaterialCheckBox.STATE_UNCHECKED
            } else {
                checkBoxAddUserInfoHobbyTotalState.value = MaterialCheckBox.STATE_INDETERMINATE
            }
        }
    }

    companion object {
        @BindingAdapter("android:checkedButtonId")
        @JvmStatic
        fun setCheckedButtonId(group: MaterialButtonToggleGroup, buttonId: Int) {
            group.check(buttonId)
        }

        @BindingAdapter("checkedButtonChangeListener")
        @JvmStatic
        fun checkedButtonChangeListener(group: MaterialButtonToggleGroup, inverseBindingListener: InverseBindingListener) {
            group.addOnButtonCheckedListener { group, checkedId, isChecked ->
                inverseBindingListener.onChange()
            }
        }

        @InverseBindingAdapter(attribute = "android:checkedButtonId", event = "checkedButtonChangeListener")
        @JvmStatic
        fun getCheckedButtonId(group: MaterialButtonToggleGroup): Int {
            return group.checkedButtonId
        }
    }
}