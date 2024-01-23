package kr.co.lion.ex08

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.ex08.databinding.ActivityMainBinding
import kr.co.lion.ex08.databinding.TextBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    val idList = mutableListOf<String>()
    val nameList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.apply {
            // Adapter 객체 생성
            val recyclerViewAdapter = RecyclerViewAdapter()
            // Adapter 적용
            recyclerView.adapter = recyclerViewAdapter
            // RecyclerView 의 항목을 보여줄 방식을 설정
            // 위에서 아래 방향
            // this = activityMainBinding
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

            // RecyclerView Decoration
            // 구분선 양쪽 여백 주기 가능 (Material 3)
            val deco = MaterialDividerItemDecoration(this@MainActivity, MaterialDividerItemDecoration.VERTICAL)
            // 구분선 좌측 여백
            deco.dividerInsetStart = 10
            // 구분선 우측 여백
            deco.dividerInsetEnd = 10
            recyclerView.addItemDecoration(deco)
        }

        setView()

    }

    fun initView() {
    }

    fun setView() {
        activityMainBinding.apply {
            submitBtn.setOnClickListener {
                idList.add(idTextInput.text.toString())
                nameList.add(nameTextInput.text.toString())

                // 어댑터에게 데이터셋이 변경되었음을 알림
                recyclerView.adapter?.notifyDataSetChanged()

                // textField 비우기
                idTextInput.text?.clear()
                nameTextInput.text?.clear()

                // 포커스 해제
                idTextInput.clearFocus()
                nameTextInput.clearFocus()
            }
        }
    }

    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderClass>() {
        // ViewHolder
        // binding 객체 가지고 잇음
        inner class ViewHolderClass(textBinding: TextBinding) : RecyclerView.ViewHolder(textBinding.root) {
            // 매개변수로 들어오는 바인딩 객체를 담을 프로퍼티
            // 항목 안의 것까지 객체 만들 필요 없음
            // text.xml
            val textBinding: TextBinding

            init {
                this.textBinding = textBinding
            }
        }

        // RecyclerView를 통해 보여줄 항목 전체의 개수 반환
        override fun getItemCount(): Int {
            return idList.size
        }

        // ViewHolder 객체를 생성해 반환함
        // 새롭게 항목이 보여질 때 재사용 가능한 항목이 없다면 이 메서드를 호출함
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            // View Binding
            val textBinding = TextBinding.inflate(layoutInflater)
            // View Holder
            val viewHolderClass = ViewHolderClass(textBinding)

            return viewHolderClass
        }

        // 항목의 View에 보여주고자 하는 데이터 설정
        // 첫 번째 매개변수 : ViewHolder 객체, 재사용 가능한 것이 없다면 onCreatViewHolder 메서드를
        // 호출하고 반환하는 ViewHolder 객체가 들어오고 재사용 가능한 것이 있따면 재사용 가능한 ViewHolder 객체가 들어옴
        // 두 번째 매개변수 : 구성하고자 하는 항목의 순서값(0부터 1씩 증가)
        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.textBinding.textView.text = idList[position]
            holder.textBinding.textView2.text = nameList[position]
        }
    }
}