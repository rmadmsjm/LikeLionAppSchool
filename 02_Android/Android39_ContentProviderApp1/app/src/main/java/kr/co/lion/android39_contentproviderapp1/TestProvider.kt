package kr.co.lion.android39_contentproviderapp1

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri

class TestProvider : ContentProvider() {

    // 데이터베이스 접근 객체
    lateinit var sqLiteDatabase: SQLiteDatabase

    // delete
    // 두 번째 : 조건절
    // 세 번째 : 조건절의 ?에 바인딩될 값 배열
    // 반환 : 삭제된 행의 개수
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        val cnt = sqLiteDatabase.delete("TestTable", selection, selectionArgs)

        return cnt
    }

    // ContentProvider의 authorities를 봔환해줌
    // ContentProvider를 사용하는 쪽에서 다수의 Provider를 사용하고 있다면 이를 구분하기 위한 용도로 사용함
    override fun getType(uri: Uri): String? {
        return uri.authority
    }

    // insert
    // 첫 번째 매개 변수 : authorities가 담긴 Uri 객체
    // 두 번째 매개 변수 : 저장할 데이터가 담긴 객체
    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        // 저장
        sqLiteDatabase.insert("TestTable", null, values)

        return uri
    }

    // onCreate
    // ContentProvider가 생성될 때 자동으로 호출됨
    // 별로 할 작업이 없음
    // 데이터베이스에 접속하는 작업을 함
    override fun onCreate(): Boolean {
        val dbHelper = DBHelper(context!!)
        sqLiteDatabase = dbHelper.writableDatabase

        return true
    }

    // select
    // 첫 번째 매개 변수 : authorities가 담긴 Uri 객체
    // 두 번째 매개 변수 : 가져올 컬럼 몰록
    // 세 번째 매개 변수 : 조건 절
    // 네 번째 매개 변수 : 조건 절의 ?에 들어갈 값
    // 다섯 번째 매개 변수 : 정렬 기준
    // 반환 : 데이터를 접근할 수 있는 커서 객체
    override fun query( uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        val cursor = sqLiteDatabase.query("TestTable", projection, selection, selectionArgs,
            null, null, sortOrder)

        return cursor
    }

    // update
    // 두 번째 : 저장할 데이터
    // 세 번째 : 조건절
    // 네 번째 : 조건절의 ?에 바인딩될 값 배열
    // 반환 : 수정이 적용된 행의 개수
    override fun update(
        uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        val cnt = sqLiteDatabase.update("TestTable", values, selection, selectionArgs)

        return cnt
    }
}