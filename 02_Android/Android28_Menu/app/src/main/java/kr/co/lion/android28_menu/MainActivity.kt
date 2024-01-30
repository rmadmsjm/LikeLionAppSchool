package kr.co.lion.android28_menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.android28_menu.databinding.ActivityMainBinding
import kr.co.lion.android28_menu.databinding.RowBinding

// Context Menu : View를 길게 누르면 나타나는 메뉴, 직관적이지 못 함
// Popup Menu : 개발자가 코드를 통해 원하는 View에 띄우는 메뉴

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.apply {
            recyclerView.apply {
                adapter = RecyclerViewAdapter()
                layoutManager = LinearLayoutManager(this@MainActivity)
            }

            // textView의 Context Menu
            // 첫 번째 : 메뉴를 구성하기 위해 사용할 메뉴 객체
            textView.setOnCreateContextMenuListener { menu, v, menuInfo ->
                // 메뉴의 헤더
                menu?.setHeaderTitle("textView의 메뉴")
                // 메뉴를 구성
                menuInflater.inflate(R.menu.textview_menu, menu)

                // 각 메뉴 item을 추출해 리스너 설정 -> ⭐ menu의 item에 리스너 설정
                menu?.findItem(R.id.textviewMenuItem1)?.setOnMenuItemClickListener {
                    textView.text = "TextView의 메뉴 항목1 선택"
                    true
                }
                menu?.findItem(R.id.textviewMenuItem2)?.setOnMenuItemClickListener {
                    textView.text = "TextView의 메뉴 항목2 선택"
                    true
                }
            }

            // Popup Menu
            button.setOnClickListener {
                // 팝업 메뉴 생성
                // 두 번째 매개 변수 : 메뉴를 띄울 View 지정
                val popupMenu = PopupMenu(this@MainActivity, textView)
                // 메뉴 구성
                menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
                // 메뉴 항목을 눌렀을 때 동작할 리스너 설정 -> ⭐ menu에 리스너 설정
                popupMenu.setOnMenuItemClickListener {
                    // 메뉴 항목의 id로 분기
                    when(it.itemId) {
                        R.id.popupMenuItem1 -> textView.text = "팝업 메뉴1을 선택"
                        R.id.popupMenuItem2 -> textView.text = "팝업 메뉴2를 선택"
                    }
                    true
                }
                // 메뉴 보여주기
                popupMenu.show()
            }
        }
    }

    // RecyclerView의 어뎁터
    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderClass>(){

        // ViewHolder
        inner class ViewHolderClass(rowBinding: RowBinding) : RecyclerView.ViewHolder(rowBinding.root){
            val rowBinding : RowBinding

            init {
                this.rowBinding = rowBinding

                rowBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                // RecyclerView 항목에 context menu 설정
                rowBinding.root.setOnCreateContextMenuListener { menu, v, menuInfo ->
                    // 메뉴의 헤더
                    menu?.setHeaderTitle("${adapterPosition}번째 항목의 메뉴")
                    // 메뉴를 구성
                    menuInflater.inflate(R.menu.recyclerview_menu, menu)

                    // 각 메뉴 item을 추출해 리스너 설정 -> ⭐ menu의 item에 리스너 설정
                    menu?.findItem(R.id.recyclerviewMenuItem1)?.setOnMenuItemClickListener {
                        activityMainBinding.textView.text = "${adapterPosition}번째 항목의 메뉴1 선택"
                        true
                    }
                    menu?.findItem(R.id.recyclerviewMenuItem2)?.setOnMenuItemClickListener {
                        activityMainBinding.textView.text = "${adapterPosition}번째 항목의 메뉴2 선택"
                        true
                    }
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val rowBinding = RowBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolderClass(rowBinding)

            return viewHolderClass
        }

        override fun getItemCount(): Int {
            return 20
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.rowBinding.textViewRow.text = "항목 $position"
        }
    }

}