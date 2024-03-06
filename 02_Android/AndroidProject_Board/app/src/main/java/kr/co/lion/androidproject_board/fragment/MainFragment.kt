package kr.co.lion.androidproject_board.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.androidproject_board.ContentActivity
import kr.co.lion.androidproject_board.ContentFragmentName
import kr.co.lion.androidproject_board.R
import kr.co.lion.androidproject_board.databinding.FragmentMainBinding
import kr.co.lion.androidproject_board.databinding.RowMainBinding

class MainFragment : Fragment() {

    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var contentActivity: ContentActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentMainBinding = FragmentMainBinding.inflate(inflater)
        contentActivity = activity as ContentActivity

        settingToolbar()
        settingSearchBar()
        settingRecyclerViewMain()
        settingRecyclerViewMainSearch()

        return fragmentMainBinding.root
    }

    // 툴바 설정
    fun settingToolbar() {
        fragmentMainBinding.apply {
            toolbarMain.apply {
                title = "전체 게시판"

                setNavigationIcon(R.drawable.menu_24px)
                setNavigationOnClickListener {
                    // NavigationDrawer
                    contentActivity.activityContentBinding.drawerLayoutContent.open()
                }
            }
        }
    }

    // searchBar 설정
    fun settingSearchBar() {
        fragmentMainBinding.apply {
            searchBarMain.apply {
                hint = "여기를 눌러 검색해주세요"

                inflateMenu(R.menu.menu_main)
                setOnMenuItemClickListener {
                    when(it.itemId) {
                        R.id.menuItemMainAdd -> {
                            contentActivity.replaceFragment(ContentFragmentName.ADD_CONTENT_FRAGMENT, true, true, null)
                        }
                    }
                    true
                }
            }

            searchViewMain.apply {
                hint = "검색어를 입력해주세요"
            }
        }
    }

    // 메인 화면 RecyclerView 설정
    fun settingRecyclerViewMain() {
        fragmentMainBinding.apply {
            recyclerViewMain.apply {
                adapter = MainRecyclerViewAdapter()
                layoutManager = LinearLayoutManager(contentActivity)
            }
        }
    }

    // 검색 화면 RecyclerView 설정
    fun settingRecyclerViewMainSearch() {
        fragmentMainBinding.apply {
            recyclerViewMainSearch.apply {
                adapter = SearchRecyclerViewAdapter()
                layoutManager = LinearLayoutManager(contentActivity)
            }
        }
    }

    // 메인 화면 RecyclerView Adapter
    inner class MainRecyclerViewAdapter() : RecyclerView.Adapter<MainRecyclerViewAdapter.MainViewHolder>() {
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
            return 10
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            holder.rowMainBinding.textViewRowMainTitle.text = "제목 $position"
            holder.rowMainBinding.textViewRowMainUserNickname.text = "작성자 $position"
        }
    }

    // 검색 화면 RecyclerView Adapter
    inner class SearchRecyclerViewAdapter() : RecyclerView.Adapter<SearchRecyclerViewAdapter.SearchViewHolder>() {
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
            return 10
        }

        override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
            holder.rowMainBinding.textViewRowMainTitle.text = "제목 $position"
            holder.rowMainBinding.textViewRowMainUserNickname.text = "작성자 $position"
        }
    }
}