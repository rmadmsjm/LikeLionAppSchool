package kr.co.lion.androidproject3memoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.androidproject3memoapp.databinding.FragmentShowAllBinding
import kr.co.lion.androidproject3memoapp.databinding.RowShowAllBinding

class ShowAllFragment : Fragment() {

    lateinit var fragmentShowAllBinding: FragmentShowAllBinding
    lateinit var mainActivity: MainActivity

    // RecyclerView 구성할 memoList
    var memoList = mutableListOf<MemoModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentShowAllBinding = FragmentShowAllBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingRecyclerShowAll()

        return fragmentShowAllBinding.root
    }

    // RecyclerView 설정
    fun settingRecyclerShowAll() {
        fragmentShowAllBinding.apply {
            recyclerShowAll.apply {
                // 데이터 가져오기
                memoList = MemoDao.selectMemoDataAll(mainActivity)

                // adapter 설정
                adapter = RecyclerShowAllAdapter()
                // 레이아웃 매니저 설정
                layoutManager = LinearLayoutManager(mainActivity)
                // 데코
                val deco = MaterialDividerItemDecoration(mainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    // RecyclerView Adapter
    inner class RecyclerShowAllAdapter : RecyclerView.Adapter<RecyclerShowAllAdapter.RecyclerShowAllViewHolder>() {
        inner class RecyclerShowAllViewHolder(rowShowAllBinding: RowShowAllBinding) : RecyclerView.ViewHolder(rowShowAllBinding.root) {
            val rowShowAllBinding: RowShowAllBinding

            init {
                this.rowShowAllBinding = rowShowAllBinding

                this.rowShowAllBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerShowAllViewHolder {
            val rowShowAllBinding = RowShowAllBinding.inflate(layoutInflater)
            val recyclerShowAllViewHolder = RecyclerShowAllViewHolder(rowShowAllBinding)

            return recyclerShowAllViewHolder
        }

        override fun getItemCount(): Int {
            return memoList.size
        }

        override fun onBindViewHolder(holder: RecyclerShowAllViewHolder, position: Int) {
            // position 번째 객체의 데이터 설정
            holder.rowShowAllBinding.textShowAllSubject.text = memoList[position].memoSubject
            holder.rowShowAllBinding.textShowAllWriteDate.text = memoList[position].memoDate

            // RecyclerView 항목 리스너
            holder.rowShowAllBinding.root.setOnClickListener {
                // Bundle 객체에 담기
                val memoReadBundle = Bundle()
                memoReadBundle.putInt("memoIdx", memoList[position].memoIdx)

                // 메모 보기 화면 보이도록 함
                mainActivity.replaceFragment(FragmentName.MEMO_READ_FRAGMENT, true, true, memoReadBundle)
            }
        }
    }
}