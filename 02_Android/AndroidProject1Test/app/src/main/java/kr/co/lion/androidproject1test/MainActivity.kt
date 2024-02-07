package kr.co.lion.androidproject1test

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.androidproject1test.databinding.ActivityMainBinding
import kr.co.lion.androidproject1test.databinding.RowMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // InputActivity Launcher
    lateinit var inputActivityLaucher : ActivityResultLauncher<Intent>
    // ShowActivity Launcher
    lateinit var showActivityLauncher : ActivityResultLauncher<Intent>

    // RecyclerView 구성하기 위한 리스트
    val recyclerViewList = mutableListOf<Animal>()
    // 현재 항목을 구성하기 위해 사용한 객체가 Util.animlList의 몇 번째 객체인지 담을 리스트
    val recyclerViewIndexList = mutableListOf<Int>()

    // 현재 선택 되어 있는 필터 타입
    var filterType = FilterType.FILTER_TYPE_ALL
    // 현재 선택되어 있는 필터 타입 - MultiChoice
    var filterTypeMulti = booleanArrayOf(true, true, true)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        setLauncher()
        setToolbar()
        setView()
        setEvent()
    }

    // Launcher 설정
    fun setLauncher() {
        // InputActivity Launcher
        val contract1 = ActivityResultContracts.StartActivityForResult()
        inputActivityLaucher = registerForActivityResult(contract1) {
        }

        // ShowActivity Launcher
        val contract2 = ActivityResultContracts.StartActivityForResult()
        showActivityLauncher = registerForActivityResult(contract2) {
        }
    }

    override fun onResume() {
        super.onResume()
        activityMainBinding.apply {
            // 필터 타입에 맞는 데이터 담기
            setRecyclerViewList()
            // RecyclerView 갱신
            recyclerViewMain.adapter?.notifyDataSetChanged()
        }
    }

    // 툴바
    fun setToolbar() {
        activityMainBinding.apply {
            toolbarMain.apply {
                // 타이틀
                title = "동물원 관리"
                // 메뉴
                inflateMenu(R.menu.menu_main)
                setOnMenuItemClickListener {
                    when(it.itemId) {
                        // 필터 메뉴
                        R.id.menu_item_main_filter -> {
                            // 필터 선택을 위한 다이얼로그 띄우기
                            // 기본 다이얼로그
                            showFilterDialog()
                            // MultiChoice 다이얼로그
//                            showFilterDialogMultiChoice()
                            // SingChoice 다이얼로그
//                            showFilterDialogSingleChoice()
                        }
                    }

                    true
                }
            }
        }
    }

    // View 설정
    fun setView() {
        activityMainBinding.apply {
            recyclerViewMain.apply {
                adapter = RecyclerViewMainAdapter()
                layoutManager = LinearLayoutManager(this@MainActivity)
                val deco = MaterialDividerItemDecoration(this@MainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    // 이벤트 설정
    fun setEvent() {
        activityMainBinding.apply {
            // FloatActionButton
            fabMainAdd.setOnClickListener {
                // inputActivity 실행
                val inputIntent = Intent(this@MainActivity, InputActivity::class.java)
                inputActivityLaucher.launch(inputIntent)
            }
        }
    }

    // RecyclerView Adapter
    inner class RecyclerViewMainAdapter : RecyclerView.Adapter<RecyclerViewMainAdapter.ViewHolderMain>() {
        // ViewHolder
        inner class ViewHolderMain(rowMainBinding: RowMainBinding) : RecyclerView.ViewHolder(rowMainBinding.root) {
            val rowMainBinding : RowMainBinding

            init {
                this.rowMainBinding = rowMainBinding

                this.rowMainBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        //재사용 가능한 항목이 없을 때 호출
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMain {
            val rowMainBinding = RowMainBinding.inflate(layoutInflater)
            val viewHolderMain = ViewHolderMain(rowMainBinding)

            return viewHolderMain
        }

        override fun getItemCount(): Int {
            return recyclerViewList.size
        }

        override fun onBindViewHolder(holder: ViewHolderMain, position: Int) {
//            when(position % 3) {
//                0 -> {
//                    holder.rowMainBinding.textViewRowMainName.text = "사자"
//                    holder.rowMainBinding.imageViewRowMainType.setImageResource(R.drawable.lion)
//                }
//                1 -> {
//                    holder.rowMainBinding.textViewRowMainName.text = "호랑이"
//                    holder.rowMainBinding.imageViewRowMainType.setImageResource(R.drawable.tiger)
//                }
//                2 -> {
//                    holder.rowMainBinding.textViewRowMainName.text = "기린"
//                    holder.rowMainBinding.imageViewRowMainType.setImageResource(R.drawable.giraffe)
//                }
//            }

            // position 번째 객체 추출
            val animal = recyclerViewList[position]

            // 동물 이름 설정
            holder.rowMainBinding.textViewRowMainName.text = animal.name

            // 동물 타입 이미지 설정
            // 타입별로 분기
            when(animal.type) {
                // 사자
                AnimalType.ANIMAL_TYPE_LION -> {
                    holder.rowMainBinding.imageViewRowMainType.setImageResource(R.drawable.lion)
                }

                // 호랑이
                AnimalType.ANIMAL_TYPE_TIGER -> {
                    holder.rowMainBinding.imageViewRowMainType.setImageResource(R.drawable.tiger)
                }

                // 기린
                AnimalType.ANIMAL_TYPE_GIRAFFE -> {
                    holder.rowMainBinding.imageViewRowMainType.setImageResource(R.drawable.giraffe)
                }
            }

            // 항목 리스너 (ShowActivity 실행)
            holder.rowMainBinding.root.setOnClickListener {
                val showIntnet = Intent(this@MainActivity, ShowActivity::class.java)
                // 현재 항목의 순서값 담기
                // showIntnet.putExtra("position", position)

                // 사용자가 선택한 항목을 구성하기 위해 사용한 객체가 Util.animalList 리스트에 몇 번째에 있는 값인지 담기
                showIntnet.putExtra("position", recyclerViewIndexList[position])

                showActivityLauncher.launch(showIntnet)
            }
        }
    }

    // 기본 필터 다이얼로그 띄우는 메서드
    fun showFilterDialog() {
        val dialogBuilder = MaterialAlertDialogBuilder(this@MainActivity)
        dialogBuilder.setTitle("필터 선택")

        // 항목
        val itemArray = arrayOf("전체", "사자", "호랑이", "기린")
        dialogBuilder.setItems(itemArray) { dialogInterface: DialogInterface, i: Int ->
            // 리스너의 두 번째 매개변수(i)에는 사용자가 선택한 다이얼로그의 항목의 순서값이 전달됨
            // 선택한 항목에 대한 필터값 설정
            // 사용자가 선택한 다이얼로그의 항목 순서값으로 분기
            filterType = when(i) {
                0 -> FilterType.FILTER_TYPE_ALL
                1 -> FilterType.FILTER_TYPE_LION
                2 -> FilterType.FILTER_TYPE_TIGER
                3 -> FilterType.FILTER_TYPE_GIAFFE
                else -> FilterType.FILTER_TYPE_ALL
            }

            // 데이터 새로 담기
            setRecyclerViewList()
            // RecyclerView 갱신
            activityMainBinding.recyclerViewMain.adapter?.notifyDataSetChanged()
        }

        dialogBuilder.setNegativeButton("취소", null)
        dialogBuilder.show()
    }

    // MultiChoice 필터 다이얼로그 띄우는 메서드
    fun showFilterDialogMultiChoice(){
        val dialogBuilder = MaterialAlertDialogBuilder(this@MainActivity)
        dialogBuilder.setTitle("필터 선택")

        // 항목
        val itemArray = arrayOf("사자", "호랑이", "기린")
        // 두 번째 : 체크 상태가 변경된 항목의 순서값
        // 세 번째 : 체크 상태
        dialogBuilder.setMultiChoiceItems(itemArray, filterTypeMulti) { dialogInterface: DialogInterface, i: Int, b: Boolean ->
            // 체크가 변경된 항목 번째의 값 변경
            filterTypeMulti[i] = b
        }

        dialogBuilder.setNegativeButton("취소", null)
        dialogBuilder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
            // 데이터를 새로 담기
            setRecyclerViewList()
            // 리사이클러뷰를 갱신
            activityMainBinding.recyclerViewMain.adapter?.notifyDataSetChanged()
        }
        dialogBuilder.show()
    }

    // SingleChoice 필터 다이얼로그 띄우는 메서드
    fun showFilterDialogSingleChoice() {
        val dialogBuilder = MaterialAlertDialogBuilder(this@MainActivity)
        dialogBuilder.setTitle("필터 선택")

        // 항목
        val itemArray = arrayOf("전체", "사자", "호랑이", "기린")
        dialogBuilder.setSingleChoiceItems(itemArray, filterType.num) { dialogInterface: DialogInterface, i: Int ->
            // 리스너의 두 번째 매개변수(i)에는 사용자가 선택한 다이얼로그의 항목의 순서값이 전달됨
            // 선택한 항목에 대한 필터값 설정
            // 사용자가 선택한 다이얼로그의 항목 순서값으로 분기
            filterType = when (i) {
                0 -> FilterType.FILTER_TYPE_ALL
                1 -> FilterType.FILTER_TYPE_LION
                2 -> FilterType.FILTER_TYPE_TIGER
                3 -> FilterType.FILTER_TYPE_GIAFFE
                else -> FilterType.FILTER_TYPE_ALL
            }
        }

        dialogBuilder.setNegativeButton("취소", null)
        dialogBuilder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
            // 데이터를 새로 담기
            setRecyclerViewList()
            // 리사이클러뷰를 갱신
            activityMainBinding.recyclerViewMain.adapter?.notifyDataSetChanged()
        }
        dialogBuilder.show()
    }

    // 검색 필터에 따라 리스트에 데이터 담는 메서드 호출 메서드
    fun setRecyclerViewList() {
        // 기본 다이얼로그, SingleChoice 다이얼로그
        setRecyclerViewListBasic()
        // MultiChoice 다이얼로그
//        setRecyclerViewListMulti()
    }

    // 기본 다이얼로그, SingleChoice 다이얼로그 : 검색 필터에 따라 리스트에 데이터 담기
    fun setRecyclerViewListBasic() {
        // 리스트 초기화
        recyclerViewList.clear()
        recyclerViewIndexList.clear()

        // 필터에 따라 분기
        when(filterType) {
            // 전체
            FilterType.FILTER_TYPE_ALL -> {
                // 모두 담기
                var index = 0
                Util.animalList.forEach {
                    recyclerViewList.add(it)
                    recyclerViewIndexList.add(index)
                    index++
                }
            }
            // 사자
            FilterType.FILTER_TYPE_LION -> {
                // 동물 타입이 사자인 것만 담기
                var index = 0
                Util.animalList.forEach {
                    if(it.type == AnimalType.ANIMAL_TYPE_LION) {
                        recyclerViewList.add(it)
                        recyclerViewIndexList.add(index)
                    }
                    index++
                }
            }
            // 호랑이
            FilterType.FILTER_TYPE_TIGER -> {
                // 동물 타입이 호랑이 것만 담기
                var index = 0
                Util.animalList.forEach {
                    if(it.type == AnimalType.ANIMAL_TYPE_TIGER) {
                        recyclerViewList.add(it)
                        recyclerViewIndexList.add(index)
                    }
                    index++
                }
            }
            // 기린
            FilterType.FILTER_TYPE_GIAFFE -> {
                // 동물 타입이 기린인 것만 담기
                var index = 0
                Util.animalList.forEach {
                    if(it.type == AnimalType.ANIMAL_TYPE_GIRAFFE) {
                        recyclerViewList.add(it)
                        recyclerViewIndexList.add(index)
                    }
                    index++
                }
            }
        }
    }

    // MultiChoice 다이얼로그 :  검색 필터에 따라 리스트에 데이터 담기
    fun setRecyclerViewListMulti() {
        // 리스트 초기화
        recyclerViewList.clear()
        recyclerViewIndexList.clear()

        // animalList에 담긴 객체 수 만큼 반복
        var index = 0
        Util.animalList.forEach {
            // 동물 타입이 사자이고, 사자가 true라면 담기
            if(it.type == AnimalType.ANIMAL_TYPE_LION && filterTypeMulti[0] == true) {
                recyclerViewList.add(it)
                recyclerViewIndexList.add(index)
            }
            // 동물 타입이 호랑이이고, 호랑이가 true라면 담기
            else if(it.type == AnimalType.ANIMAL_TYPE_TIGER && filterTypeMulti[1] == true) {
                recyclerViewList.add(it)
                recyclerViewIndexList.add(index)
            }
            // 동물 타입이 기린이고, 기린이 true라면 담기
            else if(it.type == AnimalType.ANIMAL_TYPE_GIRAFFE && filterTypeMulti[2] == true) {
                recyclerViewList.add(it)
                recyclerViewIndexList.add(index)
            }
            index++
        }
    }
}