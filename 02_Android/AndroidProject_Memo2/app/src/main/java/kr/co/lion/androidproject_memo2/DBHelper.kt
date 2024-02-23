package kr.co.lion.androidproject_memo2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "Memo.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val sql = """create table MemoTable
            |(idx integer primary key autoincrement,
            |title text not null,
            |content text not null,
            |date text not null)
        """.trimMargin()

        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}