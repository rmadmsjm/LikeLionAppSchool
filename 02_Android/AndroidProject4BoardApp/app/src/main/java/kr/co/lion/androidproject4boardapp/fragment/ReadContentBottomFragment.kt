package kr.co.lion.androidproject4boardapp.fragment

import android.app.Dialog
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.divider.MaterialDividerItemDecoration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.androidproject4boardapp.ContentActivity
import kr.co.lion.androidproject4boardapp.R
import kr.co.lion.androidproject4boardapp.ReplyState
import kr.co.lion.androidproject4boardapp.Tools
import kr.co.lion.androidproject4boardapp.dao.ReplyDao
import kr.co.lion.androidproject4boardapp.dao.UserDao
import kr.co.lion.androidproject4boardapp.databinding.FragmentReadContentBottomBinding
import kr.co.lion.androidproject4boardapp.databinding.RowReadContentReplayBinding
import kr.co.lion.androidproject4boardapp.model.ReplyModel
import kr.co.lion.androidproject4boardapp.model.UserModel
import kr.co.lion.androidproject4boardapp.viewmodel.ReadContentBottomViewModel
import kr.co.lion.androidproject4boardapp.viewmodel.RowReadContentReplyViewModel
import java.text.SimpleDateFormat
import java.util.Date

class ReadContentBottomFragment(var isContentWriter: Boolean, var contentIdx: Int) : BottomSheetDialogFragment() {

    lateinit var fragmentReadContentBottomBinding: FragmentReadContentBottomBinding
    lateinit var contentActivity: ContentActivity
    lateinit var readContentBottomViewModel: ReadContentBottomViewModel

    // 댓글 정보를 가지고 있는 리스트
    var replyList = mutableListOf<ReplyModel>()
    var userList = mutableListOf<UserModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        // fragmentReadContentBottomBinding = FragmentReadContentBottomBinding.inflate(inflater)
        fragmentReadContentBottomBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_read_content_bottom, container, false)
        readContentBottomViewModel = ReadContentBottomViewModel()
        fragmentReadContentBottomBinding.readContentBottomViewModel = readContentBottomViewModel
        fragmentReadContentBottomBinding.lifecycleOwner = this

        contentActivity = activity as ContentActivity

        settingTextField()
        settingRecyclerViewReadContentBottom()
        gettingReplyData()

        // readContentBottomViewModel.textFieldAddContentReply.value = "테스트 댓글 입니다."

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
            // val rowReadContentReplayBinding = RowReadContentReplayBinding.inflate(layoutInflater)
            val rowReadContentReplayBinding = DataBindingUtil.inflate<RowReadContentReplayBinding>(layoutInflater,
                R.layout.row_read_content_replay, parent, false)
            val rowReadContentReplyViewModel = RowReadContentReplyViewModel()
            rowReadContentReplayBinding.rowReadContentReplyViewModel = rowReadContentReplyViewModel
            rowReadContentReplayBinding.lifecycleOwner = this@ReadContentBottomFragment

            val bottomViewHolder = BottomViewHolder(rowReadContentReplayBinding)

            return bottomViewHolder
        }

        override fun getItemCount(): Int {
            return replyList.size
        }

        override fun onBindViewHolder(holder: BottomViewHolder, position: Int) {
            holder.rowReadContentReplayBinding.rowReadContentReplyViewModel?.textViewRowReplyText?.value = replyList[position].replyText

            userList.forEach {
                if(it.userIdx == replyList[position].replyWriterIdx){
                    holder.rowReadContentReplayBinding.rowReadContentReplyViewModel?.textViewRowReplyNickname?.value = it.userNickname
                    return@forEach
                }
            }

            holder.rowReadContentReplayBinding.rowReadContentReplyViewModel?.textViewRowReplyDate?.value = replyList[position].replyWriteDate

            // 로그인한 사람과 댓글 작성한 사람이 같지 않다면 삭제 버튼 보이지 않게 하기
            if(contentActivity.loginUserIdx != replyList[position].replyWriterIdx) {
                holder.rowReadContentReplayBinding.buttonRowReplyDelete.isVisible = false
                // 삭제 버튼 리스너 제거
                holder.rowReadContentReplayBinding.buttonRowReplyDelete.setOnClickListener(null)
            } else {
                holder.rowReadContentReplayBinding.buttonRowReplyDelete.isVisible = true
                // 삭제 버튼 리스너 설정
                holder.rowReadContentReplayBinding.buttonRowReplyDelete.setOnClickListener {
                    CoroutineScope(Dispatchers.Main).launch {
                        // 댓글 삭제
                        ReplyDao.updateReplyState(replyList[position].replyIdx, ReplyState.REPLY_SATE_DELETE)
                        // 데이터를 다시 받아와 RecyclerView 갱신
                        gettingReplyData()
                    }
                }
            }
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

    // 입력 요소 설정
    fun settingTextField() {
        fragmentReadContentBottomBinding.apply {
            // 로그인한 사람이 현재 글의 작성자인 경우
            if(isContentWriter) {
                // 자신의 글에는 댓글을 작성할 수 있다는 문구 보여주기
                textFieldAddContentReply.setText("댓글을 작성할 수 없습니다")
                textFieldAddContentReply.isEnabled = false
            } else {
                textFieldAddContentReply.apply {
                    // 엔터키 이벤트 설정
                    setOnEditorActionListener { v, actionId, event ->
                        CoroutineScope(Dispatchers.Main).launch {
                            // 댓글 번호 가져오기
                            val replySequence = ReplyDao.getReplySequence()
                            // 댓글 번호 업데이트
                            ReplyDao.updateReplySequence(replySequence + 1)
                            // 저장할 데이터 담기
                            val replyIdx = replySequence + 1
                            val replyWriterIdx = contentActivity.loginUserIdx
                            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
                            val replyWriteDate = simpleDateFormat.format(Date())
                            val replayContentIdx = contentIdx
                            val replyText = readContentBottomViewModel?.textFieldAddContentReply?.value!!
                            val replyState = ReplyState.REPLY_STATE_NORMAL.num

                            val replyModel = ReplyModel(replyIdx, replyWriterIdx, replyWriteDate, replayContentIdx,
                                replyText, replyState)

                            // 저장
                            ReplyDao.insertReplyData(replyModel)

                            // 입력 요소 비우기
                            readContentBottomViewModel?.textFieldAddContentReply?.value = ""

                            // RecylerView 갱신
                            gettingReplyData()
                        }

                        // false를 반환하여 키보드 내려가게 하기
                        // true : 키보드 유지 , false : 키보드 내리기
                        false
                    }
                }
            }
        }
    }

    // 서버로부터 데이터를 가져와 RecyclerView 갱신
    fun gettingReplyData(){
        CoroutineScope(Dispatchers.Main).launch {
            // 댓글 정보를 가져온다
            replyList = ReplyDao.gettingReplyList(contentIdx)
            // 사용자 정보를 가져온다.
            userList = UserDao.getUserAll()
            // 리사이클러뷰를 갱신한다.
            fragmentReadContentBottomBinding.recyclerViewAddContentReply.adapter?.notifyDataSetChanged()
        }
    }
}