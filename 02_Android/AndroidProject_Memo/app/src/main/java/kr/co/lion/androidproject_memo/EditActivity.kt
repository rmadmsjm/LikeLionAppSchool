package kr.co.lion.androidproject_memo

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.androidproject_memo.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {

    lateinit var activityEditBinding: ActivityEditBinding

    // adapterPosition
    var adapterPosition:Int = -1

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
                        // 메모 수정 완료
                        R.id.menuItemEditSubmit -> {
                            editData()
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
            adapterPosition = intent.getIntExtra("adapterPosition", -1)

            // textFieldEditTitle
            textFieldEditTitle.apply {
                setText(Util.memoDataList[adapterPosition].title)
            }
            // textFieldEditContext
            textFieldEditContext.apply {
                setText(Util.memoDataList[adapterPosition].context)
            }
        }
    }

    // 메모 수정 메서드
    fun editData() {
        activityEditBinding.apply {
            val memoData = Util.memoDataList[adapterPosition]

            memoData.title = textFieldEditTitle.text.toString()
            memoData.context = textFieldEditContext.text.toString()

            // 데이터 유효성 검사
            if(memoData.title!!.isEmpty()) {
                textFieldLayoutEditTitle.error = "제목을 입력해 주세요"
                Util.showSoftInput(this@EditActivity, textFieldEditTitle)
                if(Util.memoDataList[adapterPosition].context!!.isEmpty()) {
                    textFieldLayoutEditContext.error = "내용을 입력해 주세요"
                }
                return
            }
            if(memoData.context!!.isEmpty()) {
                textFieldLayoutEditContext.error = "내용을 입력해 주세요"
                Util.showSoftInput(this@EditActivity, textFieldEditContext)
                return
            }

            setResult(RESULT_OK)
            finish()
        }
    }
}