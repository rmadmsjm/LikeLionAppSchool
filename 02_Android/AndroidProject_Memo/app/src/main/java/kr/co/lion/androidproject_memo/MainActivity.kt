package kr.co.lion.androidproject_memo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.androidproject_memo.databinding.ActivityMainBinding
import kr.co.lion.androidproject_memo.databinding.RowRecyclerviewBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // InputActivity Launcher
    lateinit var inputactivityLauncher: ActivityResultLauncher<Intent>

    // 메모 List
    var memoList = mutableListOf<MemoClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        initData()
        setToolbar()
    }

    // 기본 데이터 및 객체 세팅
    fun initData() {
        // InputActivity Launcher
        val inputContract = ActivityResultContracts.StartActivityForResult()
        inputactivityLauncher = registerForActivityResult(inputContract) {
        }
    }

    // Toolbar
    fun setToolbar() {
        activityMainBinding.apply {
            toolbarMain.apply {
                // 타이틀
                title = "메모 관리"
                // 메뉴 설정
                inflateMenu(R.menu.menu_main)
                // 메뉴 리스너
                setOnMenuItemClickListener {
                    // 메뉴 id로 분기
                    when(it.itemId) {
                        R.id.menuItemMainAdd -> {
                            val mainIntent = Intent(this@MainActivity, InputActivity::class.java)
                            inputactivityLauncher.launch(mainIntent)
                        }
                    }

                    true
                }
            }
        }
    }

    // View 구성
    fun setView() {
        activityMainBinding.apply {
            recyclerViewMain.apply {
                adapter = RecyclerViewAdapter()
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }
    }

    // RecycelrView Adapter
    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderClass>() {
        // ViewHolder
        inner class ViewHolderClass(rowRecyclerviewBinding: RowRecyclerviewBinding)
            : RecyclerView.ViewHolder(rowRecyclerviewBinding.root) {
                val rowRecyclerviewBinding: RowRecyclerviewBinding

                init {
                    this.rowRecyclerviewBinding = rowRecyclerviewBinding

                    // 항목의 가로, 세로 길이 설정
                    this.rowRecyclerviewBinding.root.layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                }
            }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val rowRecyclerviewBinding = RowRecyclerviewBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolderClass(rowRecyclerviewBinding)

            return viewHolderClass
        }

        override fun getItemCount(): Int {
            return 20
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.rowRecyclerviewBinding.textViewRowTitle.text = "제목 $position"

            // 현재 날짜 설정
            val dateFormat = SimpleDateFormat("yyyy.MM,dd", Locale.getDefault())
            val currentDate = dateFormat.format(Date(System.currentTimeMillis()))
            holder.rowRecyclerviewBinding.textViewRowDate.text = currentDate
        }
    }
}