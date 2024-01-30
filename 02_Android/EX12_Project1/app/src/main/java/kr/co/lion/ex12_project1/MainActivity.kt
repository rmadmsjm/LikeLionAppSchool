package kr.co.lion.ex12_project1

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kr.co.lion.ex12_project1.R
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.google.android.material.divider.MaterialDivider
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.ex12_project1.databinding.ActivityMainBinding
import kr.co.lion.ex12_project1.databinding.RowBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // InputInfosActivity 실행을 위한 Launcher
    lateinit var inputInfosActivityLauncher: ActivityResultLauncher<Intent>
    // ShowInfosActivity 실행을 위한 Launcher
    lateinit var showInfosActivityLauncher: ActivityResultLauncher<Intent>

    // 학생 정보를 담을 List
    val studentList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        initData()
        initView()
        initToolbar()
        setView()
    }

    fun initData() {
        // 계약 객체
        // InputInfosActivity 등록
        val contract = ActivityResultContracts.StartActivityForResult()
        inputInfosActivityLauncher = registerForActivityResult(contract) {
        }
    }

    fun initView() {
        activityMainBinding.apply {
            recyclerViewMain.apply {
                adapter = RecyclerViewAdapter()
                layoutManager = LinearLayoutManager(this@MainActivity)
                val deco = MaterialDividerItemDecoration(this@MainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    fun initToolbar() {
        activityMainBinding.apply {
            toolbarMain.apply {
                title = "학생 정보 관리"
                inflateMenu(R.menu.main_menu)
            }
        }
    }

    fun setView() {
        activityMainBinding.apply {
            toolbarMain.apply {
                setOnMenuItemClickListener {

                    // 선택한 메뉴 아이템의 아이디로 분기
                    when(it.itemId) {
                        // 학생 정보 입력
                        R.id.mainMenuItemInputInfo -> {
                            val intent = Intent(this@MainActivity, InputInfosActivity::class.java)
                            inputInfosActivityLauncher.launch(intent)
                        }
                    }

                    true
                }
            }
        }
    }

    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderClass>() {
        inner class ViewHolderClass(rowBinding: RowBinding) : RecyclerView.ViewHolder(rowBinding.root) {
            val rowBinding : RowBinding

            init {
                this.rowBinding = rowBinding

                // 항목 뷰의 가로, 세로 길이 세팅
                this.rowBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                // 항목을 터치했을 때 이벤트
                this.rowBinding.root.setOnClickListener {
                    val intent = Intent(this@MainActivity, ShowInfosActivity::class.java)

                    // 선택한 항목 번째의 학생 객체를 Intent에 담기

                    showInfosActivityLauncher.launch(intent)
                }
            }
        }

        override fun getItemCount(): Int {
            return studentList.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val rowBinding = RowBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolderClass(rowBinding)

            return viewHolderClass
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.rowBinding.textViewName.text = studentList[position]
            holder.rowBinding.textViewGrade.text = studentList[position]
        }
    }
}