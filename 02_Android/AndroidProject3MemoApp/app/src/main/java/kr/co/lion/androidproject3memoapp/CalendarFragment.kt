package kr.co.lion.androidproject3memoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.androidproject3memoapp.databinding.FragmentCalendarBinding
import kr.co.lion.androidproject3memoapp.databinding.RowCalendarBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class CalendarFragment : Fragment() {

    lateinit var fragmentCalendarBinding: FragmentCalendarBinding
    lateinit var mainActivity: MainActivity

    // RecyclerView 구성할 memoList
    var memoList = mutableListOf<MemoModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentCalendarBinding = FragmentCalendarBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingRecyclerMain()
        settingButtonMainToday()
        settingCalendarMain()

        return fragmentCalendarBinding.root
    }

    // 오늘 버튼 설정
    fun settingButtonMainToday() {
        fragmentCalendarBinding.apply {
            buttonMainToday.setOnClickListener {
                // 현재 시간을 Long 값으로 구해 CalendarView에 설정
                // currentTimeMillis() : 메서드를 호출한 시간을 Long 타입으로 반환
                calendarMain.date = System.currentTimeMillis()
                // MainActivity 프로퍼티에 값 넣기
                mainActivity.calendarNowTime = calendarMain.date
                // RecyclerView 갱신
                getMemoDataByDate()
            }
        }
    }

    // 캘린더 설정
    fun settingCalendarMain() {
        fragmentCalendarBinding.apply {
            calendarMain.apply {
                // 캘린더 날짜를 MainActivity의 프로퍼티 값으로 지정
                date = mainActivity.calendarNowTime

                // 캘리더 최대 날짜를 오늘로 설정
                maxDate = System.currentTimeMillis()

                // CalendarView 클릭하면 동작하는 리스너
                // 두 번째, 세 번째, 네 번째 : 각 설정된 년, 월, 일
                setOnDateChangeListener { view, year, month, dayOfMonth ->
                    // MainFragment로 돌아왔을 때의 복원
                    // 캘린더의 현재 날짜 값을 MainActivity의 프로퍼티에 넣기
                    // 년월일 값을 Long 타입 날짜 값으로 변경
                    // 날짤 데이터를 관리하는 객체 생성하고 새롭게 설정된 날짜 값을 넣기
                    val c1 = Calendar.getInstance()
                    c1.set(year, month, dayOfMonth)
                    // 생성된 날짜 값을 Long 타입의 시간 값으로 가져와 담기
                    mainActivity.calendarNowTime = c1.timeInMillis
                    // calendarView의 date 프로퍼티도 설정
                    date = c1.timeInMillis

                    // RecyclerView 갱신
                    getMemoDataByDate()
                }
            }
        }
    }

    // RecyclerView 설정
    fun settingRecyclerMain() {
        fragmentCalendarBinding.apply {
            recyclerMain.apply {
                // adapter 설정
                adapter = ReyclerMAinAdapter()
                // 레이아웃 매니저 설정
                layoutManager = LinearLayoutManager(mainActivity)
                // 데코
                val deco = MaterialDividerItemDecoration(mainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)

                // RecyclerView 갱신
                getMemoDataByDate()
            }
        }
    }

    // RecyclerView Adapter
    inner class ReyclerMAinAdapter : RecyclerView.Adapter<ReyclerMAinAdapter.RecyclerMAinViewHolder>() {
        inner class RecyclerMAinViewHolder(rowCalendarBinding: RowCalendarBinding) : RecyclerView.ViewHolder(rowCalendarBinding.root) {
            val rowCalendarBinding: RowCalendarBinding

            init {
                this.rowCalendarBinding = rowCalendarBinding

                this.rowCalendarBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerMAinViewHolder {
            val rowCalendarBinding = RowCalendarBinding.inflate(layoutInflater)
            val recyclerMAinViewHolder = RecyclerMAinViewHolder(rowCalendarBinding)

            return recyclerMAinViewHolder
        }

        override fun getItemCount(): Int {
            return memoList.size
        }

        override fun onBindViewHolder(holder: RecyclerMAinViewHolder, position: Int) {
            holder.rowCalendarBinding.textCalendarSubject.text = memoList[position].memoSubject

            // RecyclerView 항목 리스너
            holder.rowCalendarBinding.root.setOnClickListener {
                // Bundle 객체에 담기
                val memoReadBundle = Bundle()
                memoReadBundle.putInt("memoIdx", memoList[position].memoIdx)

                // 메모 보기 화면 보이도록 함
                mainActivity.replaceFragment(FragmentName.MEMO_READ_FRAGMENT, true, true, memoReadBundle)
            }
        }
    }

    // 캘린더에 설정되어 있는 날짜의 메모 내용 가져와 RecyclerView 갱신
    fun getMemoDataByDate() {
        // 캘린더에 설정되어 있는 날자 데이터를 '년.월.일' 형태로 만들기
        // -> '년.월.일' : 데이터베이스에 저장되어 있는 형식
        val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
        val targetDate = simpleDateFormat.format(mainActivity.calendarNowTime)

        // 데이터 가져오기
        memoList = MemoDao.selectMemoDataByDate(mainActivity, targetDate)

        // RecyclerView 갱신
        fragmentCalendarBinding.recyclerMain.adapter?.notifyDataSetChanged()
    }
}