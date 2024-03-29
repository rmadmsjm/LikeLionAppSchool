package kr.co.lion.ex14_sqlitedatabase1

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.ex14_sqlitedatabase1.databinding.ActivityMainBinding
import kr.co.lion.ex14_sqlitedatabase1.databinding.RowMainBinding

/*
Model 클래스 : 데이터를 담는 클래스
Dao 클래스 : 데이터베이스에 관련된 작업을 담는 클래스
 */

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // InputActivity 런처
    lateinit var inputActivityLauncher: ActivityResultLauncher<Intent>
    // ShowInfoActivity 런처
    lateinit var showInfoActivityLauncher: ActivityResultLauncher<Intent>

    // Dao가 반환하는 학생 객체를 담을 리스트
    lateinit var studentList: MutableList<StudentModel>

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
        // InputActivity 런처
        val contract1 = ActivityResultContracts.StartActivityForResult()
        inputActivityLauncher = registerForActivityResult(contract1){
            // 저장된 데이터 가져와서 RecycelrView 갱신
            studentList = StudentDao.selectAllStudent(this@MainActivity)
            activityMainBinding.recyclerViewMain.adapter?.notifyDataSetChanged()
        }

        // ShowInfoActivity 런처
        val contract2 = ActivityResultContracts.StartActivityForResult()
        showInfoActivityLauncher = registerForActivityResult(contract2){
        }
    }

    // 툴바 구성
    fun setToolbar(){
        activityMainBinding.apply {
            toolbarMain.apply {
                // 타이틀
                title = "학생 정보 관리"
                // 메뉴
                inflateMenu(R.menu.menu_main)
                // 메뉴의 리스너
                setOnMenuItemClickListener {
                    // 메뉴의 id로 분기
                    when(it.itemId){
                        // 추가 메뉴
                        R.id.menu_main_add -> {
                            // InputActivity를 실행
                            val inputIntent = Intent(this@MainActivity, InputActivity::class.java)
                            inputActivityLauncher.launch(inputIntent)
                        }
                    }
                    true
                }
            }
        }
    }

    // View 구성
    fun setView(){
        activityMainBinding.apply {
            // ReyclerView
            recyclerViewMain.apply {
                // RecyclerView 구성을 위한 리스트
                studentList = StudentDao.selectAllStudent(this@MainActivity)
                // 어뎁터 설정
                adapter = RecyclerViewMainAdapter()
                // 레이아웃 매니저
                layoutManager = LinearLayoutManager(this@MainActivity)
                // 구분선
                val deco = MaterialDividerItemDecoration(this@MainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    // RecyclerView의 Adapter
    inner class RecyclerViewMainAdapter : RecyclerView.Adapter<RecyclerViewMainAdapter.ViewHolderMain>(){
        // ViewHolder
        inner class ViewHolderMain(rowMainBinding: RowMainBinding) : RecyclerView.ViewHolder(rowMainBinding.root){
            val rowMainBinding:RowMainBinding

            init{
                this.rowMainBinding = rowMainBinding

                // 항목 클릭시 전체가 클릭이 될 수 있도록 가로 세로 길이를 설정
                this.rowMainBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                // 항목을 눌렀을 때의 리스너
                this.rowMainBinding.root.setOnClickListener {
                    // ShowInfoActivity를 실행
                    val showInfoIntent = Intent(this@MainActivity, ShowInfoActivity::class.java)

                    // Intent에 선택한 학생 번호 담기
                    showInfoIntent.putExtra("idx", studentList[adapterPosition].idx)

                    showInfoActivityLauncher.launch(showInfoIntent)
                }

                // 항목을 길게 눌렀을 때 메뉴를 띄우기
                this.rowMainBinding.root.setOnCreateContextMenuListener { menu, v, menuInfo ->
                    // 메뉴를 구성
                    menuInflater.inflate(R.menu.menu_main_row, menu)
                    // 메뉴 항목을 눌렀을 때
                    menu?.findItem(R.id.menu_main_row_item_delete)?.setOnMenuItemClickListener {
                        // 선택한 학생 정보 삭제 (데이터베이스)
                        StudentDao.deleteStudent(this@MainActivity, studentList[adapterPosition].idx)

                        // 데이터 다시 받아와서 RecycelrView 갱신
                        // 데이터 다시 받아 오는 이유 : 정보를 studentList에서 삭제한 게 아니기 때문
                        studentList = StudentDao.selectAllStudent(this@MainActivity)
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
            holder.rowMainBinding.textViewRowMainName.text = studentList[position].name
            holder.rowMainBinding.textViewRowMainName.text = "${studentList[position].grade}학년"
        }
    }
}