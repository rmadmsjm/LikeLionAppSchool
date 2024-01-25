package kr.co.lion.ex10_activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.ex10_activity.databinding.ActivityMainBinding
import kr.co.lion.ex10_activity.databinding.RowBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    // InputStudentInfoActivity에서 받아올 값을 담을 List
//    val studentInfosList = mutableListOf<String>()
    // InputStudentInfoActivity에서 받아올 각 값을 담을 Lists
    val nameList = mutableListOf<String>()
    val gradeList = mutableListOf<String>()
    val korList = mutableListOf<String>()
    val engList = mutableListOf<String>()
    val mathList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)


        // 계약 객체
        val contract = ActivityResultContracts.StartActivityForResult()
        // 계약 등록
        // InputStudentInfoActivity
        val inputStudentInfoActivityLauncher = registerForActivityResult(contract) {
            // InputStudentInfoActivity 갔다 돌아왔을 때 동작할 코드
            if(it != null) {
                when(it.resultCode) {
                    RESULT_OK -> {
                        // setResult 메서드에 설정한 Intent 객체로부터 데이터 추출
                        if(it.data != null) {
//                            val studentInfos = it.data!!.getStringArrayExtra("studentInfos")
                            val nameData = it.data!!.getStringExtra("name")
                            val gradeData = it.data!!.getStringExtra("grade")
                            val korData = it.data!!.getStringExtra("kor")
                            val engData = it.data!!.getStringExtra("eng")
                            val mathData = it.data!!.getStringExtra("math")

                            nameList.add(nameData.toString())
                            gradeList.add(gradeData.toString())
                            korList.add(korData.toString())
                            engList.add(engData.toString())
                            mathList.add(mathData.toString())
                        }
                    }
                    RESULT_CANCELED -> {
                        //
                    }
                }
            }
        }
        // TotalPointAvgActivity
        val totalPointAvgActivityLauncher = registerForActivityResult(contract) {
            // TotalPointAvgActivity 갔다 돌아왔을 대 동작할 코드
        }
        // StudentInfoActivity

        init()
        setViewEvent(inputStudentInfoActivityLauncher, totalPointAvgActivityLauncher)
    }

    fun init() {
        activityMainBinding.apply {
            // RecyclerView 설정
            studentInfoRecyclerView.apply {
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

    fun setViewEvent(inputStudentInfoActivityLauncher: ActivityResultLauncher<Intent>, totalPointAvgActivityLauncher: ActivityResultLauncher<Intent>){
        activityMainBinding.apply {
            inputStudentInfoBtn.setOnClickListener {
                // Activity 실행
                val inputStudentInfoActivityIntent = Intent(this@MainActivity, InputStudentInfoActivity::class.java)

                inputStudentInfoActivityLauncher.launch(inputStudentInfoActivityIntent)
            }

            totalPointAvgBtn.setOnClickListener {
                // Activity 실행
                val totalPointAvgActivityIntent = Intent(this@MainActivity, TotalPointAvgActivity::class.java)
                totalPointAvgActivityLauncher.launch(totalPointAvgActivityIntent)
            }
        }
    }

    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderClass>() {

        inner class ViewHolderClass(rowBinding: RowBinding) : RecyclerView.ViewHolder(rowBinding.root) {
            val rowBinding : RowBinding

            init {
                this.rowBinding = rowBinding
            }
        }

        override fun getItemCount(): Int {
//            return studentInfosList.size
            return nameList.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val rowBinding = RowBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolderClass(rowBinding)

            return viewHolderClass
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.rowBinding.nameTextView.text = nameList[position]
            holder.rowBinding.gradeTextView.text = gradeList[position]
        }
    }
}