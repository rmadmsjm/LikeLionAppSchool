package kr.co.lion.androidproject_memo

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.androidproject_memo.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {

    lateinit var activityEditBinding: ActivityEditBinding

    lateinit var editMemoData: MemoClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityEditBinding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(activityEditBinding.root)

        setToolbar()
        setView()
    }

    // Toolbar
    fun setToolbar() {
        activityEditBinding.apply {
            toolbarEdit.apply {
                // 타이틀
                title = "메모 수정"

                // back button
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }

                // 메뉴 설정
                inflateMenu(R.menu.menu_edit)
                // 메뉴 리스너
                setOnMenuItemClickListener {
                    // 메뉴 id로 분기
                    when(it.itemId) {
                        R.id.menuItemEditSubmit -> {
                            editData()
                            val editIntent = Intent()
                            editIntent.putExtra("editMemoData", editMemoData)
                            setResult(RESULT_OK, editIntent)
                            finish()
                        }
                    }

                    true
                }
            }
        }
    }

    // View 설정
    fun setView() {
        activityEditBinding.apply {
            // Intent로부터 메모 데이터 객체 추출
            editMemoData = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra("memoData", MemoClass::class.java)!!
            } else {
                intent.getParcelableExtra<MemoClass>("memoData")!!
            }

            // textFieldEditTitle
            textFieldEditTitle.apply {
                setText(editMemoData.title)
            }
            // textFieldEditContext
            textFieldEditContext.apply {
                setText(editMemoData.context)
            }
        }
    }

    // 메모 수정 메서드
    fun editData() {
        activityEditBinding.apply {
            editMemoData.title = textFieldEditTitle.text.toString()
            editMemoData.context = textFieldEditContext.text.toString()
        }
    }
}