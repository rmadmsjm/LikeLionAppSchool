package kr.co.lion.ex16_sqlitedatabase3_2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "Memo.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        // 쿼리문
        val sql = """create table MemoTable
            |(memoIdx integer primary key autoincrement,
            |memoSubject text not null,
            |memoText text not null)
        """.trimMargin()

        // 쿼리 실행
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}