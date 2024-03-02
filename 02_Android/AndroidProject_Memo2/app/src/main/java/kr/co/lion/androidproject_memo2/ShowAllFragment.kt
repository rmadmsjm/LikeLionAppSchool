package kr.co.lion.androidproject_memo2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.androidproject_memo2.databinding.FragmentShowAllBinding
import kr.co.lion.androidproject_memo2.databinding.RowShowAllBinding

class ShowAllFragment : Fragment() {

    lateinit var fragmentShowAllBinding: FragmentShowAllBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentShowAllBinding = FragmentShowAllBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingRecyclerViewShowAll()

        return fragmentShowAllBinding.root
    }

    // RecycelrView 설정
    fun settingRecyclerViewShowAll() {
        fragmentShowAllBinding.apply {
            recyclerViewShowAll.apply {
                adapter = RecyclerViewShowAllAdapter()
                layoutManager = LinearLayoutManager(mainActivity)
                val deco = MaterialDividerItemDecoration(mainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    inner class RecyclerViewShowAllAdapter() : RecyclerView.Adapter<RecyclerViewShowAllAdapter.RecycelrViewShowAllViewHolder>() {
        inner class RecycelrViewShowAllViewHolder(rowShowAllBinding: RowShowAllBinding) : RecyclerView.ViewHolder(rowShowAllBinding.root) {
            val rowShowAllBinding: RowShowAllBinding

            init {
                this.rowShowAllBinding = rowShowAllBinding

                this.rowShowAllBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycelrViewShowAllViewHolder {
            val rowShowAllBinding = RowShowAllBinding.inflate(layoutInflater)
            val recycelrViewShowAllViewHolder = RecycelrViewShowAllViewHolder(rowShowAllBinding)

            return recycelrViewShowAllViewHolder
        }

        override fun getItemCount(): Int {
            return 10
        }

        override fun onBindViewHolder(holder: RecycelrViewShowAllViewHolder, position: Int) {
            holder.rowShowAllBinding.textViewRowShowAllTitle.text = "제목 $position"
            holder.rowShowAllBinding.textViewRowShowAllDate.text = "날짜 $position"
        }
    }
}