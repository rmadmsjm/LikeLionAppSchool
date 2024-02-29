package kr.co.lion.androidproject3memoapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import kr.co.lion.androidproject3memoapp.databinding.FragmentMemoAddBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MemoAddFragment : Fragment() {

    lateinit var fragmentMemoAddBinding: FragmentMemoAddBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentMemoAddBinding = FragmentMemoAddBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingToolbar()
        settingTextField()

        return fragmentMemoAddBinding.root
    }

    // 툴바 설정
    fun settingToolbar() {
        fragmentMemoAddBinding.apply {
            toolbarMemoAdd.apply {
                // 타이틀
                title = "메모 추가"

                // back
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    // BackStack에서 제거해 이전 화면이 보이게 함
                    mainActivity.removeFragment(FragmentName.MEMO_ADD_FRAGMENT)
                }

                // 메뉴
                inflateMenu(R.menu.memo_add_menu)
                // 메뉴 항목 리스너
                setOnMenuItemClickListener {
                    // 메뉴 항목 id로 분기
                    when(it.itemId) {
                        // 완료 메뉴
                        R.id.menuItemMemoAddDone -> {
                            // 유효성 검사 메서드 호출
                            val chk = checkTextFieldInput()

                            if (chk == true) {
                                // 데이터 저장
                                saveMemoData()

                                // 키보드 내리기
                                mainActivity.hideSoftInput()

                                // 데이터 memoIdx 가져오기
                                val maxMemoIdx = MemoDao.selectMaxMemoIdx(mainActivity)

                                // Bundle에 담기
                                val memoReadBundle = Bundle()
                                memoReadBundle.putInt("memoIdx", maxMemoIdx)

                                // 모두 제대로 입력을 했다면 MemoReadFragment로 이동
                                mainActivity.replaceFragment(FragmentName.MEMO_READ_FRAGMENT, true, true, memoReadBundle)
                            }
                        }
                        // 초기화 메뉴
                        R.id.menuItemMemoAddReset -> {
                            // 입력 요소 모두 초기화
                            textFieldMemoAddSubject.setText("")
                            textFieldMemoAddText.setText("")
                            textInputLayoutMemoAddSubject.error = null
                            textInputLayoutMemoAddText.error = null
                            // textInputLayoutMemoAddSubject.isErrorEnabled = false
                            // textInputLayoutMemoAddText.isErrorEnabled = false

                            // 첫 번째 입력 요소에 포커스
                            mainActivity.showSoftInput(textFieldMemoAddSubject)
                        }
                    }
                    true
                }
            }
        }
    }

    // 입력 요소 설정
    fun settingTextField() {
        fragmentMemoAddBinding.apply {
            // 첫 번째 입력 요소에 포커스 주기
            mainActivity.showSoftInput(textFieldMemoAddSubject)

            // 에러 메시지가 표시될 공간을 미리 확보
//            textInputLayoutMemoAddSubject.error = "공간 확보"
//            textInputLayoutMemoAddText.error = "공간 확보"
//            textInputLayoutMemoAddSubject.error = null
//            textInputLayoutMemoAddText.error = null

            // 에러 메시지가 보여지는 상황에서 입력했을 때 에머 메세지 없애기
            textFieldMemoAddSubject.addTextChangedListener {
                textInputLayoutMemoAddSubject.error = null
                // textInputLayoutMemoAddSubject.isErrorEnabled = false
            }
            textFieldMemoAddText.addTextChangedListener {
                textInputLayoutMemoAddText.error = null
                // textInputLayoutMemoAddText.isErrorEnabled = false
            }
        }
    }

    // 유효성 검사
    // 반환값 = true : 모두 정상적으로 잘 입력된 것
    // 반환값 = false : 입력에 문제가 있는 것
    fun checkTextFieldInput() : Boolean {
        fragmentMemoAddBinding.apply {
            // 입력하지 않은 입력 요소 중 가장 위에 있는 View를 담을 변수
            var errorView: View? = null

            // 제목
            if(textFieldMemoAddSubject.text.toString().trim().isEmpty()) {
                textInputLayoutMemoAddSubject.error = "제목을 입력해주세요"

                if(errorView == null) {
                    errorView = textFieldMemoAddSubject
                }
            } else {
                textInputLayoutMemoAddSubject.error = null
            }

            // 내용
            if(textFieldMemoAddText.text.toString().trim().isEmpty()) {
                textInputLayoutMemoAddText.error = "내용을 입력해주세요"

                if(errorView == null) {
                    errorView = textFieldMemoAddSubject
                }
            } else {
                textInputLayoutMemoAddText.error = null
            }

            // 비어 있는 입력 요소가 있을 경우
            if(errorView != null) {
                // 비어 있는 입력 요소에 포커스
                mainActivity.showSoftInput(errorView)
                // 메서드 수행 중지
                return false
            }

            return true
        }
    }

    // 입력한 데이터 저장
    fun saveMemoData() {
        fragmentMemoAddBinding.apply {
            // 입력한 문자열 데이터 추출
            val memoSubject = textFieldMemoAddSubject.text.toString().trim()
            val memoText = textFieldMemoAddText.text.toString().trim()

            // 날짜 데이터
            val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
            val memoDate = simpleDateFormat.format(Date())

            // 테스트용 날짜
            // val testMemoDate = "2024.02.27"

            //Log.d("test1234", "$memoSubject , $memoText , $memoDate")

            // 객체에 담기
            val memoModel = MemoModel(0, memoSubject, memoDate, memoText)

            // 저장
            MemoDao.insertMemoData(mainActivity, memoModel)
        }
    }
}