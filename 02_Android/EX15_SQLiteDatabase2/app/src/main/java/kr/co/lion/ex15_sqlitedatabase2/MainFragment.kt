package kr.co.lion.ex15_sqlitedatabase2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.ex15_sqlitedatabase2.databinding.FragmentMainBinding
import kr.co.lion.ex15_sqlitedatabase2.databinding.RowMainBinding

class MainFragment : Fragment() {

    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActivity: MainActivity

    // Dao가 반환하는 학생 객체를 담을 리스트
    lateinit var studentList: MutableList<StudentModel>

    // onCreate() : Fragment 객체 생성 시 한 번만 호출됨
    // onCreateView() : Fragment 화면이 보일 때마다 호출됨
    // Fragment를 제거했다 붙이기 때문에 onCreateView() 안에 구현해도 됨
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentMainBinding = FragmentMainBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingToolbar()
        settingVeiw()

        return fragmentMainBinding.root
    }

    // Toolbar 설정
    fun settingToolbar() {
        fragmentMainBinding.apply {
            toolbarMain.apply {
                // 타이틀
                title = "학생 관리"
                // 메뉴
                inflateMenu(R.menu.menu_main)
                // 메뉴 리스너
                setOnMenuItemClickListener {
                    // 메뉴 id로 분기
                    when(it.itemId) {
                        R.id.menuItemMainAdd -> {
                            // InputFragment 실행
                            mainActivity.replaceFragment(FragmentName.INPUT_FRAGMENT, true, true, null)
                        }
                    }
                    true
                }
            }
        }
    }

    // View 설정
    fun settingVeiw() {
        fragmentMainBinding.apply {
            // RecyclerView
            recyclerViewMain.apply {
                //  RecyclerView 구성을 위한 리스트
                studentList = StudentDao.selectAllStudent(mainActivity)
                // 어뎁터
                adapter = RecyclerViewMainAdapter()
                // 레이아웃 매니저
                layoutManager = LinearLayoutManager(mainActivity)
                // 데코레이션
                val deco = MaterialDividerItemDecoration(mainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    // RecyclerView의 어뎁터
    inner class RecyclerViewMainAdapter : RecyclerView.Adapter<RecyclerViewMainAdapter.ViewHolderMain>() {
        // ViewHolder
        inner class ViewHolderMain(rowMainBinding: RowMainBinding) : RecyclerView.ViewHolder(rowMainBinding.root) {
            val rowMainBinding: RowMainBinding

            init {
                this.rowMainBinding = rowMainBinding
                this.rowMainBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        // 재사용 가능한 View가 없을 때 호출됨
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMain {
            val rowMainBinding = RowMainBinding.inflate(layoutInflater)
            val viewHolderMain = ViewHolderMain(rowMainBinding)

            return viewHolderMain
        }

        override fun getItemCount(): Int {
            return studentList.size
        }

        // 항목이 보이면 무조건 호출됨
        override fun onBindViewHolder(holder: ViewHolderMain, position: Int) {
            holder.rowMainBinding.textViewRowMainName.text = studentList[position].name

            // 항목 리스너
            holder.rowMainBinding.root.setOnClickListener {
                val showBundle = Bundle()
                showBundle.putInt("idx", studentList[position].idx)

                // ShowFragment 실행
                mainActivity.replaceFragment(FragmentName.SHOW_FRAGMENT, true, true, showBundle)
            }
        }
    }
}