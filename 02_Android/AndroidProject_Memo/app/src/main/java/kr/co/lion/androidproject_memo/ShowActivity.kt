package kr.co.lion.androidproject_memo

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import kr.co.lion.androidproject_memo.databinding.ActivityShowBinding

class ShowActivity : AppCompatActivity() {

    lateinit var activityShowBinding: ActivityShowBinding

    // EditActivity Launcher
    lateinit var editActivityLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityShowBinding = ActivityShowBinding.inflate(layoutInflater)
        setContentView(activityShowBinding.root)

        initData()
        setToolbar()
    }

    // 기본 데이터 및 객체 세팅
    fun initData() {
        // EditActivity Launcher
        val editContract = ActivityResultContracts.StartActivityForResult()
        editActivityLauncher = registerForActivityResult(editContract) {
        }
    }

    // Toolbar
    fun setToolbar() {
        activityShowBinding.apply {
            toolbarShow.apply {
                // 타이틀
                title = "메모 보기"

                // back button
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }

                // 메뉴 설정
                inflateMenu(R.menu.menu_show)
                // 메뉴 리스너
                setOnMenuItemClickListener {
                    // 메뉴 id로 분기
                    when(it.itemId) {
                        R.id.menuItemShowEdit -> {
                            val editIntent = Intent(this@ShowActivity, EditActivity::class.java)
                            editActivityLauncher.launch(editIntent)
                        }
                        R.id.menuItemShowDelete -> {
                        }
                    }

                    true
                }
            }
        }
    }

    // View 설정
    fun setView() {
        activityShowBinding.apply {
            // Intent로부터 메모 데이터 객체 추출
            val intentData = intent.extras
            if (intentData != null && intentData.containsKey("memoData")) {
                val memoData = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    intent.getParcelableExtra("memoData", MemoClass::class.java)
                } else {
                    intent.getParcelableExtra<MemoClass>("memoData")
                }

                Log.d("ShowActivity", "Title: ${memoData?.title}, Date: ${memoData?.date}, Context: ${memoData?.context}")

                textViewShowTitle.text = memoData?.title
                textViewShowDate.text = memoData?.date
                textViewShowContext.text = memoData?.context
            } else {
                Snackbar.make(activityShowBinding.root, "메모 데이터가 없습니다", Snackbar.LENGTH_SHORT).show()            }

//            // textViewShowTitle
//            textViewShowTitle.apply {
//                text = memoData?.title
//            }
//            // textViewShowDate
//            textViewShowDate.apply {
//                text = memoData?.date
//            }
//            // textViewShowContext
//            textViewShowContext.apply {
//                text = memoData?.context
//            }
        }
    }
}