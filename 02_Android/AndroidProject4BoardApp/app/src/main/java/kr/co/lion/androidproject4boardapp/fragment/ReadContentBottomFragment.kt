package kr.co.lion.androidproject4boardapp.fragment

import android.app.Dialog
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.androidproject4boardapp.ContentActivity
import kr.co.lion.androidproject4boardapp.R
import kr.co.lion.androidproject4boardapp.databinding.FragmentReadContentBottomBinding
import kr.co.lion.androidproject4boardapp.databinding.RowReadContentReplayBinding

class ReadContentBottomFragment : BottomSheetDialogFragment() {

    lateinit var fragmentReadContentBottomBinding: FragmentReadContentBottomBinding
    lateinit var contentActivity: ContentActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentReadContentBottomBinding = FragmentReadContentBottomBinding.inflate(inflater)
        contentActivity = activity as ContentActivity

        settingRecyclerViewReadContentBottom()

        return fragmentReadContentBottomBinding.root
    }

    // RecyclerView 설정
    fun settingRecyclerViewReadContentBottom() {
        fragmentReadContentBottomBinding.apply {
            recyclerViewAddContentReply.apply {
                // 어뎁터
                adapter = BottomRecyclerViewAdapter()
                // 레이아웃 매니저
                layoutManager = LinearLayoutManager(contentActivity)
                // 데코
                val deco = MaterialDividerItemDecoration(contentActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    // 댓글 RecyclerView Adapter
    inner class BottomRecyclerViewAdapter : RecyclerView.Adapter<BottomRecyclerViewAdapter.BottomViewHolder>() {
        inner class BottomViewHolder(rowReadContentReplayBinding: RowReadContentReplayBinding) : RecyclerView.ViewHolder(rowReadContentReplayBinding.root) {
            val rowReadContentReplayBinding: RowReadContentReplayBinding

            init {
                this.rowReadContentReplayBinding = rowReadContentReplayBinding

                this.rowReadContentReplayBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottomViewHolder {
            val rowReadContentReplayBinding = RowReadContentReplayBinding.inflate(layoutInflater)
            val bottomViewHolder = BottomViewHolder(rowReadContentReplayBinding)

            return bottomViewHolder
        }

        override fun getItemCount(): Int {
            return 100
        }

        override fun onBindViewHolder(holder: BottomViewHolder, position: Int) {
            holder.rowReadContentReplayBinding.textViewRowReplyText.text = "댓글 $position"
            holder.rowReadContentReplayBinding.textViewRowReplyNickname.text = "작성자 $position"
            holder.rowReadContentReplayBinding.textViewRowReplyDate.text = "2024.03.07"
        }
    }


    // Dialog가 만들어질 때 자동으로 호출되는 메서드
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // 다이얼로그 받기
        val dialog = super.onCreateDialog(savedInstanceState)

        // 다이얼로그가 보일 때 동작하는 리스너
        dialog.setOnShowListener {
            val bottomSheetDialog = it as BottomSheetDialog
            // 높이 설정
            setBottomSheetHeight(bottomSheetDialog)
        }

        return dialog
    }

    // BottomSheet 높이 설정
    fun setBottomSheetHeight(bottomSheetDialog:BottomSheetDialog){
        // BottomSheet의 기본 View 객체 가져오기
        val bottomSheet = bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)!!
        // BottomSheet 높이 설정할 수 있는 객체를 생성
        val behavior = BottomSheetBehavior.from(bottomSheet)
        // 높이 설정
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = getBottomSheetDialogHeight()
        bottomSheet.layoutParams = layoutParams
        // 펼쳐져 있는 상태
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    // BottomSheet 높이 구하기 (화면 액정의 85% 크기)
    fun getBottomSheetDialogHeight(): Int {
        return (getWindowHeight() * 0.85).toInt()
    }

    // 사용자 단말기 액정 길이를 구해 반환하는 메서드
    fun getWindowHeight(): Int {
        // 화면 크기 정보를 담을 배열 객체
        val displayMatrics = DisplayMetrics()
        // 애적의 가로 세로 길이 정보 담기
        contentActivity.windowManager.defaultDisplay.getMetrics(displayMatrics)
        // 세로 길이 반환
        return displayMatrics.heightPixels
    }
}