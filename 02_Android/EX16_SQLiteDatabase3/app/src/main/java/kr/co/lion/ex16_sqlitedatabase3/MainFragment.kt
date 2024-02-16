package kr.co.lion.ex16_sqlitedatabase3

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kr.co.lion.ex16_sqlitedatabase3.databinding.FragmentMainBinding
import kr.co.lion.ex16_sqlitedatabase3.databinding.RowMainBinding

class MainFragment : Fragment() {

    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActivity: MainActivity

    lateinit var memoList: MutableList<MemoModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentMainBinding = FragmentMainBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingData()
        settingView()

        return fragmentMainBinding.root
    }

    // Data 설정
    fun settingData() {
        memoList = MemoDao.selectAllMemo(mainActivity)
    }

    // View 설정
    fun settingView() {
       fragmentMainBinding.apply {
           recyclerViewMainFragment.apply {
               adapter = RecycelrViewAdaper()
               layoutManager = LinearLayoutManager(mainActivity)
           }
       }
    }

    // RecycelrView adapter
    inner class RecycelrViewAdaper : RecyclerView.Adapter<RecycelrViewAdaper.ViewHolderClass>() {
        // ViewHolder
        inner class ViewHolderClass(rowMainBinding: RowMainBinding) : RecyclerView.ViewHolder(rowMainBinding.root) {
            val rowMainBinding : RowMainBinding

            init {
                this.rowMainBinding = rowMainBinding

                this.rowMainBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val rowMainBinding = RowMainBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolderClass(rowMainBinding)

            return viewHolderClass
        }

        override fun getItemCount(): Int {
            return memoList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.rowMainBinding.textView.text = memoList[position].title

            // 항목 리스너
            holder.rowMainBinding.root.setOnClickListener {
                MaterialAlertDialogBuilder(mainActivity).apply {
                    // 타이틀
                    setTitle(memoList[position].title)
                    // 메시지
                    setMessage(memoList[position].content)
                    // positive button
                    setPositiveButton("닫기") { dialogInterface: DialogInterface, i: Int ->
                        dialogInterface.dismiss()
                    }
                    // negative button
                    setNegativeButton("삭제") { dialogInterface: DialogInterface, i: Int ->
                        MemoDao.deleteMemo(mainActivity, memoList[position].idx)
                    }
                }.show()
            }
        }
    }

}