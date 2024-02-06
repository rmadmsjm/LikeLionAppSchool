package kr.co.lion.androidproject_memo

import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kr.co.lion.androidproject_memo.databinding.ActivityShowBinding

class ShowActivity : AppCompatActivity() {

    lateinit var activityShowBinding: ActivityShowBinding

    // EditActivity Launcher
    lateinit var editActivityLauncher: ActivityResultLauncher<Intent>

    lateinit var memoData: MemoClass

    var editState = false

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
            // resultCode에 따라 분기
            when(it.resultCode) {
                RESULT_OK -> {
                    // 전달된 Intent객체가 있을 때 객체 추출
                    if(it.data != null) {
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            memoData = it.data?.getParcelableExtra("editMemoData", MemoClass::class.java)!!

                            activityShowBinding.apply {
                                textViewShowTitle.text = memoData.title.toString()
                                textViewShowContext.text = memoData.context.toString()
                            }

                            editState = true
                        } else {
                            memoData = it.data?.getParcelableExtra<MemoClass>("editMemoData")!!

                            activityShowBinding.apply {
                                textViewShowTitle.text = memoData.title.toString()
                                textViewShowContext.text = memoData.context.toString()
                            }

                            editState = true
                        }
                    } else {
                        Snackbar.make(activityShowBinding.root, "수정 메모 데이터가 없습니다", Snackbar.LENGTH_SHORT)
                    }
                }
                RESULT_CANCELED -> {
                    Snackbar.make(activityShowBinding.root, "메모 수정을 취소했습니다", Snackbar.LENGTH_SHORT)
                }
            }
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
                }

                // 메뉴 설정
                inflateMenu(R.menu.menu_show)
                // 메뉴 리스너
                setOnMenuItemClickListener {
                    // 메뉴 id로 분기
                    when(it.itemId) {
                        R.id.menuItemShowEdit -> {
                            val editIntent = Intent(this@ShowActivity, EditActivity::class.java)
                            editIntent.putExtra("editMemoData", memoData)
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
            memoData = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra("memoData", MemoClass::class.java)!!
            } else {
                intent.getParcelableExtra<MemoClass>("memoData")!!
            }

            // textViewShowTitle
            textViewShowTitle.apply {
                text = memoData.title
            }
            // textViewShowDate
            textViewShowDate.apply {
                text = memoData.date
            }
            // textViewShowContext
            textViewShowContext.apply {
                text = memoData.context
            }
        }
    }

    // 삭제 다이얼로그 메서드
    fun showDialog() {
        val builder = MaterialAlertDialogBuilder(this@ShowActivity).apply {
            setTitle("삭제")
            setMessage("메모를 삭제하겠습니까?")
            setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
                val deleteIntent = Intent()
                deleteIntent.putExtra("memoData", memoData)
                setResult(RESULT_OK, deleteIntent)
                finish()
            }
            setNegativeButton("취소") { dialogInterface: DialogInterface, i: Int ->
                dialogInterface.dismiss()
            }
        }
        builder.show()
    }
}