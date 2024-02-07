package kr.co.lion.androidproject1test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.androidproject1test.databinding.ActivityMainBinding
import kr.co.lion.androidproject1test.databinding.RowMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // InputActivity Launcher
    lateinit var inputActivityLaucher : ActivityResultLauncher<Intent>
    // ShowActivity Launcher
    lateinit var showActivityLauncher : ActivityResultLauncher<Intent>

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
            return Util.animalList.size
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
            val animal = Util.animalList[position]

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
                showIntnet.putExtra("position", position)
                showActivityLauncher.launch(showIntnet)
            }
        }
    }
}