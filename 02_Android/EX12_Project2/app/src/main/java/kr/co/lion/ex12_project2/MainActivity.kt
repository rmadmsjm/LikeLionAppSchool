package kr.co.lion.ex12_project2

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.ex12_project2.databinding.ActivityMainBinding
import kr.co.lion.ex12_project2.databinding.RowMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // InputActivity 런처
    lateinit var inputActivityLauncher:ActivityResultLauncher<Intent>
    // ShowInfoActivity 런처
    lateinit var showInfoActivityLauncher:ActivityResultLauncher<Intent>

    // 학생 정보 List
    var studentList = mutableListOf<StudentData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        initData()
        setToolbar()
        setView()
    }

    // 기본 데이터 및 객체 셋팅
    fun initData() {
        // InputAcitvity 런처
        val contract1 = ActivityResultContracts.StartActivityForResult()
        inputActivityLauncher = registerForActivityResult(contract1){
            // 작업 결과가 ok 라면
            if(it.resultCode == RESULT_OK) {
                // 전달된 Intent객체가 있다면
                if(it.data != null) {
                    // 학생 객체를 추출한다.
                    if(Build.VERSION.SDK_INT == Build.VERSION_CODES.TIRAMISU) {
                        val studentData = it.data?.getParcelableExtra("studentData", StudentData::class.java)
                        studentList.add(studentData!!)
                        activityMainBinding.recyclerViewMain.adapter?.notifyDataSetChanged()
                    } else {
                        val studentData = it.data?.getParcelableExtra<StudentData>("studentData")
                        studentList.add(studentData!!)
                        activityMainBinding.recyclerViewMain.adapter?.notifyDataSetChanged()
                    }
                }
            }
        }

        // ShowInfoActivity 런처
        val contract2 = ActivityResultContracts.StartActivityForResult()
        showInfoActivityLauncher = registerForActivityResult(contract2){
        }
    }

    // 툴바 구성
    fun setToolbar() {
        activityMainBinding.apply {
            toolbarMain.apply {
                // 타이틀
                title = "학생 정보 관리"
                // 메뉴 설정
                inflateMenu(R.menu.menu_main)
                // 메뉴 리스너
                setOnMenuItemClickListener {
                    // 메뉴의 id로 분기
                    when(it.itemId) {
                        // 학생 정보 추가 메뉴
                        R.id.menu_main_add -> {
                            // InputActivity 실행
                            val inputIntent = Intent(this@MainActivity, InputActivity::class.java)
                            inputActivityLauncher.launch(inputIntent)
                        }
                    }
                    true
                }
            }
        }
    }

    // 뷰 구성
    fun setView() {
        activityMainBinding.apply {
            // RecyclerView
            recyclerViewMain.apply {
                adapter = RecycelrViewMainAdapter()
                layoutManager = LinearLayoutManager(this@MainActivity)
                val deco = MaterialDividerItemDecoration(this@MainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    // RecycelrView Adapter
    inner class RecycelrViewMainAdapter : RecyclerView.Adapter<RecycelrViewMainAdapter.ViewHolderMain>() {
        // ViewHolder
        inner class ViewHolderMain(rowMainBinding: RowMainBinding) : RecyclerView.ViewHolder(rowMainBinding.root) {
            val rowMainBinding : RowMainBinding

            init {
                this.rowMainBinding = rowMainBinding

                // 항목 클릭 시 전체가 클릭이 될 수 있도록 가로, 세로 길이 설정
                this.rowMainBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                // 항목을 눌렀을 때 리스너
                this.rowMainBinding.root.setOnClickListener {
                    // ShowInfoActivity 실행
                    val showInfoIntent = Intent(this@MainActivity, ShowInfoActivity::class.java)

                    // 선택한 항목 번째의 학생 객체를 Intent에 담기
                    showInfoIntent.putExtra("studentData", studentList[adapterPosition])

                    showInfoActivityLauncher.launch(showInfoIntent)
                }

                // 항목을 길게 눌렀을 때 context menu
                this.rowMainBinding.root.setOnCreateContextMenuListener { menu, v, menuInfo ->
                    // 메뉴 구성
                    menuInflater.inflate(R.menu.menu_main_row, menu)

                    // 메뉴 항목 눌렀을 때
                    menu?.findItem(R.id.menu_main_row_item_delete)?.setOnMenuItemClickListener {
                        // apdaterPostion 번째 객체 리스트에서 삭제
                        studentList.removeAt(adapterPosition)
                        // RecyclerView 갱신
                        activityMainBinding.recyclerViewMain.adapter?.notifyDataSetChanged()
                        true
                    }
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMain {
            val rowMainBinding = RowMainBinding.inflate(layoutInflater)
            val viewHolderMain = ViewHolderMain(rowMainBinding)

            return viewHolderMain
        }

        override fun getItemCount(): Int {
            return studentList.size
        }

        override fun onBindViewHolder(holder: ViewHolderMain, position: Int) {
            holder.rowMainBinding.textViewRowMainName.text = "${studentList[position].name}"
            holder.rowMainBinding.textViewRowMainGrade.text = "${studentList[position].grade}학년"
        }
    }
}