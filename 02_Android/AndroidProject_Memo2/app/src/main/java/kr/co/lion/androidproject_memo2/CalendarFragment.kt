package kr.co.lion.androidproject_memo2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.androidproject_memo2.databinding.FragmentCalendarBinding
import kr.co.lion.androidproject_memo2.databinding.RowCalendarBinding

class CalendarFragment : Fragment() {

    lateinit var fragmentCalendarBinding: FragmentCalendarBinding
    lateinit var mainActivity: MainActivity
    lateinit var mainFragment: MainFragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentCalendarBinding = FragmentCalendarBinding.inflate(inflater)
        mainActivity = activity as MainActivity
        mainFragment = MainFragment()

        settingView()

        return fragmentCalendarBinding.root
    }

    // View 설정
    fun settingView() {
        fragmentCalendarBinding.apply {
            recyclerViewCalendar.apply {
                adapter = RecyclerViewCalendarAdapter()
                layoutManager = LinearLayoutManager(mainActivity)
            }
        }
    }

    // RecycelrView 어뎁터
    inner class RecyclerViewCalendarAdapter : RecyclerView.Adapter<RecyclerViewCalendarAdapter.ViewHolderCalendar>() {
        inner class ViewHolderCalendar(rowCalendarBinding: RowCalendarBinding) : RecyclerView.ViewHolder(rowCalendarBinding.root) {
            val rowCalendarBinding: RowCalendarBinding

            init {
                this.rowCalendarBinding = rowCalendarBinding

                this.rowCalendarBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCalendar {
            val rowCalendarBinding = RowCalendarBinding.inflate(layoutInflater)
            val viewHolderCalendar = ViewHolderCalendar(rowCalendarBinding)

            return viewHolderCalendar
        }

        override fun getItemCount(): Int {
            return 10
        }

        override fun onBindViewHolder(holder: ViewHolderCalendar, position: Int) {
            holder.rowCalendarBinding.textViewRowCalendarTitle.text = "제목 $position"
            holder.rowCalendarBinding.textViewRowCalendarDate.text = "날짜 $position"

            holder.rowCalendarBinding.root.setOnClickListener {
                val showBundle = Bundle()
                showBundle.putInt("positon", position)

                mainActivity.replaceFragment(FragmentName.SHOW_FRAGMENT, true, true, showBundle)
            }
        }
    }
}