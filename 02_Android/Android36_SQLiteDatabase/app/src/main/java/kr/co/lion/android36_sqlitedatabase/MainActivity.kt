package kr.co.lion.android36_sqlitedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android36_sqlitedatabase.databinding.ActivityMainBinding

/*
데이터 베이스 : 데이터들을 저장하고 관리하는 데이터 집합
SQLite : 데이터 베이스를 관리하는 관계형 데이터 베이스 시스템(RDBMS)의 한 종류
테이블 : 데이터 베이스 내에서 데이터를 묶어서 관리하는 요소
컬럼 : 테이블 내의 데이터 항목(컬럼, 열)
로우 : 테이블에 저장되어 있는 개체 하나에 대한 컬럼의 묶음(로우, 행)
 */

/*
안드로이드에서 SQLite DB 사용
1. SQLiteOpenHelper를 상속받은 클래스 만들기 (DBHelper.kt)
 */

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        // SQLiteOpenHelper 객체 생성
        // Helper 객체를 만든다고 해서 데이터 베이스에 접속하는 게 아님
        val dbHelper = DBHelper(this, "Test.db", DBHelper.databaseVersion)

        // 데이터 베이스에 접속
        // 이때 데이터 베이스 파일 찾게 됨
        // 파일이 있으면 데이터 베이스 파일을 열고
        // 없으면 파일 생성 후 데이터 파일을 열어 SQLiteOpenHelper에 있는 onCreate 메서드를 호출함
        // 만약 기존 데이터 베이스 파일 버전보다 더 높은 버전으로 설정하면 onUpgrade가 호출됨
        val sqLiteDatabase = dbHelper.writableDatabase

        // 데이버 베이스 닫기
        // 다 쓰면 꼭 닫아야 함
        sqLiteDatabase.close()
    }
}