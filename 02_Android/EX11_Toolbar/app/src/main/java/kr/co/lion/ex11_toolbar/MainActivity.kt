package kr.co.lion.ex11_toolbar

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.ex11_toolbar.databinding.ActivityMainBinding
import kr.co.lion.ex11_toolbar.databinding.RowMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // InputActivity 실행을 위한 Launcher
    lateinit var inputActivityLauncher: ActivityResultLauncher<Intent>
    // ReportActivity 실행을 위한 Launcher
    // startActivity() 사용해도 되지만, 다 Launcher로 쓰는 거 추천
    lateinit var reportActivityLauncher: ActivityResultLauncher<Intent>
    // ShowInfoActivity 실행을 위한 Launcher
    lateinit var showInfoActivityLauncher:ActivityResultLauncher<Intent>

    // 학생 정보를 담을 리스트 생성
    val studentList = mutableListOf<InfoClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        initData()
        initView()
        setEvent()
    }

    // 초기 데이터 셋팅
    fun initData() {
        // 계약 객체
        // InputActivity 등록
        val contract1 = ActivityResultContracts.StartActivityForResult()
        inputActivityLauncher = registerForActivityResult(contract1) {
            if(it.resultCode == RESULT_OK) {
                if(it.data != null) {
                    // 객체 호출
                    // createFromParcel 메서드가 호출되고 반환하는 객체를 반환해준다.
                    val info1 = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                        it.data!!.getParcelableExtra("obj", InfoClass::class.java)
                    } else {
                        it.data!!.getParcelableExtra<InfoClass>("obj")
                    }

//                    Log.d("test1234", "${info1?.name}")
//                    Log.d("test1234", "${info1?.grade}")
//                    Log.d("test1234", "${info1?.kor}")
//                    Log.d("test1234", "${info1?.eng}")
//                    Log.d("test1234", "${info1?.math}")

                    // 리스트에 객체 담기
                    studentList.add(info1!!)

                    // RecyclerView 갱신
                    activityMainBinding.recyclerViewItem.adapter?.notifyDataSetChanged()
                }
            }
        }

        // ReportActivity 등록
        val contract2 = ActivityResultContracts.StartActivityForResult()
        reportActivityLauncher = registerForActivityResult(contract2) {
        }

        // ShowInfoActivity 등록
        val contract3 = ActivityResultContracts.StartActivityForResult()
        showInfoActivityLauncher = registerForActivityResult(contract3) {
        }

    }

    // View 초기 셋팅
    fun initView() {
        activityMainBinding.apply {
            materialToolbar.apply {
                title = "학생 정보 관리"
                inflateMenu(R.menu.main_menu)
            }

            recyclerViewItem.apply {
                // RecyclerView에 어뎁터를 설정한다.
                adapter = RecyclerViewAdapter()
                // layoutManager 설정
                layoutManager = LinearLayoutManager(this@MainActivity)
                // 데코레이션
                val dec = MaterialDividerItemDecoration(this@MainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(dec)
            }
        }
    }

    // 이벤트 설정
    fun setEvent() {
        activityMainBinding.apply {
            materialToolbar.apply {
                setOnMenuItemClickListener {
                    when(it.itemId) {
                        R.id.menuStudentInfo -> {
                            // InputActivity
                            val newInent = Intent(this@MainActivity, InputActivity::class.java)
                            inputActivityLauncher.launch(newInent)
                        }

                        R.id.menuTotalPointAvg -> {
                            // ReportActivity
                            val newIntent2 = Intent(this@MainActivity, ReportActivity::class.java)

                            // 학생 정보가 저장되어 있는 여부를 담기
                            val chk1 = if(studentList.size == 0) {
                                false
                            } else {
                                true
                            }

                            newIntent2.putExtra("chk1", chk1)
                            // 담긴 학생 정보가 있다면 각 값을 구해 담아줌
                            if(chk1 == true) {
                                var korTotal = 0
                                var engTotal = 0
                                var mathTotal = 0

                                studentList.forEach {
                                    korTotal += it.kor
                                    engTotal += it.eng
                                    mathTotal += it.math
                                }

                                val korAvg = korTotal / studentList.size
                                val engAvg = engTotal / studentList.size
                                val mathAvg = mathTotal / studentList.size

                                val allTotal = korTotal + engTotal + mathTotal
                                val allAvg = (allTotal / studentList.size) / 3

                                newIntent2.putExtra("korTotal", korTotal)
                                newIntent2.putExtra("engTotal", engTotal)
                                newIntent2.putExtra("mathTotal", mathTotal)
                                newIntent2.putExtra("korAvg", korAvg)
                                newIntent2.putExtra("engAvg", engAvg)
                                newIntent2.putExtra("mathAvg", mathAvg)
                                newIntent2.putExtra("allTotal", allTotal)
                                newIntent2.putExtra("allAvg", allAvg)
                            }

                            reportActivityLauncher.launch(newIntent2)
                        }
                    }

                    true
                }
            }
        }
    }

    // RecyclerView의 어댑터
    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderClass>() {

        // ViewHolder
        inner class ViewHolderClass(rowMainBinding: RowMainBinding) : RecyclerView.ViewHolder(rowMainBinding.root) {
            val rowMainBinding : RowMainBinding

            init {
                this.rowMainBinding = rowMainBinding

                // 항목 뷰의 가로, 세로 길이 세팅
                this.rowMainBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                // 항목을 터치했을 때 이벤트
                this.rowMainBinding.root.setOnClickListener {
                    // 애니메이션을 보여주기 위한 딜레이
                    // SystemClock.sleep(100)
                    // ShowInfoActivity를 실행한다.
                    val newIntent = Intent(this@MainActivity, ShowInfoActivity::class.java)

                    // 선택한 항목 번째의 학생 객체를 Intent에 담기
                    newIntent.putExtra("obj", studentList[adapterPosition])

                    showInfoActivityLauncher.launch(newIntent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val rowMainBinding = RowMainBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolderClass(rowMainBinding)

            return viewHolderClass
        }

        override fun getItemCount(): Int {
            return studentList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.rowMainBinding.textViewRowMainName.text = studentList[position].name
            holder.rowMainBinding.textViewRowMainGrade.text = "${studentList[position].grade}학년"
        }
    }
}