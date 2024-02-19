package kr.co.lion.android39_contentproviderapp2

import android.content.ContentValues
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android39_contentproviderapp2.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.apply {
            button.setOnClickListener {
                // 저장할 데이터 준비
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val now = sdf.format(Date())

                // 저장할 데이트를 ContentValues에 담기
                val cv1 = ContentValues()
                cv1.put("data1", 100)
                cv1.put("data2", 11.11)
                cv1.put("data3", "문자열")
                cv1.put("data4", now)

                // ContentProvider 접근을 위한 이름을 가진 객체 생성
                // ContentProvider 사용을 위해 권한을 부여해야 함
                val uri = Uri.parse("content://kr.co.lion.testprovider")

                // 저장 요청 수행
                // 저장 요청은 OS에 하고, 이 요청을 받은 OS는 해당 Content Provider의 insert 메서드를 호출함
                // Content Provider의 insert 메서드 내에 코드가 구현되어 있는대로 동작함
                contentResolver.insert(uri, cv1)

                textView.text = "저장 완료"
            }

            button2.setOnClickListener {
                val uri = Uri.parse("content://kr.co.lion.testprovider")

                // 데이터 가져오기
                // 두 번째 : 가져올 컬럼 목록, null이면 모두 가져옴
                // 세 번째 : 조건절
                // 네 번째 : 조건절의 ?에 설정된 값 배열
                // 다섯 번째 : 정렬 기준 컬럼 목록
                val cursor = contentResolver.query(uri, null, null, null, null)!!

                while(cursor.moveToNext()){
                    // 컬럼 순서값 가져오기
                    val idx1 = cursor.getColumnIndex("idx")
                    val idx2 = cursor.getColumnIndex("data1")
                    val idx3 = cursor.getColumnIndex("data2")
                    val idx4 = cursor.getColumnIndex("data3")
                    val idx5 = cursor.getColumnIndex("data4")

                    // 데이터 가져오기
                    val  idx = cursor.getInt(idx1)
                    val  data1 = cursor.getInt(idx2)
                    val  data2 = cursor.getDouble(idx3)
                    val  data3 = cursor.getString(idx4)
                    val  data4 = cursor.getString(idx5)

                    textView.apply {
                        text = "idx : ${idx}\n"
                        append("data1 : ${data1}\n")
                        append("data2 : ${data2}\n")
                        append("data3 : ${data3}\n")
                        append("data4 : ${data4}\n")
                    }
                }
            }

            button3.setOnClickListener {
                // 수정할 데이터 담기
                val cv1 = ContentValues()
                cv1.put("data1", 1000)

                // 조건절
                val where = "idx = ?"
                val args = arrayOf("1")

                // 수정
                val uri = Uri.parse("content://kr.co.lion.testprovider")
                contentResolver.update(uri, cv1, where, args)

                textView.text = "수정 완료"
            }

            button4.setOnClickListener {
                // 조건절
                val where = "idx = ?"
                val args = arrayOf("1")

                // 삭제 요청
                val uri = Uri.parse("content://kr.co.lion.testprovider")
                contentResolver.delete(uri, where, args)

                textView.text = "삭제 완료"
            }
        }
    }
}