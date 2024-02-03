package kr.co.lion.androidproject_memo

import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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
        setView()
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
                            showDialog()
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
            val memoData = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                intent.getParcelableExtra("memoData", MemoClass::class.java)
            } else {
                intent.getParcelableExtra<MemoClass>("memoData")
            }

            // textViewShowTitle
            textViewShowTitle.apply {
                text = memoData?.title
            }
            // textViewShowDate
            textViewShowDate.apply {
                text = memoData?.date
            }
            // textViewShowContext
            textViewShowContext.apply {
                text = memoData?.context
            }
        }
    }

    // 삭제 다이얼로그 메서드
    fun showDialog() {
        val builer = MaterialAlertDialogBuilder(this@ShowActivity).apply {
            setTitle("삭제")
            setMessage("메모를 삭제하겠습니까?")
            setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
                setResult(RESULT_OK)
                finish()
            }
            setNegativeButton("취소") { dialogInterface: DialogInterface, i: Int ->
            }
        }
        builer.show()
    }
}