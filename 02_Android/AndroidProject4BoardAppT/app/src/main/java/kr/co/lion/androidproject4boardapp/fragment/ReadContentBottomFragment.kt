package kr.co.lion.androidproject4boardapp.fragment

import android.app.Dialog
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
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
import kr.co.lion.androidproject4boardapp.databinding.FragmentReadContentBinding
import kr.co.lion.androidproject4boardapp.databinding.FragmentReadContentBottomBinding
import kr.co.lion.androidproject4boardapp.databinding.RowReadContentReplayBinding
import kr.co.lion.androidproject4boardapp.model.ReplyModel
import kr.co.lion.androidproject4boardapp.model.UserModel
import kr.co.lion.androidproject4boardapp.viewmodel.ReadContentBottomViewModel
import kr.co.lion.androidproject4boardapp.viewmodel.RowReadContentReplyViewModel
import java.text.SimpleDateFormat
import java.util.Date


class ReadContentBottomFragment(var isContentWriter:Boolean, var contentIdx:Int) : BottomSheetDialogFragment() {

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
        settingRecyclerViewAddContentReply()
        gettingReplyData()

        // readContentBottomViewModel.textFieldAddContentReply.value = "테스트 댓글 입니다."

        return fragmentReadContentBottomBinding.root
    }

    // RecyclerView 구성 메서드
    fun settingRecyclerViewAddContentReply(){
        fragmentReadContentBottomBinding.apply {
            recyclerViewAddContentReply.apply {
                // 어뎁터
                adapter = BottomRecyclerViewAdapter()
                // 레이아웃 매니저
                layoutManager = LinearLayoutManager(contentActivity)
                // 데코레이션
                val deco = MaterialDividerItemDecoration(contentActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    // 댓글 목록을 보여줄 RecyclerView의 어뎁터
    inner class BottomRecyclerViewAdapter : RecyclerView.Adapter<BottomRecyclerViewAdapter.BottomViewHolder>(){

        inner class BottomViewHolder(rowReadContentReplayBinding: RowReadContentReplayBinding) : RecyclerView.ViewHolder(rowReadContentReplayBinding.root){
            val rowReadContentReplayBinding : RowReadContentReplayBinding

            init {
                this.rowReadContentReplayBinding = rowReadContentReplayBinding

                rowReadContentReplayBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottomViewHolder {
            // val rowReadContentReplayBinding = RowReadContentReplayBinding.inflate(layoutInflater)
            val rowReadContentReplayBinding = DataBindingUtil.inflate<RowReadContentReplayBinding>(layoutInflater, R.layout.row_read_content_replay, parent, false)
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
                    holder.rowReadContentReplayBinding.rowReadContentReplyViewModel?.textViewRowReplyNickName?.value = it.userNickName
                    return@forEach
                }
            }
            holder.rowReadContentReplayBinding.rowReadContentReplyViewModel?.textViewRowReplyDate?.value = replyList[position].replyWriteDate

            // 로그인한 사람과 댓글 작성자가 같은 사람이 아니라면 삭제 버튼을 숨긴다.
            if(contentActivity.loginUserIdx != replyList[position].replyWriterIdx){
                holder.rowReadContentReplayBinding.buttonRowReplyDelete.isVisible = false
                // 삭제 버튼에 대한 리스너를 제거한다.
                holder.rowReadContentReplayBinding.buttonRowReplyDelete.setOnClickListener(null)
            } else {
                holder.rowReadContentReplayBinding.buttonRowReplyDelete.isVisible = true
                // 삭제 버튼에 대한 리스너를 설정해준다
                holder.rowReadContentReplayBinding.buttonRowReplyDelete.setOnClickListener {
                    CoroutineScope(Dispatchers.Main).launch {
                        // 댓글을 삭제한다.
                        ReplyDao.updateReplyState(replyList[position].replyIdx, ReplyState.REPLY_SATE_DELETE)
                        // 데이터를 다시 받아와 리사이클러뷰를 갱신한다.
                        gettingReplyData()
                    }
                }
            }
        }
    }

    // 다이얼로그가 만들어질 때 자동으로 호출되는 메서드
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // 다이얼로그를 받는다.
        val dialog = super.onCreateDialog(savedInstanceState)
        // 다이얼로그가 보일때 동작하는 리스너
        dialog.setOnShowListener {

            val bottomSheetDialog = it as BottomSheetDialog
            // 높이를 설정한다.
            setBottomSheetHeight(bottomSheetDialog)
        }

        return dialog
    }

    // BottomSheet의 높이를 설정해준다.
    fun setBottomSheetHeight(bottomSheetDialog:BottomSheetDialog){
        // BottomSheet의 기본 뷰 객체를 가져온다
        val bottomSheet = bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)!!
        // BottomSheet 높이를 설정할 수 있는 객체를 생성한다.
        val behavior = BottomSheetBehavior.from(bottomSheet)
        // 높이를 설정한다.
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = getBottomSheetDialogHeight()
        bottomSheet.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED

    }

    // BottomSheet의 높이를 구한다(화면 액정의 85% 크기)
    fun getBottomSheetDialogHeight() : Int {
        return (getWindowHeight() * 0.85).toInt()
    }

    // 사용자 단말기 액정의 길이를 구해 반환하는 메서드
    fun getWindowHeight() : Int {
        // 화면 크기 정보를 담을 배열 객체
        val displayMetrics = DisplayMetrics()
        // 액정의 가로 세로 길이 정보를 담아준다.
        contentActivity.windowManager.defaultDisplay.getMetrics(displayMetrics)
        // 세로길이를 반환해준다.
        return displayMetrics.heightPixels
    }

    // 입력 요소 설정
    fun settingTextField(){

        fragmentReadContentBottomBinding.apply {
            // 현재 글의 작성자가 로그인한 사람이라면..
            if(isContentWriter){
                // 자신의 글에는 댓글을 입력할 수 없다는 문구를 넣어준다.
                textFieldAddContentReply.setText("댓글을 달 수 없습니다")
                textFieldAddContentReply.isEnabled = false
            } else {
                textFieldAddContentReply.apply {
                    // 엔터키 이벤트를 설정한다.
                    setOnEditorActionListener { v, actionId, event ->

                        CoroutineScope(Dispatchers.Main).launch {
                            // 댓글 번호를 가져온다.
                            val replySequence = ReplyDao.getReplySequence()
                            // 댓글 번호를 업데이트 한다.
                            ReplyDao.updateReplySequence(replySequence + 1)
                            // 저장할 데이터를 담는다.
                            val replyIdx = replySequence + 1
                            val replyWriterIdx = contentActivity.loginUserIdx
                            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
                            val replyWriteDate = simpleDateFormat.format(Date())
                            val replayContentIdx = contentIdx
                            val replyText = readContentBottomViewModel?.textFieldAddContentReply?.value!!
                            val replyState = ReplyState.REPLY_STATE_NORMAL.num

                            val replyModel = ReplyModel(replyIdx, replyWriterIdx, replyWriteDate, replayContentIdx,
                                replyText, replyState)
                            // 저장한다.
                            ReplyDao.insertReplyData(replyModel)

                            // 입력 요소를 비워준다.
                            readContentBottomViewModel?.textFieldAddContentReply?.value = ""

                            // 리사이클러뷰를 갱신한다.
                            gettingReplyData()
                        }

                        // false를 반환하여 키보드가 내려가게 한다.
                        false
                    }
                }
            }
        }
    }

    // 서버로 부터 데이터를 가져와 리사이클러뷰를 갱신한다.
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









