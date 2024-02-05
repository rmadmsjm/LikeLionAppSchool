package kr.co.lion.androidproject_memo

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kr.co.lion.androidproject_memo.databinding.ActivityMainBinding
import kr.co.lion.androidproject_memo.databinding.RowRecyclerviewBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // InputActivity Launcher
    lateinit var inputactivityLauncher: ActivityResultLauncher<Intent>

    // ShowActivity Launcher
    lateinit var showActivityLauncher: ActivityResultLauncher<Intent>

    // 메모 데이터 List
    var memoDataList = mutableListOf<MemoClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        initData()
        setToolbar()
        setView()
    }

    // 기본 데이터 및 객체 세팅
    fun initData() {
        // InputActivity Launcher
        val inputContract = ActivityResultContracts.StartActivityForResult()
        inputactivityLauncher = registerForActivityResult(inputContract) {
            // resultCode에 따라 분기
            when(it.resultCode) {
                RESULT_OK -> {
                    // 전달된 Intent객체가 있을 때 객체 추출
                    if(it.data != null) {
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            val memoData = it.data?.getParcelableExtra("memoData", MemoClass::class.java)
                            memoDataList.add(memoData!!)

                            activityMainBinding.recyclerViewMain.adapter?.notifyDataSetChanged()

                        } else {
                            val memoData = it.data?.getParcelableExtra<MemoClass>("memoData")
                            memoDataList.add(memoData!!)

                            activityMainBinding.recyclerViewMain.adapter?.notifyDataSetChanged()
                        }
                        
                        Snackbar.make(activityMainBinding.root, "메모가 등록되었습니다", Snackbar.LENGTH_SHORT).show()
                    } else {
                        Snackbar.make(activityMainBinding.root, "메모 데이터가 없습니다", Snackbar.LENGTH_SHORT).show()
                    }
                }
                RESULT_CANCELED -> {
                    Snackbar.make(activityMainBinding.root, "메모 작성을 취소했습니다", Snackbar.LENGTH_SHORT).show()
                }
            }
        }

        // ShowActivity Launcher
        val showContract = ActivityResultContracts.StartActivityForResult()
        showActivityLauncher = registerForActivityResult(showContract) {
            // resultCode에 따라 분기
            when(it.resultCode) {
                RESULT_OK -> {
                    if(it.data != null) {
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            val deleteMemoData = it.data?.getParcelableExtra("memoData", MemoClass::class.java)
                            memoDataList.remove(deleteMemoData)

                            activityMainBinding.recyclerViewMain.adapter?.notifyDataSetChanged()
                        } else {
                            val deleteMemoData = it.data?.getParcelableExtra<MemoClass>("memoData")
                            memoDataList.remove(deleteMemoData)

                            activityMainBinding.recyclerViewMain.adapter?.notifyDataSetChanged()
                        }
                        Snackbar.make(activityMainBinding.root, "메모가 삭제되었습니다", Snackbar.LENGTH_SHORT).show()
                    } else {
                        Snackbar.make(activityMainBinding.root, "삭제할 메모 데이터가 없습니다", Snackbar.LENGTH_SHORT).show()
                    }
                }
                RESULT_CANCELED -> {
                }
            }
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
        inner class ViewHolderClass(rowRecyclerviewBinding: RowRecyclerviewBinding) : RecyclerView.ViewHolder(rowRecyclerviewBinding.root) {
                val rowRecyclerviewBinding: RowRecyclerviewBinding

                init {
                    this.rowRecyclerviewBinding = rowRecyclerviewBinding

                    // 항목의 가로, 세로 길이 설정
                    this.rowRecyclerviewBinding.root.layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )

                    // 항목 리스너
                    this.rowRecyclerviewBinding.root.setOnClickListener {
                        // ShowActivity 실행
                        val showIntent = Intent(this@MainActivity, ShowActivity::class.java)
                        showIntent.putExtra("memoData", memoDataList[adapterPosition])
                        showActivityLauncher.launch(showIntent)
                    }
                }
            }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val rowRecyclerviewBinding = RowRecyclerviewBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolderClass(rowRecyclerviewBinding)

            return viewHolderClass
        }

        override fun getItemCount(): Int {
            return memoDataList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.rowRecyclerviewBinding.textViewRowTitle.text = memoDataList[position].title
            holder.rowRecyclerviewBinding.textViewRowDate.text = memoDataList[position].date
        }
    }
}