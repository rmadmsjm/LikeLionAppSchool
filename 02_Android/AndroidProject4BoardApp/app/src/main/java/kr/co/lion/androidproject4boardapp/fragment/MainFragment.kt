package kr.co.lion.androidproject4boardapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.androidproject4boardapp.ContentActivity
import kr.co.lion.androidproject4boardapp.ContentFragmentName
import kr.co.lion.androidproject4boardapp.R
import kr.co.lion.androidproject4boardapp.dao.ContentDao
import kr.co.lion.androidproject4boardapp.dao.UserDao
import kr.co.lion.androidproject4boardapp.databinding.FragmentMainBinding
import kr.co.lion.androidproject4boardapp.databinding.RowMainBinding
import kr.co.lion.androidproject4boardapp.model.ContentModel
import kr.co.lion.androidproject4boardapp.model.UserModel

class MainFragment : Fragment() {

    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var contentActivity: ContentActivity

    // 메인 화면의 RecyclerView 구성을 위한 리스트
    var mainList = mutableListOf<ContentModel>()
    // 검색 화면의 ReycyclerView 구성을 위한 리스트
    var searchList = mutableListOf<ContentModel>()
    // 사용자 정보를 담을 리스트
    var userList = mutableListOf<UserModel>()

    // 게시판 종류를 담을 프로퍼티
    var contentType = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentMainBinding = FragmentMainBinding.inflate(inflater)
        contentActivity = activity as ContentActivity

        // 게시판 종류 값 담기
        contentType = arguments?.getInt("typeNum")!!

        settingToolbar()
        settingSearchBar()
        settingRecycelrViewMain()
        settingRecycelrViewMainSearch()
        gettingMainData()

        /*
        ContentDao의 게시글 목록 가져오기에서 firebase index 설정하기 위해 실행하려고 추가했던 코드
        CoroutineScope(Dispatchers.Main).launch {
            ContentDao.gettingContentList(0)
        }
         */

        return fragmentMainBinding.root
    }

    // 툴바 설정
    fun settingToolbar() {
        fragmentMainBinding.apply {
            toolbarMain.apply {
                // 타이틀
                title = arguments?.getString("typeName")

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
            return mainList.size
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            holder.rowMainBinding.textViewRowMainSubject.text = mainList[position].contentSubject

            // 사용자의 수만큼 반복
            userList.forEach {
                // 사용자 번호와 작성자 번호가 같으면 출력하고 중단
                if(it.userIdx == mainList[position].contentWriterIdx) {
                    holder.rowMainBinding.textViewRowMainNicname.text = it.userNickname
                    return@forEach
                }
            }

            // 항목을 눌렀을 때 동작할 리스너 연결
            holder.rowMainBinding.root.setOnClickListener {
                // 필요한 데이터 담기
                val readBundle = Bundle()
                readBundle.putInt("contentIdx", mainList[position].contentIdx)
                // 글 읽는 화면으로 이동
                contentActivity.replaceFragment(ContentFragmentName.READ_CONTENT_FRAGMENT, true, true, readBundle)
            }
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

    // 서버에서 현재 게시판의 데이터를 가져와 메인 화면의 RecyclerView 갱신하기
    fun gettingMainData() {
        CoroutineScope(Dispatchers.Main).launch {
            // 서버에서 데이터 가져오기
            mainList = ContentDao.gettingContentList(contentType)
            // 사용자 정보 가져오기
            userList = UserDao.getUserAll()
            // RecyclerView 갱신
            fragmentMainBinding.recyclerViewMain.adapter?.notifyDataSetChanged()
        }
    }
}