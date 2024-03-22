package kr.co.lion.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kr.co.lion.roomdatabase.databinding.ActivityMainBinding
import java.sql.DatabaseMetaData

// Room DataBase
// SQLite 데이터베이스 사용시 프로그래밍을 보다 간단하게 할 수 있도록 제공되는 라이브러리
// 구글에서 정한 규격대로 프로그래밍을 하게 되면 SQLite 데이터 베이스를 사용하는 코드를
// 자동으로 만들어준다.

// 1. build.grade.kts 에 라이브러리 설정
// - 상단 plugins
//   kotlin("kapt")
// - 하단 dependencies
//    implementation("androidx.room:room-runtime:2.6.1")
//    annotationProcessor("androidx.room:room-compiler:2.6.1")
//    kapt("androidx.room:room-compiler:2.6.1")

// 2. Entity 를 작성해준다.
// Entity 는 데이터 베이스에 저장된 데이터를 담거나 저장할 데이터를 담을 모델에 해당한다.
// RoomDatabase는 Entiry에 작성한 내용을 기반으로 테이블을 생성해준다.
// TestModel.kt

// 3. Dao를 작성해준다.
// Dao는 Database Access Object의 약자. 데이터베이스에 접속해서 데이터를 읽고 쓰는 작업을
// 수행한다.
// TestDao.kt

// 4. DataBase를 작성해준다.
// SQLiteOpenHelper의 역할을 해준다고 생각하면 된다.
// 추상클래스로 만들어야 하고 이 클래스를 상속받아 필요한 기능들은 구현한 클래스가
// 자동 생성된다.
// TestDatabase.kt

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.apply {

            button.setOnClickListener {
                // 데이터 베이스 접속
                val testDatabase = TestDatabase.getInstance(this@MainActivity)
                // 저장할 데이터를 담는다.
                // primary key로 지정된 프로퍼티는 1 부터 1씩 증가되는 값이 자동으로 부여되기 때문에
                // 이 프로퍼티는 제외한다.
                val testModel1 = TestModel(testData1 = "문자열1", testData2 = 11.11)
                // 저장한다.
                CoroutineScope(Dispatchers.Main).launch {
                    async(Dispatchers.IO) {
                        testDatabase?.testDao()?.insertData(testModel1)
                    }
                }
                
                val testModel2 = TestModel(testData1 = "문자열2", testData2 = 22.22)
                CoroutineScope(Dispatchers.Main).launch { 
                    async(Dispatchers.IO){
                        testDatabase?.testDao()?.insertData(testModel2)
                    }
                }
                
                textView.text = "저장완료"
            }

            button2.setOnClickListener {
                // 데이터 베이스 접속
                val testDatabase = TestDatabase.getInstance(this@MainActivity)
                CoroutineScope(Dispatchers.Main).launch {
                    // 데이터를 받아온다.
                    val job1 = async(Dispatchers.IO) {
                        testDatabase?.testDao()?.selectDataAll()
                    }
                    val dataList = job1.await() as List<TestModel>

                    textView.text = ""

                    dataList.forEach {
                        textView.append("testIdx : ${it.testIdx}\n")
                        textView.append("testData1 : ${it.testData1}\n")
                        textView.append("testData2 : ${it.testData2}\n\n")
                    }
                }
            }

            button3.setOnClickListener {
                // 데이터 베이스 접속
                val testDatabase = TestDatabase.getInstance(this@MainActivity)
                CoroutineScope(Dispatchers.Main).launch {
                    // 데이터를 받아온다.
                    val job1 = async(Dispatchers.IO) {
                        testDatabase?.testDao()?.selectDataOne(1)
                    }
                    val testModel = job1.await() as TestModel

                    textView.text = ""

                    textView.append("testIdx : ${testModel.testIdx}\n")
                    textView.append("testData1 : ${testModel.testData1}\n")
                    textView.append("testData2 : ${testModel.testData2}\n\n")
                }
            }

            button4.setOnClickListener {
                // 데이터 베이스 접속
                val testDatabase = TestDatabase.getInstance(this@MainActivity)
                // 데이터를 준비한다.
                // primary key인 testIdx가 조건절이 된다.
                val testModel = TestModel(testIdx = 1, testData1 = "새로운 문자열", testData2 = 55.55)
                // 수정한다.
                CoroutineScope(Dispatchers.Main).launch { 
                    async(Dispatchers.IO) { 
                        testDatabase?.testDao()?.updateData(testModel)
                    }
                    
                    textView.text = "수정 완료"
                }
            }

            button5.setOnClickListener {
                // 데이터 베이스에 접속한다.
                val testDatabase = TestDatabase.getInstance(this@MainActivity)
                // 데이터를 준비한다.
                // primary key인 testIdx가 조건절이 된다.
                val testModel = TestModel(testIdx = 1)
                // 삭제한다.
                CoroutineScope(Dispatchers.Main).launch {
                    async(Dispatchers.IO) {
                        testDatabase?.testDao()?.deleteData(testModel)
                    }

                    textView.text = "삭제 완료"
                }
            }
        }
    }
}