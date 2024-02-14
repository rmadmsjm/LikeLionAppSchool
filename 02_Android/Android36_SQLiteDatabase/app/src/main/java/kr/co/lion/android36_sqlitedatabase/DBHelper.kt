package kr.co.lion.android36_sqlitedatabase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

/*
SQLiteOpenHelper의 생성자 매개변수
1) 첫 번째 : context
2) 두 번째 : 데이터 베이스 파일의 이름
            이 이름으로 만들어진 데이터 베이스 파일이 없다면 OS가 파일을 만들고, OS가 onCreate 메서드 호출함
3) 세 번째 : null에 대한 처리를 어떻게 할 것인가, 그냥 null 셋팅
4) 네 번째 : 데이터 베이스 파일의 버전
            ⭐ 어플리케이션의 버전과 무관, 아무 숫자나 지정
            어플리케이션을 서비스 하는 중에 테이블의 개수나 구조를 변경해야 하는 경우,
            이전의 숫자보다 높은 숫자로 지정해주면 예전에 저장한 어플리케이션은 onUpgrade 메서드 호출함
 */

class DBHelper(context: Context, dbName: String, version: Int) : SQLiteOpenHelper(context, dbName, null, version) {

    companion object {
        val databaseVersion = 1
    }

    // onCreate : 파일이 없을 때 파일 생성 후 호출되고,
    //            어플리케이션 설치 후, 어플리케이션 실행 시 데이터 베이스를 처음 사용할 때 데이터 베이스 파일이 없을 경우에만 호출됨
    override fun onCreate(db: SQLiteDatabase?) {
        Log.d("test1234", "onCreate")
    }

    // onUpgrade : 어플리케이션을 업데이트한 사람들을 위한 메서드, 기존의 테이블을 최신 형태의 구조로 변경하는 작업
    // 어플리케이션을 업데이트 후 onCreate 메서드 호출되지 않음
    // -> why? 어플리케이션 설치 후, 어플리케이션 실행 시 데이터 베이스를 처음 사용할 때 데이터 베이스 파일이 경우에만 호출됨
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.d("test1234", "onUpgrade : $oldVersion -> $newVersion")
    }
}