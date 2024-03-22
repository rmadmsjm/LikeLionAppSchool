package kr.co.lion.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// entities : 엔티티들을 지정한다. 지정한 엔티티 하나당 하나의 테이블이 생성된다.
@Database(entities = [TestModel::class], version = 1)
// 추상클래스로 만들어야 한다.
abstract class TestDatabase : RoomDatabase() {
    // dao를 지정한다.
    abstract fun testDao() : TestDao

    companion object{
        // 데이터 베이스 객체를 담을 변수
        var testDatabase:TestDatabase? = null

        // RoomDatabase는 코루틴을 이용하게 된다.
        // 즉 비동기적으로 동작할 수 있도록 되어 있다.
        // 비동기적 작업시 데이터 베이스 접을 여러군데서 하면 문제가 발생될 수 있으므로
        // 데이터베이스 접속을 동기적으로 할 수 있도록 해야 한다.
        @Synchronized
        fun getInstance(context:Context) : TestDatabase? {
            if(testDatabase == null){
                synchronized(TestDatabase::class){
                    // 데이터이스를 생성하고 Model들의 구조와 동일한 테이블을 생성한다.
                    // test.db : 접속할 sqlite database 파일
                    testDatabase = Room.databaseBuilder(
                        context.applicationContext, TestDatabase::class.java, "test.db"
                    ).build()
                }
            }
            return testDatabase
        }
    }

}