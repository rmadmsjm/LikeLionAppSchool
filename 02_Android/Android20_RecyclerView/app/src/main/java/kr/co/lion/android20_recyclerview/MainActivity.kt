package kr.co.lion.android20_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.android20_recyclerview.databinding.ActivityMainBinding
import kr.co.lion.android20_recyclerview.databinding.RowBinding

// AdapterView : 무한 개의 항목을 보여 주는 목적으로 사용하는 뷰
// Adapter를 사용하기 때문에 AdapterView라고 부름

// Adapter : View를 구성하기 위해 필요한 정보를 가지고 있는 요소

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // 이미지 id
    val imageRes = arrayOf(
        R.drawable.imgflag1,
        R.drawable.imgflag2,
        R.drawable.imgflag3,
        R.drawable.imgflag4,
        R.drawable.imgflag5,
        R.drawable.imgflag6,
        R.drawable.imgflag7,
        R.drawable.imgflag8
    )

    // 문자열 1
    val textData1 = arrayOf(
        "토고", "프랑스", "스위스", "스페인", "일본", "독일", "브라질", "대한민국"
    )

    // 문자열2
    val textData2 = arrayOf(
        "탈락", "진출", "탈락", "진출", "탈락", "진출", "진출", "진출"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.apply {
            // Adapter 객체 생성
            val recyclerViewAdapter = RecyclerViewAdapter()
            // Adapter 적용
            recyclerView.adapter = recyclerViewAdapter

            // RecyclerView 의 항목을 보여줄 방식을 설정
            // 1) 세로 방향
            // this = activityMainBinding
             recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            // 2) 가로 방향
            // 마지막 Boolean 값 = 순서를 뒤집을 것이냐
//            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            // 3) 그리드
            // 한 줄에 몇 칸을 사용할 것인가
//            recyclerView.layoutManager = GridLayoutManager(this@MainActivity, 2)
            // 4) 그리드
            // 항목 View의 크기가 다를 경우 GridlayoutManager는 같은 행의 모든 뷰가 같은 크기로 조정됨
            // StaggeredGridLayoutManager는 항목 View의 크기를 필요한 만큼한 사용하고 화면의 빈칸이 없어지도록 함
            // RecyclerView Decoration 사용하지 않는 것을 추천
//            recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
//            recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL)

            // RecyclerView Decoration
            // 각 항목을 구분하기 위한 선
//            val deco = DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)
//            recyclerView.addItemDecoration(deco)
            // 구분선 양쪽 여백 주기 가능 (Material 3)
            val deco = MaterialDividerItemDecoration(this@MainActivity, MaterialDividerItemDecoration.VERTICAL)
            // 구분선 좌측 여백
            deco.dividerInsetStart = 50
            // 구분선 우측 여백
            deco.dividerInsetEnd = 50
            recyclerView.addItemDecoration(deco)
        }
    }

    // Adapter
    // RecyclerView 구성을 위해 필요한 코드가 작성되어 있음
    // 1. 클래스를 작성
//    inner class RecyclerViewAdapter{
//        //
//    }

    // 2. ViewHolder 클래스 작성
    // ViewHolder : View의 id를 가지고 있는 요소
//    inner class RecyclerViewAdapter{
//        // ViewHolder
//        inner class ViewHolderClass(rowBinding: RowBinding) : RecyclerView.ViewHolder(rowBinding.root) {
//            // 매개변수로 들어오는 바인딩 객체를 담을 프로퍼티
//            val rowBinding:RowBinding
//
//            init {
//                this.rowBinding = rowBinding
//            }
//        }
//    }

    // 3. adapter 클래스가 Adapter를 상속 받도록 구현
    // 필요한 메서드 구현
    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderClass>() {
        // ViewHolder
        // binding 객체 가지고 잇음
        inner class ViewHolderClass(rowBinding: RowBinding) : RecyclerView.ViewHolder(rowBinding.root) {
            // 매개변수로 들어오는 바인딩 객체를 담을 프로퍼티
            // 항목 안의 것까지 객체 만들 필요 없음
            // row.xml
            val rowBinding:RowBinding

            init {
                this.rowBinding = rowBinding

                // 항목을 누르면 반응하는 리스너
                // adapterPosition 프로퍼티 : 항목의 순서값
                // 사용자가 터치한 항목이 몇번째 항목인가로 사용함
                this.rowBinding.root.setOnClickListener {
                    activityMainBinding.textView.text = "선택한 항목 : ${textData1[adapterPosition]}"
                }

                // View의 가로 길이를 최대 길이로 맞추기
                this.rowBinding.root.layoutParams = ViewGroup.LayoutParams(
                    // 가로 길이
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    // 세로 길이
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        // RecyclerView를 통해 보여줄 항목 전체의 개수 반환
        override fun getItemCount(): Int {
            return imageRes.size
        }

        // ViewHolder 객체를 생성해 반환함
        // 새롭게 항목이 보여질 때 재사용 가능한 항목이 없다면 이 메서드를 호출함
        // 재사용 가능한 것이 있으면 OS가 직접 onBindViewHolder에 넣어줌 -> 이 메서드 호출 X
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            // View Binding
            val rowBinding = RowBinding.inflate(layoutInflater)
            // View Holder
            val viewHolderClass = ViewHolderClass(rowBinding)

            // 반환
            return viewHolderClass
        }

        // 항목의 View에 보여주고자 하는 데이터 설정
        // 첫 번째 매개변수 : ViewHolder 객체, 재사용 가능한 것이 없다면 onCreatViewHolder 메서드를
        // 호출하고 반환하는 ViewHolder 객체가 들어오고 재사용 가능한 것이 있따면 재사용 가능한 ViewHolder 객체가 들어옴
        // 두 번째 매개변수 : 구성하고자 하는 항목의 순서값(0부터 1씩 증가)
        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.rowBinding.imageViewRow.setImageResource(imageRes[position])
            holder.rowBinding.textViewRow1.text = textData1[position]
            holder.rowBinding.textViewRow2.text = textData2[position]
        }
    }
}