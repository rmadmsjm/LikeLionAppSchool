package kr.co.lion.ex10_activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.ex10_activity.databinding.ActivityMainBinding
import kr.co.lion.ex10_activity.databinding.RowBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // InputStudentInfoActivity에서 받아올 각 값을 담을 Lists
    val nameList = mutableListOf<String>()
    val gradeList = mutableListOf<String>()
    val korList = mutableListOf<Int>()
    val engList = mutableListOf<Int>()
    val mathList = mutableListOf<Int>()

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
                            korList.add(korData!!.toInt())
                            engList.add(engData!!.toInt())
                            mathList.add(mathData!!.toInt())
                        }
                    }
                    RESULT_CANCELED -> {
                        Toast.makeText(this@MainActivity, "학생 입력 정보 취소", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        init()
        setViewEvent(inputStudentInfoActivityLauncher)
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

    fun setViewEvent(inputStudentInfoActivityLauncher: ActivityResultLauncher<Intent>){
        activityMainBinding.apply {
            inputStudentInfoBtn.setOnClickListener {
                // Activity 실행
                val inputStudentInfoActivityIntent = Intent(this@MainActivity, InputStudentInfoActivity::class.java)

                inputStudentInfoActivityLauncher.launch(inputStudentInfoActivityIntent)
            }

            totalPointAvgBtn.setOnClickListener {
                // Activity 실행
                val totalPointAvgActivityIntent = Intent(this@MainActivity, TotalPointAvgActivity::class.java)

                // TotalPointAvgActivity로 데이터 전달
                // list를 array로 전환해서 전달
                totalPointAvgActivityIntent.putExtra("kor", korList.toIntArray())
                totalPointAvgActivityIntent.putExtra("eng", engList.toIntArray())
                totalPointAvgActivityIntent.putExtra("math", mathList.toIntArray())

                startActivity(totalPointAvgActivityIntent)
            }
        }
    }

    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderClass>() {

        inner class ViewHolderClass(rowBinding: RowBinding) : RecyclerView.ViewHolder(rowBinding.root) {
            val rowBinding : RowBinding

            init {
                this.rowBinding = rowBinding

                // 항목을 누르면 반응하는 리스너
                this.rowBinding.root.setOnClickListener {
                    // Activity 실행
                    val studentInfoActivityIntent = Intent(this@MainActivity, StudentInfoActivity::class.java)

                    // StudentInfoActivity로 데이터 전달
                    // adapterPosition : RecyclerView에서 클릭된 항목의 위치 제공
                    val position = adapterPosition
                    // RecyclerView.NO_POSITION : RecyclerView의 context에서 유효하지 않거나 정의되지 않은 위치
                    if (position != RecyclerView.NO_POSITION) {
                        studentInfoActivityIntent.putExtra("name", nameList[position])
                        studentInfoActivityIntent.putExtra("grade", gradeList[position])
                        studentInfoActivityIntent.putExtra("kor", korList[position].toString())
                        studentInfoActivityIntent.putExtra("eng", engList[position].toString())
                        studentInfoActivityIntent.putExtra("math", mathList[position].toString())

                        startActivity(studentInfoActivityIntent)
                    }
                }

                // View의 가로 길이를 최대 길이로 맞추기
                this.rowBinding.root.layoutParams = ViewGroup.LayoutParams(
                    // 가로 길이
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    // 세로 길이
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                )
            }
        }

        override fun getItemCount(): Int {
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