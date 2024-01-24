package kr.co.lion.ex08_explanation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.ex08_explanation.databinding.ActivityMainBinding
import kr.co.lion.ex08_explanation.databinding.RowBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // RecyclerView를 구성하기 위한 데이터를 담을 리스트
    val listRow1 = mutableListOf<String>()
    val listRow2 = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        // setTextData()
        initView()
        setViewEvent()
    }

    // 테스트용 데이터 세팅
    fun setTextData() {
        // RecyclerView 구성을 위한 임시 데이터 세팅
        listRow1.addAll(arrayOf("문자열1-1", "문자열1-2", "문자열1-3"))
        listRow2.addAll(arrayOf("문자열2-1", "문자열2-2", "문자열2-3"))
    }

    // View 초기화 메서드
    fun initView() {
        activityMainBinding.apply {
            // RecyclerView 설정
            recyclerViewResult.apply {
                // adapter
                adapter = RecyclerViewAdapter()
                // 레이아웃 매니저
                layoutManager = LinearLayoutManager(this@MainActivity)
                // 데코레이션
                val deco = MaterialDividerItemDecoration(this@MainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    // 이벤트 설정하는 메서드
    fun setViewEvent() {
        activityMainBinding.apply {
            // 버튼 이벤트
            buttonSubmit.setOnClickListener {
                // 사용자가 입력한 내용을 리스트에 담음
                listRow1.add(textFieldUserId.text!!.toString())
                listRow1.add(textFieldUserName.text!!.toString())

                // 입력 요소 비우기
                textFieldUserId.setText("")
                textFieldUserName.setText("")

                // RecyclerView 갱신
                recyclerViewResult.adapter?.notifyDataSetChanged()
            }
        }
    }

    // RecyclerView의 Adapter
    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderClass>() {

        // ViewHolder
        inner class ViewHolderClass(rowBinding: RowBinding) : RecyclerView.ViewHolder(rowBinding.root) {
            val rowBinding : RowBinding

            init {
                this.rowBinding = rowBinding
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val rowBinding = RowBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolderClass(rowBinding)
            return viewHolderClass
        }

        override fun getItemCount(): Int {
            return listRow1.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.rowBinding.textViewRow1.text = listRow1[position]
            holder.rowBinding.textViewRow2.text = listRow2[position]
        }
    }
}