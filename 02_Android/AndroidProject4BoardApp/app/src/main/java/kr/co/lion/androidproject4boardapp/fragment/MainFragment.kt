package kr.co.lion.androidproject4boardapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.androidproject4boardapp.ContentActivity
import kr.co.lion.androidproject4boardapp.ContentFragmentName
import kr.co.lion.androidproject4boardapp.R
import kr.co.lion.androidproject4boardapp.databinding.FragmentMainBinding
import kr.co.lion.androidproject4boardapp.databinding.RowMainBinding

class MainFragment : Fragment() {

    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var contentActivity: ContentActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentMainBinding = FragmentMainBinding.inflate(inflater)
        contentActivity = activity as ContentActivity

        settingToolbar()
        settingSearchBar()
        settingRecycelrViewMain()
        settingRecycelrViewMainSearch()

        return fragmentMainBinding.root
    }

    // 툴바 설정
    fun settingToolbar() {
        fragmentMainBinding.apply {
            toolbarMain.apply {
                // 타이틀
                title = "전체 게시판"

                // 네비게이션
                setNavigationIcon(R.drawable.menu_24px)
                setNavigationOnClickListener {
                    // Drawer 메뉴가 나타나게 하기
                    contentActivity.activityContentBinding.drawerLayoutContent.open()
                }
            }
        }
    }

    // SearchBar 설정
    fun settingSearchBar() {
        fragmentMainBinding.apply {
            searchBarMain.apply {
                // searchBar에 보여줄 메시지
                hint = "여기를 눌러 검색해주세요"

                // 메뉴
                inflateMenu(R.menu.menu_main_search)
                setOnMenuItemClickListener {
                    // 메뉴 항목 id로 분기
                    when(it.itemId) {
                        R.id.menuItemMainSearchAdd -> {
                            // 게시글 작성 화면 나타나게 하기
                            contentActivity.replaceFragment(ContentFragmentName.ADD_CONTENT_FRAGMENT, true, true, null)
                        }
                    }

                    true
                }
            }

            searchViewMain.apply {
                // SearchView에 보여줄 메시지
                hint = "검색어를 입력해주세요"
            }
        }
    }

    // 메인 화면의 RecycelrView 설정
    fun settingRecycelrViewMain() {
        fragmentMainBinding.apply {
            recyclerViewMain.apply {
                adapter = MainRecyclerViewAdapter()
                layoutManager = LinearLayoutManager(contentActivity)
                val deco = MaterialDividerItemDecoration(contentActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    // 검색 화면의 RecycelrView 설정
    fun settingRecycelrViewMainSearch() {
        fragmentMainBinding.apply {
            recyclerViewMainSearch.apply {
                adapter = SearchRecyclerViewAdapter()
                layoutManager = LinearLayoutManager(contentActivity)
                val deco = MaterialDividerItemDecoration(contentActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    // 메인 화면의 RecyclerView의 Adapter
    inner class MainRecyclerViewAdapter : RecyclerView.Adapter<MainRecyclerViewAdapter.MainViewHolder>() {
        inner class MainViewHolder(rowMainBinding: RowMainBinding) : RecyclerView.ViewHolder(rowMainBinding.root) {
            val rowMainBinding: RowMainBinding

            init {
                this.rowMainBinding = rowMainBinding

                this.rowMainBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val rowMainBinding = RowMainBinding.inflate(layoutInflater)
            val mainViewHolder = MainViewHolder(rowMainBinding)

            return mainViewHolder
        }

        override fun getItemCount(): Int {
            return 100
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            holder.rowMainBinding.textViewRowMainSubject.text = "제목 $position"
            holder.rowMainBinding.textViewRowMainNicname.text = "작성자 $position"
        }
    }

    // 검색 화면의 RecyclerView의 Adapter
    inner class SearchRecyclerViewAdapter : RecyclerView.Adapter<SearchRecyclerViewAdapter.SearchViewHolder>() {
        inner class SearchViewHolder(rowMainBinding: RowMainBinding) : RecyclerView.ViewHolder(rowMainBinding.root) {
            val rowMainBinding: RowMainBinding

            init {
                this.rowMainBinding = rowMainBinding

                this.rowMainBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
            val rowMainBinding = RowMainBinding.inflate(layoutInflater)
            val searchViewHolder = SearchViewHolder(rowMainBinding)

            return searchViewHolder
        }

        override fun getItemCount(): Int {
            return 100
        }

        override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
            holder.rowMainBinding.textViewRowMainSubject.text = "제목 $position"
            holder.rowMainBinding.textViewRowMainNicname.text = "작성자 $position"
        }
    }
}