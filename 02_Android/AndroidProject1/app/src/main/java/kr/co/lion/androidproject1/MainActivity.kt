package kr.co.lion.androidproject1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.androidproject1.databinding.ActivityMainBinding
import kr.co.lion.androidproject1.databinding.RowMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // AnimalRegisterActivity Launcher
    lateinit var animalRegisterActivityResultLauncher: ActivityResultLauncher<Intent>
    // ShowAnimalInfosActivity Launcher
    lateinit var showAnimalInfosActivityLauncher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        setToolbar()
    }

    // 기본 데이터 및 객체 셋팅
    fun initData() {
        // AnimalRegisterActivity Launcher
        val contractRegister = ActivityResultContracts.StartActivityForResult()
        animalRegisterActivityResultLauncher = registerForActivityResult(contractRegister) {
        }

        // ShowAnimalInfosActivity Launcher
        val contractShowInfos = ActivityResultContracts.StartActivityForResult()
        showAnimalInfosActivityLauncher = registerForActivityResult(contractShowInfos) {
        }
    }

    // 툴바
    fun setToolbar() {
        activityMainBinding.apply {
            toolbarMain.apply {
                title = "동물원 관리"
                inflateMenu(R.menu.menu_main)
                setOnMenuItemClickListener {
                    // 메뉴 id로 분기
                    when(it.itemId) {
                        R.id.menuItemMainAdd -> {
                            val registerIntent = Intent(this@MainActivity, AnimalRegisterActivity::class.java)
                            animalRegisterActivityResultLauncher.launch(registerIntent)
                        }
                        R.id.menuItemMainFilter -> {
                        }
                    }
                    true
                }
            }
        }
    }

    // 뷰 설정
    fun setView() {
        activityMainBinding.apply {
            recyclerViewMain.apply {
                adapter = RecyclerViewAdapter()
                layoutManager = LinearLayoutManager(this@MainActivity)
                val deco = MaterialDividerItemDecoration(this@MainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    // RecyclerView
    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderClass>() {
        inner class ViewHolderClass(rowMainBinding: RowMainBinding) : RecyclerView.ViewHolder(rowMainBinding.root) {
            val rowMainBinding : RowMainBinding

            init {
                this.rowMainBinding = rowMainBinding

                // 가로 세로 길이 설정
                this.rowMainBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val rowMainBinding = RowMainBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolderClass(rowMainBinding)

            return viewHolderClass
        }

        override fun getItemCount(): Int {
            return 20
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.rowMainBinding.textViewRowMainName.text = "이름 $position"
            holder.rowMainBinding.textViewRowMainType.text = "종류 $position"
        }
    }
}