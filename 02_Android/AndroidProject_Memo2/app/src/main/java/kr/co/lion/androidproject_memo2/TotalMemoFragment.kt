package kr.co.lion.androidproject_memo2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.androidproject_memo2.databinding.FragmentTotalMemoBinding
import kr.co.lion.androidproject_memo2.databinding.RowTotalmemoBinding

class TotalMemoFragment : Fragment() {

    lateinit var fragmentTotalMemoBinding: FragmentTotalMemoBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentTotalMemoBinding = FragmentTotalMemoBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingView()

        return fragmentTotalMemoBinding.root
    }


    // View 설정
    fun settingView() {
        fragmentTotalMemoBinding.apply {
            recyclerViewTotalMemo.apply {
                adapter = RecyclerViewTotalMemoAdapter()
                layoutManager = LinearLayoutManager(mainActivity)
            }
        }
    }

    // RecyclerView 어뎁터
    inner class RecyclerViewTotalMemoAdapter : RecyclerView.Adapter<RecyclerViewTotalMemoAdapter.ViewHolderTotalMemo>() {
        inner class ViewHolderTotalMemo(rowTotalmemoBinding: RowTotalmemoBinding) : RecyclerView.ViewHolder(rowTotalmemoBinding.root) {
            val rowTotalmemoBinding : RowTotalmemoBinding

            init {
                this.rowTotalmemoBinding = rowTotalmemoBinding

                this.rowTotalmemoBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderTotalMemo {
            val rowTotalmemoBinding = RowTotalmemoBinding.inflate(layoutInflater)
            val viewHolderTotalMemo = ViewHolderTotalMemo(rowTotalmemoBinding)

            return viewHolderTotalMemo
        }

        override fun getItemCount(): Int {
            return 10
        }

        override fun onBindViewHolder(holder: ViewHolderTotalMemo, position: Int) {
            holder.rowTotalmemoBinding.textViewRowTotalMemoTitle.text = "제목 $position"
            holder.rowTotalmemoBinding.textViewRowTotalMemoDate.text = "날짜 $position"
        }
    }
}