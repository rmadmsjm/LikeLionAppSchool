package kr.co.lion.androidproject4boardapp.fragment

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.KeyEvent
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
import kr.co.lion.androidproject4boardapp.MainActivity
import kr.co.lion.androidproject4boardapp.R
import kr.co.lion.androidproject4boardapp.Tools
import kr.co.lion.androidproject4boardapp.dao.ContentDao
import kr.co.lion.androidproject4boardapp.dao.UserDao
import kr.co.lion.androidproject4boardapp.databinding.ActivityMainBinding
import kr.co.lion.androidproject4boardapp.databinding.FragmentMainBinding
import kr.co.lion.androidproject4boardapp.databinding.RowMainBinding
import kr.co.lion.androidproject4boardapp.model.ContentModel
import kr.co.lion.androidproject4boardapp.model.UserModel

class MainFragment : Fragment() {

    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var contentActivity: ContentActivity

    // 메인 화면의 RecyclerView 구성을 위한 리스트
    var mainList = mutableListOf<ContentModel>()
    // 검색 화면의 RecyclerView 구성을 위한 리스트
    var searchList = mutableListOf<ContentModel>()
    // 사용자 정보를 담고 있을 리스트
    var userList = mutableListOf<UserModel>()

    // 게시판 종류를 담을 프로퍼티
    var contentType = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentMainBinding = FragmentMainBinding.inflate(inflater)
        contentActivity = activity as ContentActivity

        // 게시판 종류값을 담아준다.
        contentType = arguments?.getInt("TypeNumber")!!

        settingToolbar()
        settingSearchBar()
        settingRecyclerViewMain()
        settingRecyclerViewMainSearch()
        gettingMainData()

