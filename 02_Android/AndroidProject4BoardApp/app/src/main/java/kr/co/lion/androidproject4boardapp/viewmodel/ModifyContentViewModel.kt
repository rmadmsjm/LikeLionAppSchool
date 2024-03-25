package kr.co.lion.androidproject4boardapp.viewmodel

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.button.MaterialButtonToggleGroup
import kr.co.lion.androidproject4boardapp.ContentType
import kr.co.lion.androidproject4boardapp.R

class ModifyContentViewModel : ViewModel() {
    // 제목
    val textFieldModifyContentSubject = MutableLiveData<String>()
    // 내용
    val textFieldModifyContentText = MutableLiveData<String>()
    // 게시판 종류
    val toggleModifyContentType = MutableLiveData<Int>()

    // 게시판 종류를 받아 MutableLiveData에 설정
    fun settingContentType(contentType: Int) {
        toggleModifyContentType.value = when(contentType){
            ContentType.TYPE_ALL.num -> 0
            ContentType.TYPE_FREE.num -> R.id.buttonModifyContentType1
            ContentType.TYPE_HUMOR.num -> R.id.buttonModifyContentType2
            ContentType.TYPE_SOCIETY.num -> R.id.buttonModifyContentType3
            ContentType.TYPE_SPORTS.num -> R.id.buttonModifyContentType4
            else -> 0
        }
    }

    // 게시판 타입 반환하는 메서드
    fun gettingContentType() = when(toggleModifyContentType.value) {
        R.id.buttonModifyContentType1 -> ContentType.TYPE_FREE.num
        R.id.buttonModifyContentType2 -> ContentType.TYPE_HUMOR.num
        R.id.buttonModifyContentType3 -> ContentType.TYPE_SOCIETY.num
        R.id.buttonModifyContentType4 -> ContentType.TYPE_SPORTS.num
        else -> 0
    }

    companion object {
        // toggleAddContentType 에 값을 설정했을 때 checkedButtonId 속성에 값을 반영해 줄 때 호출(순방향)
        @BindingAdapter("android:checkedButtonId")
        @JvmStatic
        fun setCheckedButtonId(group: MaterialButtonToggleGroup, buttonId:Int){
            group.check(buttonId)
        }

        // Android OS가 현재 화면을 구성하고자 할 때 InverseBindingAdapter에 등록되어 있는 event 값을 보고
        // event에 등록되어 있는 이름과 동일한 BindingAdapter를 찾아 메서드를 호출함
        // 리스너 설정
        @BindingAdapter("checkedButtonChangeListener")
        @JvmStatic
        fun checkedButtonChangeListener(group: MaterialButtonToggleGroup, inverseBindingListener: InverseBindingListener){
            group.addOnButtonCheckedListener { group, checkedId, isChecked ->
                inverseBindingListener.onChange()
            }
        }

        // Android OS가 현재 화면을 구성하고자 할 때 속성에 설정된 MutableLiveData가 양방향(@=)으로 되어 있을 경우
        // 참고할 데이터가 셋팅되어 있는 메서드
        // inverseBindingListener.onChange() 호출시 동작할 메서드
        @InverseBindingAdapter(attribute = "android:checkedButtonId", event = "checkedButtonChangeListener")
        @JvmStatic
        fun getCheckedButtonId(group: MaterialButtonToggleGroup): Int {
            return group.checkedButtonId
        }
    }
}