package kr.co.lion.ex09

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.ex09.databinding.ActivityMainBinding
import kr.co.lion.ex09.databinding.NameBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    val nameList = mutableListOf<String>()
    val filteredNameList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        initView()
        setViewEvent()
    }

    fun initView() {
        activityMainBinding.apply {
            recyclerView.apply {
                // 어뎁터
                adapter = RecyclerViewAdapter()
                // 레이아웃 매니저
                layoutManager = LinearLayoutManager(this@MainActivity)
                // 데코레이션
                val deco = MaterialDividerItemDecoration(this@MainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    fun setViewEvent() {
        activityMainBinding.apply {
            submitBtn.setOnClickListener {
                nameList.add(nameInput.text.toString())

                // RecyclerView 갱신
                recyclerView.adapter?.notifyDataSetChanged()

                // textField 비우기
                nameInput.text?.clear()

                // 포커스 해제
                nameInput.clearFocus()
            }

            searchInput.addTextChangedListener{
                Log.d("Search", "Text changed: $it")
                filterName(it.toString())
            }
        }
    }

    fun filterName(query: String) {
        Log.d("Filter", "Filtering with query: $query")

        filteredNameList.clear()

        if (query.isNotEmpty()) {
            filteredNameList.addAll(nameList.filter { it.contains(query, ignoreCase = true) })
        } else {
            filteredNameList.addAll(nameList)
        }

        Log.d("Data", "Name list size: ${filteredNameList.size}")

        // ⚠️ 검색어에 따라서 recyclerView 업데이트 안 됨
        activityMainBinding.recyclerView.adapter?.notifyDataSetChanged()
    }

    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderClass>(){

        inner class ViewHolderClass(nameBinding: NameBinding): RecyclerView.ViewHolder(nameBinding.root) {
            val nameBinding : NameBinding

            init {
                this.nameBinding = nameBinding
            }
        }

        override fun getItemCount(): Int {
            return nameList.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val nameBinding = NameBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolderClass(nameBinding)

            return viewHolderClass
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.nameBinding.textView.text = nameList[position]
        }
    }
}