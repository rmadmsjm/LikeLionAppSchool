package kr.co.lion.ex16_sqlitedatabase3_2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kr.co.lion.ex16_sqlitedatabase3_2.databinding.FragmentMainSheetBinding

class MainSheetFragment : BottomSheetDialogFragment() {

    lateinit var fragmentMainSheetBinding: FragmentMainSheetBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentMainSheetBinding = FragmentMainSheetBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingView()

        return fragmentMainSheetBinding.root
    }

    // View 설정
    fun settingView() {
        // memoIdx 추출
        val memoIdx = arguments?.getInt("memoIdx")!!

        // 데이터베이스에서 메모 데이터 가져오기
        val memoModel = MemoDao.selectOneMemo(mainActivity, memoIdx)

        fragmentMainSheetBinding.apply {
            // 데이터 출력
            textViewSheetSubject.text = memoModel.memoSubject
            textViewSheetText.text = memoModel.memoText

            // 삭제 버튼
            buttonSheetDelete.setOnClickListener {
                // memoIdx 메모 데이터 삭제
                MemoDao.deleteMemo(mainActivity, memoIdx)
                // RecyclerView 갱신
                mainActivity.reloadRecyclerView()
                // modal 내리기
                dismiss()
            }
        }
    }
}