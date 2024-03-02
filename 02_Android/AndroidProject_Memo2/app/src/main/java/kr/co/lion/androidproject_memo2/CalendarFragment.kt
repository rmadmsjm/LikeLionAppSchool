package kr.co.lion.androidproject_memo2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.androidproject_memo2.databinding.FragmentCalendarBinding
import kr.co.lion.androidproject_memo2.databinding.RowCalendarBinding

class CalendarFragment : Fragment() {

    lateinit var fragmentCalendarBinding: FragmentCalendarBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentCalendarBinding = FragmentCalendarBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingRecyclerViewCalendar()
        
        return fragmentCalendarBinding.root
    }

    // RecycelrView 설정
    fun settingRecyclerViewCalendar() {
        fragmentCalendarBinding.apply {
            recyeclerViewCalendar.apply {
                adapter = RecycelrViewCalendarAdapter()
                layoutManager = LinearLayoutManager(mainActivity)
                val deco = MaterialDividerItemDecoration(mainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    inner class RecycelrViewCalendarAdapter() : RecyclerView.Adapter<RecycelrViewCalendarAdapter.RecycelrViewCalendarViewHolder>() {
        inner class RecycelrViewCalendarViewHolder(rowCalendarBinding: RowCalendarBinding) : RecyclerView.ViewHolder(rowCalendarBinding.root) {
            val rowCalendarBinding: RowCalendarBinding

            init {
                this.rowCalendarBinding = rowCalendarBinding

                this.rowCalendarBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycelrViewCalendarViewHolder {
            val rowCalendarBinding = RowCalendarBinding.inflate(layoutInflater)
            val recycelrViewCalendarViewHolder = RecycelrViewCalendarViewHolder(rowCalendarBinding)

            return recycelrViewCalendarViewHolder
        }

        override fun getItemCount(): Int {
            return 10
        }

        override fun onBindViewHolder(holder: RecycelrViewCalendarViewHolder, position: Int) {
            holder.rowCalendarBinding.textViewRowCalendarTitle.text = "제목 $position"
        }
    }
}