        return fragmentMainBinding.root
    }

    // 툴바 설정
    fun settingToolbar(){
        fragmentMainBinding.apply {
            toolbarMain.apply {
                // 타이틀
                title = arguments?.getString("TypeName")
                // 네비게이션
                setNavigationIcon(R.drawable.menu_24px)
                setNavigationOnClickListener {
                    // Drawer 메뉴가 나타나게 한다.
                    contentActivity.activityContentBinding.drawerLayoutContent.open()
                }
            }
        }
    }
    
    // SearchView 설정
    fun settingSearchBar(){
        fragmentMainBinding.apply { 
            searchBarMain.apply { 
                // SearchBar에 보여줄 메시지
                hint = "여기를 눌러 검색해주세요"
                // 메뉴
                inflateMenu(R.menu.menu_main_search_menu)
                setOnMenuItemClickListener {
                    // 글 작성 화면이 나타나게 한다.
                    contentActivity.replaceFragment(ContentFragmentName.ADD_CONTENT_FRAGMENT, true, true, null)
                    true
                }
            }
            
            searchViewMain.apply { 
                // SearchView에 보여줄 메시지
                hint = "검색어를 입력해주세요"
                // 검색시 사용하는 키보드의 엔터키를 누르면 동작하는 리스너
                editText.setOnEditorActionListener { v, actionId, event ->
                    if(event != null && event.action == KeyEvent.ACTION_DOWN) {
                        // 검색 결과를 가져와 보여주는 메서드를 호출한다.
                        gettingSearchData()
                    }

                    false
                }
            }
        }
    }

    // 메인 화면의 RecyclerView 설정
    fun settingRecyclerViewMain(){
        fragmentMainBinding.apply {
            recyclerViewMain.apply {
                // 어뎁터
                adapter = MainRecyclerViewAdapter()
                // 레이아웃 매니저
                layoutManager = LinearLayoutManager(contentActivity)
                // 데코레이션
                val deco = MaterialDividerItemDecoration(contentActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    // 검색 화면의 RecyclerView를 구성하는 메서드
    fun settingRecyclerViewMainSearch(){
        fragmentMainBinding.apply {
            recyclerViewMainSearch.apply {
                // 어뎁터
                adapter = SearchRecyclerViewAdapter()
                // 레이아웃 매니저
                layoutManager = LinearLayoutManager(contentActivity)
                // 데코레이션
                val deco = MaterialDividerItemDecoration(contentActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    // 메인 화면의 RecyclerView의 어뎁터
    inner class MainRecyclerViewAdapter : RecyclerView.Adapter<MainRecyclerViewAdapter.MainViewHolder>(){
        inner class MainViewHolder(rowMainBinding: RowMainBinding) : RecyclerView.ViewHolder(rowMainBinding.root){
            val rowMainBinding:RowMainBinding

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

            // 사용자의 수 만큼 반복한다.
            userList.forEach {
                // 사용자 번호와 작성자 번호가 같으면 출력하고 중단한다.
                if(it.userIdx == mainList[position].contentWriterIdx) {
                    holder.rowMainBinding.textViewRowMainNickName.text = it.userNickName
                    return@forEach
                }
            }

            // 항목을 눌렀을 때 동작할 리스너를 연결해준다.
            holder.rowMainBinding.root.setOnClickListener {
                // 필요한 데이터를 담아준다.
                val readBundle = Bundle()
                readBundle.putInt("contentIdx", mainList[position].contentIdx)
                // 글 읽는 화면으로 이동한다.
                contentActivity.replaceFragment(ContentFragmentName.READ_CONTENT_FRAGMENT, true, true, readBundle)
            }
        }
    }

    // 검색 화면의 RecyclerView의 어뎁터
    inner class SearchRecyclerViewAdapter : RecyclerView.Adapter<SearchRecyclerViewAdapter.SearchViewHolder>(){
        inner class SearchViewHolder(rowMainBinding: RowMainBinding) : RecyclerView.ViewHolder(rowMainBinding.root){
            val rowMainBinding:RowMainBinding

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
            return searchList.size
        }

        override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
            holder.rowMainBinding.textViewRowMainSubject.text = searchList[position].contentSubject

            // 사용자의 수 만큼 반복한다.
            userList.forEach {
                // 사용자 번호와 작성자 번호가 같으면 출력하고 중단한다.
                if(it.userIdx == searchList[position].contentWriterIdx) {
                    holder.rowMainBinding.textViewRowMainNickName.text = it.userNickName
                    return@forEach
                }
            }

            // 항목을 눌렀을 때 동작할 리스너를 연결해준다.
            holder.rowMainBinding.root.setOnClickListener {
                // 필요한 데이터를 담아준다.
                val readBundle = Bundle()
                readBundle.putInt("contentIdx", searchList[position].contentIdx)
                // 글 읽는 화면으로 이동한다.
                contentActivity.replaceFragment(ContentFragmentName.READ_CONTENT_FRAGMENT, true, true, readBundle)
            }
        }
    }

    // 현재 게시판의 데이터를 가져와 메인 화면의 리사이클러뷰를 갱신한다.
    fun gettingMainData(){
        CoroutineScope(Dispatchers.Main).launch {
            // 서버에서 데이터를 가져온다.
            mainList = ContentDao.gettingContentList(contentType)
            // 사용자 정보를 가져온다.
            userList = UserDao.getUserAll()
            // 리사이클러 뷰를 갱신한다.
            fragmentMainBinding.recyclerViewMain.adapter?.notifyDataSetChanged()
        }
    }

    // 검색 결과의 데이터를 가져와 검색 화면의 리사이클러뷰를 갱신한다.
    fun gettingSearchData(){
        // 검색어를 가져온다.
        // SearchView에 있는 입력 요소(editText)를 추출하여 사용자가 입력한 내용을 가져온다
        val keyword = fragmentMainBinding.searchViewMain.editText.text.toString()

        // 검색 결과를 가지고 있는 리스트를 비워준다.
        searchList.clear()

        CoroutineScope(Dispatchers.Main).launch {
            // 현재 게시판에 해당하는 게시글을 모두 가져온다.
            val tempList = ContentDao.gettingContentList(contentType)
            // 사용자 정보를 가져온다.
            userList = UserDao.getUserAll()
            // 가져온 게시글 데이터 중에서 검색어를 포함하는 제목의 글 데이터만 담는다.
            tempList.forEach {
                if(it.contentSubject.contains(keyword)){
                    // 검색 결과를 담는 리스트에 담아준다.
                    searchList.add(it)
                }
            }

            // 리사이클러 뷰를 갱신한다.
            fragmentMainBinding.recyclerViewMainSearch.adapter?.notifyDataSetChanged()
        }
    }
}







